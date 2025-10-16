package com.datatrust360.auth;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Admin-only endpoints for operational status and privileged actions.
 *
 * <p>Importance: Demonstrates RBAC enforcement for protected system functionality.</p>
 * <p>Alternatives: Expose status via public endpoints, but that risks leaking
 * sensitive system information.</p>
 */
@RestController
@RequestMapping("/api/admin")
public class AdminController {

    /**
     * Returns an admin-only status message.
     *
     * <p>Importance: Validates role-based access control is enforced at runtime.</p>
     * <p>Alternatives: Secure at gateway only, but method-level security provides
     * defense in depth.</p>
     */
    @GetMapping("/status")
    @PreAuthorize("hasRole('ADMIN')")
    public String status() {
        return "Admin only status";
    }
}
