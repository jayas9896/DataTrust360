package com.datatrust360.ingest.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Bootstraps the REST ingestion service.
 *
 * <p>Importance: Provides an HTTP entry point for event ingestion with OpenAPI support.</p>
 * <p>Alternatives: Use only gRPC ingestion, but REST remains essential for broad client
 * compatibility.</p>
 */
@SpringBootApplication
public class IngestRestApplication {
    /**
     * Starts the Spring Boot ingestion service.
     *
     * <p>Importance: Ensures REST endpoints, Kafka, and Kinesis integration are initialized.</p>
     * <p>Alternatives: Deploy as a WAR, but Boot JARs are simpler for containerization.</p>
     */
    public static void main(String[] args) {
        SpringApplication.run(IngestRestApplication.class, args);
    }
}
