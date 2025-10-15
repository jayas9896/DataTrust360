package com.datatrust360.ingest.grpc;

import com.datatrust360.common.EventEnvelope;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.kafka.core.KafkaTemplate;

import java.time.Instant;
import java.util.Map;

@GrpcService
public class IngestGrpcService extends BulkIngestServiceGrpc.BulkIngestServiceImplBase {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;
    private final String kafkaTopic = "dt360.events.bulk";

    public IngestGrpcService(KafkaTemplate<String, String> kafkaTemplate, ObjectMapper objectMapper) {
        this.kafkaTemplate = kafkaTemplate;
        this.objectMapper = objectMapper;
    }

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

    private Instant parseInstant(String value) {
        if (value == null || value.isBlank()) {
            return Instant.now();
        }
        return Instant.parse(value);
    }

    private Map<String, Object> parsePayload(String json) {
        try {
            return objectMapper.readValue(json, new TypeReference<>() {});
        } catch (Exception ex) {
            throw new IllegalArgumentException("Invalid json payload", ex);
        }
    }

    private String toJson(EventEnvelope envelope) {
        try {
            return objectMapper.writeValueAsString(envelope);
        } catch (Exception ex) {
            throw new IllegalArgumentException("Unable to serialize event", ex);
        }
    }
}
