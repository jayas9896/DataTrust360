package com.datatrust360.auth;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Security configuration for public endpoints, RBAC, OAuth2 login, and SAML SSO.
 *
 * <p>Importance: Centralized security policy ensures consistent enforcement across routes.</p>
 * <p>Alternatives: Configure security per controller, but that increases drift and risk.</p>
 */
@Configuration
@EnableMethodSecurity
public class SecurityConfig {

    /**
     * Builds the HTTP security filter chain for authentication and authorization.
     *
     * <p>Importance: Establishes public routes and secure access for the gateway.</p>
     * <p>Alternatives: Use default security auto-configuration, but explicit rules
     * are required for public access and multiple auth mechanisms.</p>
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(authz -> authz
                .requestMatchers("/public/**", "/actuator/health", "/v3/api-docs/**", "/swagger-ui/**").permitAll()
                .anyRequest().authenticated()
            )
            .oauth2Login(Customizer.withDefaults())
            .saml2Login(Customizer.withDefaults())
            .oauth2ResourceServer(oauth2 -> oauth2.jwt(Customizer.withDefaults()))
            .csrf(csrf -> csrf.disable());

        return http.build();
    }

    /**
     * Provides a simple in-memory user store for local RBAC validation.
     *
     * <p>Importance: Enables quick verification of ADMIN and VIEWER role enforcement.</p>
     * <p>Alternatives: Back with database or LDAP, but in-memory users are sufficient
     * for local development and test scaffolding.</p>
     */
    @Bean
    public UserDetailsService userDetailsService() {
        return new InMemoryUserDetailsManager(
            User.withUsername("admin")
                .password("{noop}admin")
                .roles("ADMIN")
                .build(),
            User.withUsername("viewer")
                .password("{noop}viewer")
                .roles("VIEWER")
                .build()
        );
    }
}
