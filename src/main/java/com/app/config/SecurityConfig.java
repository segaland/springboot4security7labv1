package com.app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

// Classic configuration of Spring Security
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
//        return httpSecurity
//                .csrf(csrf -> csrf.disable())
//                .httpBasic(Customizer.withDefaults())
//                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//                .authorizeHttpRequests(http ->{
//
//                    //Configured public endpoints
//                    http.requestMatchers(HttpMethod.GET,"/auth/hello").permitAll();
//
//                    //Configured private endpoints
//                    http.requestMatchers(HttpMethod.GET,"/auth/hello-secured").hasAuthority("CREATE");
//
//                    //Configured all other endpoints
//                    // if authenticaded you can access
//                    //http.anyRequest().authenticated();
//                    // if not mentioned in private endpoint configuration you can not access
//                    http.anyRequest().denyAll();
//                })
//                .build();
//    }

      @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
        return httpSecurity
                .csrf(csrf -> csrf.disable())
                .httpBasic(Customizer.withDefaults())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception{
       return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public AuthenticationProvider authenticationProvider(){
        return new DaoAuthenticationProvider(userDetailsService());
    }

    @Bean
    public UserDetailsService userDetailsService(){
       List<UserDetails> users = new ArrayList<UserDetails>();
       users.add(User.withUsername("manolo")
               .password("{noop}123456")
               .roles("ADMIN")
               .authorities("READ","CREATE")
               .build());

        users.add(User.withUsername("sandra")
                .password("{noop}123456")
                .roles("USER")
                .authorities("READ")
                .build());


        return new InMemoryUserDetailsManager(users);
    }
}
