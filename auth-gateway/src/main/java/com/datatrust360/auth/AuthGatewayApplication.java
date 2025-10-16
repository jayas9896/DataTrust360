package com.datatrust360.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Bootstraps the authentication gateway service for OAuth2/SAML and public endpoints.
 *
 * <p>Importance: Centralizes authentication flows and public access in one entry point.</p>
 * <p>Alternatives: Split public and auth services, but that adds complexity for routing
 * and shared security configuration.</p>
 */
@SpringBootApplication
public class AuthGatewayApplication {
    /**
     * Starts the Spring Boot application.
     *
     * <p>Importance: Initializes security filters and web endpoints on startup.</p>
     * <p>Alternatives: Use a servlet container deployment, but Spring Boot simplifies
     * local and containerized execution.</p>
     */
    public static void main(String[] args) {
        SpringApplication.run(AuthGatewayApplication.class, args);
    }
}
