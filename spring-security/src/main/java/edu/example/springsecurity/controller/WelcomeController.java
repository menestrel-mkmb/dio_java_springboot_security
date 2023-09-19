package edu.example.springsecurity.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WelcomeController {
    @GetMapping
    public String welcome(){
        return "Welcome to this demo Spring Boot Web with Spring Security Project";
    }

    @GetMapping("/users")
    @PreAuthorize("hasAnyRole('MANAGERS','USERS')")
    public String users() {
        return "Authorized user";
    }

    @GetMapping("/managers")
    @PreAuthorize("hasRole('MANAGERS')")
    public String managers() {
        return "Authorized manager";
    }
}
