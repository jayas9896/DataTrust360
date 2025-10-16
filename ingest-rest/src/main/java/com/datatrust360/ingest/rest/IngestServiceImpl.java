package com.datatrust360.ingest.rest;

import com.datatrust360.common.EventEnvelope;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.core.SdkBytes;
import software.amazon.awssdk.services.kinesis.KinesisClient;
import software.amazon.awssdk.services.kinesis.model.PutRecordRequest;

/**
 * Default implementation of ingestion publishing to Kafka and Kinesis.
 *
 * <p>Importance: Centralizes serialization and publish logic for REST ingestion.</p>
 * <p>Alternatives: Use separate services per transport, but a unified implementation
 * reduces duplication and keeps behaviors consistent.</p>
 */
@Service
public class IngestServiceImpl implements IngestService {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final KinesisClient kinesisClient;
    private final ObjectMapper objectMapper;
    private final String kafkaTopic;
    private final String kinesisStream;

    /**
     * Constructs the ingest service with stream clients and configuration.
     *
     * <p>Importance: Ensures transport clients and topic/stream names are configured centrally.</p>
     * <p>Alternatives: Use static clients, but dependency injection makes testing simpler.</p>
     */
    public IngestServiceImpl(
        KafkaTemplate<String, String> kafkaTemplate,
        KinesisClient kinesisClient,
        ObjectMapper objectMapper,
        @Value("${ingest.kafka.topic}") String kafkaTopic,
        @Value("${ingest.kinesis.stream}") String kinesisStream
    ) {
        this.kafkaTemplate = kafkaTemplate;
        this.kinesisClient = kinesisClient;
        this.objectMapper = objectMapper;
        this.kafkaTopic = kafkaTopic;
        this.kinesisStream = kinesisStream;
    }

    /**
     * Publishes to Kafka using the tenant ID as the partition key.
     *
     * <p>Importance: Tenant-based keys keep ordering and isolation by tenant.</p>
     * <p>Alternatives: Random partition keys, but that reduces tenant-level ordering.</p>
     */
    @Override
    public void publishToKafka(EventEnvelope envelope) {
        kafkaTemplate.send(kafkaTopic, envelope.getTenantId(), toJson(envelope));
    }

    /**
     * Publishes to Kinesis using the tenant ID as the partition key.
     *
     * <p>Importance: Aligns partitioning with tenant isolation in partner flows.</p>
     * <p>Alternatives: Use source as the key, but tenant isolation is the primary concern.</p>
     */
    @Override
    public void publishToKinesis(EventEnvelope envelope) {
        PutRecordRequest request = PutRecordRequest.builder()
            .streamName(kinesisStream)
            .partitionKey(envelope.getTenantId())
            .data(SdkBytes.fromUtf8String(toJson(envelope)))
            .build();
        kinesisClient.putRecord(request);
    }

    /**
     * Serializes the event envelope into JSON.
     *
     * <p>Importance: JSON is a common interchange format for streaming payloads.</p>
     * <p>Alternatives: Use Avro/Protobuf, but JSON simplifies interoperability for now.</p>
     */
    private String toJson(EventEnvelope envelope) {
        try {
            return objectMapper.writeValueAsString(envelope);
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException("Unable to serialize event", e);
        }
    }
}
