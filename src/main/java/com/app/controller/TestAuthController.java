package com.app.controller;


import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@PreAuthorize("denyAll()")
public class TestAuthController {

// Endpoints de la parte 1
//    @GetMapping("/hello")
//    @PreAuthorize("permitAll()")
//    public String hello(){
//      return "Hello World";
//   }
//
//    @GetMapping("/hello-secured")
//    @PreAuthorize("hasAuthority('READ')")
//    public String helloSecured(){
//        return "Hello World Secured";
//    }
//
//    @GetMapping("/hello-secured2")
//    @PreAuthorize("hasRole('ADMIN')")
//    public String helloSecured2(){
//        return "Hello World Secured2";
//    }

// Endpoints de la parte 2 con DB Oracle
    @GetMapping("/get")
    @PreAuthorize("hasAuthority('READ')")
    public String helloGet(){
       return "Hello World Get";
    }

    @PostMapping("/post")
    @PreAuthorize("hasAuthority('CREATE')")
    public String helloPost(){
        return "Hello World Post";
    }

    @PutMapping("/put")
    @PreAuthorize("hasAuthority('UPDATE')")
    public String helloPut(){
        return "Hello World Put";
    }

    @DeleteMapping("/delete")
    @PreAuthorize("hasAuthority('DELETE')")
    public String helloDelete(){
        return "Hello World Delete";
    }

    @PatchMapping("/patch")
    @PreAuthorize("hasAuthority('REFACTOR')")
    public String helloPatch(){
        return "$2a$10$1uydapT/39K5ACSzJW3KfuVMdLm.ImPPiWrJPS04wvNfNVkPybnEO";
    }
}
