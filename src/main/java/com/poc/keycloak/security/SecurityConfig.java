package com.poc.keycloak.security;

import java.util.Collection;
import java.util.Map;

import jakarta.servlet.DispatcherType;
import org.keycloak.adapters.springsecurity.KeycloakConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;


@KeycloakConfiguration
@Configuration
@EnableMethodSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(registry -> registry
                                .dispatcherTypeMatchers(DispatcherType.FORWARD).permitAll()

                        .requestMatchers("**/login").permitAll()
                                .anyRequest().authenticated()
                        )
                .oauth2ResourceServer(oauth2Configure -> oauth2Configure.jwt(jwtConfigurer -> jwtConfigurer.jwtAuthenticationConverter(jwt -> {
                    Map<String, Collection<String>> realmAccess = jwt.getClaim("realm_access");
                    Collection<String> realmGroupAccess =  jwt.getClaim("groups");

                    Collection<String> roles = realmAccess.get("roles");
                    roles.addAll(realmGroupAccess);
                    var grantedAuthorities = roles.stream()
                            .map(SimpleGrantedAuthority::new)
                            .toList();
                    return new JwtAuthenticationToken(jwt, grantedAuthorities);
                })))
        ;

        return httpSecurity.build();
    }
}
