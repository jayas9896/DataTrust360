package com.datatrust360.ingest.rest;

import com.datatrust360.common.EventEnvelope;

/**
 * Publishing contract for REST ingestion to streaming backends.
 *
 * <p>Importance: Separates controller concerns from streaming transport details.</p>
 * <p>Alternatives: Embed publishing logic directly in controllers, but that reduces testability.</p>
 */
public interface IngestService {
    /**
     * Publishes events to Kafka for internal streaming.
     *
     * <p>Importance: Kafka is optimized for internal low-latency event processing.</p>
     * <p>Alternatives: Use Kinesis for all paths, but Kafka is preferred for internal pipelines.</p>
     */
    void publishToKafka(EventEnvelope envelope);

    /**
     * Publishes events to Kinesis for partner or edge ingestion flows.
     *
     * <p>Importance: Kinesis fits managed, AWS-integrated ingestion at scale.</p>
     * <p>Alternatives: Use Kafka for partners, but Kinesis simplifies AWS integration.</p>
     */
    void publishToKinesis(EventEnvelope envelope);
}
