package com.datatrust360.ingest.grpc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Bootstraps the gRPC ingestion service for bulk event ingestion.
 *
 * <p>Importance: Enables efficient batch ingestion for high-throughput clients.</p>
 * <p>Alternatives: Use REST only, but gRPC reduces overhead for large payloads.</p>
 */
@SpringBootApplication
public class IngestGrpcApplication {
    /**
     * Starts the Spring Boot gRPC ingestion service.
     *
     * <p>Importance: Initializes the gRPC server and Kafka publisher dependencies.</p>
     * <p>Alternatives: Standalone gRPC server, but Spring Boot simplifies lifecycle management.</p>
     */
    public static void main(String[] args) {
        SpringApplication.run(IngestGrpcApplication.class, args);
    }
}
