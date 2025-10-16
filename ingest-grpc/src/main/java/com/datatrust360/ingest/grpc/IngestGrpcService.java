package com.datatrust360.ingest.grpc;

import com.datatrust360.common.EventEnvelope;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.kafka.core.KafkaTemplate;

import java.time.Instant;
import java.util.Map;

/**
 * gRPC service handling bulk ingestion and publishing to Kafka.
 *
 * <p>Importance: Provides a high-throughput ingestion path for enterprise clients.</p>
 * <p>Alternatives: Use REST batching, but gRPC reduces payload overhead and latency.</p>
 */
@GrpcService
public class IngestGrpcService extends BulkIngestServiceGrpc.BulkIngestServiceImplBase {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;
    private final String kafkaTopic = "dt360.events.bulk";

    /**
     * Creates the gRPC service with required dependencies.
     *
     * <p>Importance: Allows Kafka publishing and JSON parsing to be injected and tested.</p>
     * <p>Alternatives: Build clients inline, but that complicates testing.</p>
     */
    public IngestGrpcService(KafkaTemplate<String, String> kafkaTemplate, ObjectMapper objectMapper) {
        this.kafkaTemplate = kafkaTemplate;
        this.objectMapper = objectMapper;
    }

    /**
     * Ingests a batch of events and publishes each to Kafka.
     *
     * <p>Importance: Handles high-volume ingestion with a single round trip.</p>
     * <p>Alternatives: Use streaming RPC, but simple batching is sufficient for MVP throughput.</p>
     */
    @Override
    public void ingestBatch(IngestBatchRequest request, StreamObserver<IngestBatchResponse> responseObserver) {
        int accepted = 0;
        for (EventPayload payload : request.getEventsList()) {
            EventEnvelope envelope = new EventEnvelope();
            envelope.setTenantId(payload.getTenantId());
            envelope.setSource(payload.getSource());
            envelope.setSchemaVersion(payload.getSchemaVersion());
            envelope.setReceivedAt(parseInstant(payload.getReceivedAt()));
            envelope.setPayload(parsePayload(payload.getJsonPayload()));
            kafkaTemplate.send(kafkaTopic, envelope.getTenantId(), toJson(envelope));
            accepted++;
        }
        responseObserver.onNext(IngestBatchResponse.newBuilder().setAccepted(accepted).build());
        responseObserver.onCompleted();
    }

    /**
     * Parses an ISO-8601 timestamp, defaulting to now if blank.
     *
     * <p>Importance: Ensures each event has a usable timestamp for ordering.</p>
     * <p>Alternatives: Reject missing timestamps, but that would drop partner batches.</p>
     */
    private Instant parseInstant(String value) {
        if (value == null || value.isBlank()) {
            return Instant.now();
        }
        return Instant.parse(value);
    }

    /**
     * Parses the JSON payload into a generic map.
     *
     * <p>Importance: Supports dynamic schemas without strict compilation-time models.</p>
     * <p>Alternatives: Use Protobuf Any, but JSON is simpler for external clients.</p>
     */
    private Map<String, Object> parsePayload(String json) {
        try {
            return objectMapper.readValue(json, new TypeReference<>() {});
        } catch (Exception ex) {
            throw new IllegalArgumentException("Invalid json payload", ex);
        }
    }

    /**
     * Serializes an event envelope to JSON.
     *
     * <p>Importance: Produces a portable representation for Kafka pipelines.</p>
     * <p>Alternatives: Use Avro or Protobuf in Kafka, but JSON keeps tooling simple.</p>
     */
    private String toJson(EventEnvelope envelope) {
        try {
            return objectMapper.writeValueAsString(envelope);
        } catch (Exception ex) {
            throw new IllegalArgumentException("Unable to serialize event", ex);
        }
    }
}
