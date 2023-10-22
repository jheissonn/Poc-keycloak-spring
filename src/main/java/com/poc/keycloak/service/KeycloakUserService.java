package com.poc.keycloak.service;

import lombok.RequiredArgsConstructor;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
    public class KeycloakUserService {

    public void createUser(String realm, UserRepresentation user) {
    }
}
