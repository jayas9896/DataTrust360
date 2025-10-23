package com.datatrust360.processing;

import com.datatrust360.common.EventEnvelope;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final Logger logger = LoggerFactory.getLogger(StreamIngestListener.class);

    private final RabbitTemplate rabbitTemplate;
    private final ObjectMapper objectMapper;
    private final StorageClient storageClient;

    /**
     * Creates the listener with RabbitMQ publishing dependency.
     *
     * <p>Importance: Enables queueing of async jobs from streaming events.</p>
     * <p>Alternatives: Use direct service calls, but queues improve resilience.</p>
     */
    public StreamIngestListener(RabbitTemplate rabbitTemplate, ObjectMapper objectMapper, StorageClient storageClient) {
        this.rabbitTemplate = rabbitTemplate;
        this.objectMapper = objectMapper;
        this.storageClient = storageClient;
    }

    /**
     * Receives raw events from Kafka and enqueues scoring jobs.
     *
     * <p>Importance: Keeps stream consumption lightweight and reliable.</p>
     * <p>Alternatives: Write to database first, but async jobs keep ingestion fast.</p>
     */
    @KafkaListener(topics = "dt360.events.raw", groupId = "processing-worker")
    public void onEvent(String payload) {
        EventEnvelope envelope = parseEnvelope(payload);
        storageClient.persistEvent(envelope);
        rabbitTemplate.convertAndSend(RabbitConfig.ANOMALY_QUEUE, payload);
    }

    /**
     * Parses raw JSON into an EventEnvelope.
     *
     * <p>Importance: Enables storage persistence and downstream processing using a normalized model.</p>
     * <p>Alternatives: Store raw JSON only, but structured access improves validation.</p>
     */
    private EventEnvelope parseEnvelope(String payload) {
        try {
            return objectMapper.readValue(payload, EventEnvelope.class);
        } catch (Exception ex) {
            logger.error("Failed to parse event payload for storage", ex);
            throw new IllegalArgumentException("Invalid event payload", ex);
        }
    }
}
