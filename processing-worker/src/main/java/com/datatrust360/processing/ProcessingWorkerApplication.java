package com.datatrust360.processing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Bootstraps the processing worker for stream consumption and async jobs.
 *
 * <p>Importance: Centralizes initialization of Kafka listeners, Kinesis polling, and RabbitMQ workers.</p>
 * <p>Alternatives: Split into separate services, but a single worker simplifies MVP operations.</p>
 */
@SpringBootApplication
@EnableScheduling
public class ProcessingWorkerApplication {
    /**
     * Starts the processing worker application.
     *
     * <p>Importance: Ensures message listeners, schedulers, and queues are registered at startup.</p>
     * <p>Alternatives: Use container-managed startup, but Spring Boot eases local runs.</p>
     */
    public static void main(String[] args) {
        SpringApplication.run(ProcessingWorkerApplication.class, args);
    }
}
