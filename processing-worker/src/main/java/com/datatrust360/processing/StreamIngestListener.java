package com.datatrust360.processing;

import com.datatrust360.common.EventEnvelope;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private final ObjectMapper objectMapper;
    private final StorageClient storageClient;
    private final PartnerQueuePublisher queuePublisher;

    /**
     * Creates the listener with storage and queue publishing dependencies.
     *
     * <p>Importance: Ensures events are persisted and queued with a single responsibility boundary.</p>
     * <p>Alternatives: Publish directly from Kafka listener, but separate publisher improves reuse.</p>
     */
    public StreamIngestListener(
        ObjectMapper objectMapper,
        StorageClient storageClient,
        PartnerQueuePublisher queuePublisher
    ) {
        this.objectMapper = objectMapper;
        this.storageClient = storageClient;
        this.queuePublisher = queuePublisher;
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
        queuePublisher.enqueue(payload);
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
