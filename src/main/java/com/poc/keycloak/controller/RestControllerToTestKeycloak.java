package com.poc.keycloak.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/keycloak")
@RestController
public class RestControllerToTestKeycloak {

    @PostMapping("/write-rule")
    @PreAuthorize("hasAuthority('WRITE')")
    public String get(){
        return "Based on WRITE rule";
    }

    @GetMapping("/read-rule")
    @PreAuthorize("hasAuthority('READ')")
    public String getAnyRule(){
        return "Based on READ rule";
    }

    @GetMapping("/admin-read")
    @PreAuthorize("hasAuthority('ADMIN_READ')")
    public String getAdminRead(){
        return "Based on ADMIN_READ rule";
    }

}

