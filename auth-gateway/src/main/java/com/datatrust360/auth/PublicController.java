package com.datatrust360.auth;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Public endpoints that do not require authentication.
 *
 * <p>Importance: Provides a safe landing page and health messaging without auth friction.</p>
 * <p>Alternatives: Serve from a separate static site, but hosting in the gateway
 * simplifies routing and monitoring.</p>
 */
@RestController
@RequestMapping("/public")
public class PublicController {

    /**
     * Returns a basic landing message.
     *
     * <p>Importance: Demonstrates unauthenticated access for marketing or onboarding.</p>
     * <p>Alternatives: Redirect to external site, but local endpoint aids availability checks.</p>
     */
    @GetMapping("/landing")
    public String landing() {
        return "DataTrust360 public landing page";
    }
}
