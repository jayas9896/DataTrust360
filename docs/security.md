# Security Overview

## Public Access
- `/public/**` is accessible without authentication.
- Swagger and health endpoints are public for discovery and readiness checks.

## Role-Based Access Control
- `/api/admin/**` requires `ROLE_ADMIN`.
- Method-level security uses `@PreAuthorize` for defense in depth.

## OAuth2 Login
- Configured for enterprise IdPs via `spring.security.oauth2.client`.
- Redirect-based login is used for interactive users.

## SAML SSO
- Configured via `spring.security.saml2.relyingparty`.
- Suitable for enterprise federation and legacy IdPs.

## Resource Server
- OAuth2 resource server enabled for JWT validation on protected APIs.

## Notes
- Update issuer URIs and certificates in `auth-gateway/src/main/resources/application.yml`.
- For production, replace in-memory users with database or external IdP mappings.
