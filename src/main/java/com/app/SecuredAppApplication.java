package com.app;

import com.app.persistence.entity.PermissionEntity;
import com.app.persistence.entity.RoleEntity;
import com.app.persistence.entity.RoleEnum;
import com.app.persistence.entity.UserEntity;
import com.app.persistence.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;
import java.util.Set;

@SpringBootApplication
public class SecuredAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(SecuredAppApplication.class, args);
	}

    @Bean
    CommandLineRunner init(UserRepository userRepository){
        return args -> {
            PermissionEntity createPermission = PermissionEntity.builder()
                    .name("CREATE")
                    .build();
            PermissionEntity readPermission = PermissionEntity.builder()
                    .name("READ")
                    .build();
            PermissionEntity updatePermission = PermissionEntity.builder()
                    .name("UPDATE")
                    .build();
            PermissionEntity deletePermission = PermissionEntity.builder()
                    .name("DELETE")
                    .build();
            PermissionEntity refactorPermission = PermissionEntity.builder()
                    .name("REFACTOR")
                    .build();
            RoleEntity roleAdmin = RoleEntity.builder()
                    .roleEnum(RoleEnum.ADMIN)
                    .permissionList(Set.of(createPermission,readPermission,updatePermission,deletePermission))
                    .build();
            RoleEntity roleUser = RoleEntity.builder()
                    .roleEnum(RoleEnum.USER)
                    .permissionList(Set.of(createPermission,readPermission))
                    .build();
            RoleEntity roleInvited = RoleEntity.builder()
                    .roleEnum(RoleEnum.INVITED)
                    .permissionList(Set.of(readPermission))
                    .build();
            RoleEntity roleDeveloper = RoleEntity.builder()
                    .roleEnum(RoleEnum.DEVELOPER)
                    .permissionList(Set.of(createPermission,readPermission,updatePermission,deletePermission, refactorPermission))
                    .build();

            UserEntity userManolo = UserEntity.builder()
                    .username("manolo")
                    .password("$2a$10$1uydapT/39K5ACSzJW3KfuVMdLm.ImPPiWrJPS04wvNfNVkPybnEO")
                    .isEnabled(true)
                    .accountNotExpired(true)
                    .accountNotLocked(true)
                    .credentialNotExpired(true)
                    .roles(Set.of(roleAdmin))
                    .build();

            UserEntity userSandra = UserEntity.builder()
                    .username("sandra")
                    .password("$2a$10$1uydapT/39K5ACSzJW3KfuVMdLm.ImPPiWrJPS04wvNfNVkPybnEO")
                    .isEnabled(true)
                    .accountNotExpired(true)
                    .accountNotLocked(true)
                    .credentialNotExpired(true)
                    .roles(Set.of(roleDeveloper))
                    .build();

            UserEntity userRoberto = UserEntity.builder()
                    .username("roberto")
                    .password("$2a$10$1uydapT/39K5ACSzJW3KfuVMdLm.ImPPiWrJPS04wvNfNVkPybnEO")
                    .isEnabled(true)
                    .accountNotExpired(true)
                    .accountNotLocked(true)
                    .credentialNotExpired(true)
                    .roles(Set.of(roleInvited))
                    .build();

            UserEntity userMaria = UserEntity.builder()
                    .username("maria")
                    .password("$2a$10$1uydapT/39K5ACSzJW3KfuVMdLm.ImPPiWrJPS04wvNfNVkPybnEO")
                    .isEnabled(true)
                    .accountNotExpired(true)
                    .accountNotLocked(true)
                    .credentialNotExpired(true)
                    .roles(Set.of(roleDeveloper))
                    .build();

            UserEntity userMiguel = UserEntity.builder()
                    .username("miguel")
                    .password("$2a$10$1uydapT/39K5ACSzJW3KfuVMdLm.ImPPiWrJPS04wvNfNVkPybnEO")
                    .isEnabled(true)
                    .accountNotExpired(true)
                    .accountNotLocked(true)
                    .credentialNotExpired(true)
                    .roles(Set.of(roleUser))
                    .build();

            userRepository.saveAll(List.of(userMaria,userManolo,userRoberto,userSandra,userMiguel));
        };
    }

}
