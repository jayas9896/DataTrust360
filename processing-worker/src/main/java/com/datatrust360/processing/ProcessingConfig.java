package com.datatrust360.processing;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.kinesis.KinesisClient;
import software.amazon.awssdk.services.kinesis.KinesisClientBuilder;

import java.net.URI;

/**
 * Configuration for HTTP clients used by the processing worker.
 *
 * <p>Importance: Centralizes outbound client configuration for storage integration.</p>
 * <p>Alternatives: Instantiate clients directly in services, but that scatters configuration.</p>
 */
@Configuration
public class ProcessingConfig {

    /**
     * Builds a RestClient for storage-service communication.
     *
     * <p>Importance: Provides a reusable, base-url-aware client for persistence calls.</p>
     * <p>Alternatives: Use RestTemplate directly, but RestClient is the modern Spring option.</p>
     */
    @Bean
    public RestClient storageRestClient(@Value("${processing.storage.base-url}") String baseUrl) {
        return RestClient.builder()
            .baseUrl(baseUrl)
            .build();
    }

    /**
     * Builds a Kinesis client with optional endpoint override.
     *
     * <p>Importance: Enables local Kinesis emulation while keeping AWS compatibility.</p>
     * <p>Alternatives: Use default AWS endpoints only, but that blocks local testing.</p>
     */
    @Bean
    public KinesisClient kinesisClient(@Value("${processing.kinesis.endpoint:}") String endpoint) {
        KinesisClientBuilder builder = KinesisClient.builder()
            .region(Region.US_EAST_1);
        if (endpoint != null && !endpoint.isBlank()) {
            builder.endpointOverride(URI.create(endpoint));
        }
        return builder.build();
    }
}
