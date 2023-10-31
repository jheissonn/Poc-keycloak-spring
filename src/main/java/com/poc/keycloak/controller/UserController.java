package com.poc.keycloak.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.keycloak.representations.idm.GroupRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final Keycloak keycloak = KeycloakBuilder.builder()
            .serverUrl("http://localhost:8080/auth")
            .realm("master")
            .username("admin")
            .password("admin")
            .clientId("admin-cli")
            .build();

    @PostMapping
    public String createUser(@RequestBody UserRepresentation userRepresentation) {
        keycloak.realm("examples").users().create(userRepresentation);
        return "User created successfully.";
    }

    @GetMapping
    public List<UserRepresentation> getUsers() {
        return keycloak.realm("examples").users().list();
    }

    @GetMapping("/groups")
    public List<GroupRepresentation> getGroups() {
        return keycloak.realm("examples").groups().groups();
    }


}
