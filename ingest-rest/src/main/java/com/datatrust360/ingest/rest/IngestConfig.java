package com.datatrust360.ingest.rest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.kinesis.KinesisClient;
import software.amazon.awssdk.services.kinesis.KinesisClientBuilder;

import java.net.URI;

/**
 * Configuration for ingestion dependencies like the Kinesis client.
 *
 * <p>Importance: Centralizes AWS client configuration for consistency across environments.</p>
 * <p>Alternatives: Build clients ad hoc in services, but that scatters configuration.</p>
 */
@Configuration
public class IngestConfig {

    /**
     * Creates a Kinesis client with optional endpoint override.
     *
     * <p>Importance: Supports LocalStack endpoints for local development.</p>
     * <p>Alternatives: Use default AWS endpoints only, but that blocks local testing.</p>
     */
    @Bean
    public KinesisClient kinesisClient(@Value("${ingest.kinesis.endpoint:}") String endpoint) {
        KinesisClientBuilder builder = KinesisClient.builder()
            .region(Region.US_EAST_1);
        if (endpoint != null && !endpoint.isBlank()) {
            builder.endpointOverride(URI.create(endpoint));
        }
        return builder.build();
    }
}
