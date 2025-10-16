package com.datatrust360.processing;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * Consumes Kafka streams and enqueues jobs for asynchronous processing.
 *
 * <p>Importance: Decouples real-time ingestion from heavier anomaly scoring tasks.</p>
 * <p>Alternatives: Process inline during consumption, but that increases latency and backpressure.</p>
 */
@Component
public class StreamIngestListener {

    private final RabbitTemplate rabbitTemplate;

    /**
     * Creates the listener with RabbitMQ publishing dependency.
     *
     * <p>Importance: Enables queueing of async jobs from streaming events.</p>
     * <p>Alternatives: Use direct service calls, but queues improve resilience.</p>
     */
    public StreamIngestListener(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    /**
     * Receives raw events from Kafka and enqueues scoring jobs.
     *
     * <p>Importance: Keeps stream consumption lightweight and reliable.</p>
     * <p>Alternatives: Write to database first, but async jobs keep ingestion fast.</p>
     */
    @KafkaListener(topics = "dt360.events.raw", groupId = "processing-worker")
    public void onEvent(String payload) {
        rabbitTemplate.convertAndSend(RabbitConfig.ANOMALY_QUEUE, payload);
    }
}
