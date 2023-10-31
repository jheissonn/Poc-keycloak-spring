package com.poc.keycloak.controller;

import com.poc.keycloak.service.KeycloakBuilderService;
import lombok.RequiredArgsConstructor;
import org.keycloak.representations.AccessTokenResponse;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
@RequiredArgsConstructor
public class LoginController {
    private final KeycloakBuilderService keycloakBuilderService;

    @PostMapping
    public AccessTokenResponse login(@RequestParam("username") String username,
                                     @RequestParam("password") String password) {
        return keycloakBuilderService.getKeycloak(username, password).tokenManager().getAccessToken();
    }
}
