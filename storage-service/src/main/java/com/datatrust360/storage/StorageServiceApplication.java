package com.datatrust360.storage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Bootstraps the storage service for SQL and NoSQL persistence.
 *
 * <p>Importance: Central entry point for storage APIs and repository configuration.</p>
 * <p>Alternatives: Separate SQL and NoSQL services, but one service reduces operational overhead.</p>
 */
@SpringBootApplication
public class StorageServiceApplication {
    /**
     * Starts the storage service.
     *
     * <p>Importance: Initializes JPA and Mongo repositories.</p>
     * <p>Alternatives: Deploy as separate apps, but a unified service simplifies MVP deployment.</p>
     */
    public static void main(String[] args) {
        SpringApplication.run(StorageServiceApplication.class, args);
    }
}
