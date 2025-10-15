package com.datatrust360.ingest.rest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.kinesis.KinesisClient;
import software.amazon.awssdk.services.kinesis.KinesisClientBuilder;

import java.net.URI;

@Configuration
public class IngestConfig {

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
