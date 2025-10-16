package com.datatrust360.processing;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * RabbitMQ configuration for async processing queues.
 *
 * <p>Importance: Defines durable queues for anomaly scoring workloads.</p>
 * <p>Alternatives: Use Kafka for async jobs, but RabbitMQ is optimized for work queues.</p>
 */
@Configuration
public class RabbitConfig {

    public static final String ANOMALY_QUEUE = "dt360.anomaly.scoring";

    /**
     * Declares the anomaly scoring queue.
     *
     * <p>Importance: Ensures the queue exists before workers start consuming.</p>
     * <p>Alternatives: Rely on auto-creation by producers, but that can be race-prone.</p>
     */
    @Bean
    public Queue anomalyQueue() {
        return new Queue(ANOMALY_QUEUE, true);
    }
}
