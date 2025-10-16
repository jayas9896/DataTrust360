package com.datatrust360.ingest.rest;

import com.datatrust360.common.EventEnvelope;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.kafka.core.KafkaTemplate;
import software.amazon.awssdk.services.kinesis.KinesisClient;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * Unit tests for REST ingestion service serialization behavior.
 *
 * <p>Importance: Ensures serialization failures surface as expected exceptions.</p>
 * <p>Alternatives: Use integration tests only, but unit tests isolate failure cases faster.</p>
 */
class IngestServiceImplTest {

    /**
     * Verifies invalid JSON serialization triggers an IllegalArgumentException.
     *
     * <p>Importance: Ensures upstream callers can detect and handle serialization errors.</p>
     * <p>Alternatives: Swallow errors and log only, but that hides failures from callers.</p>
     */
    @Test
    void publishToKafkaRejectsInvalidJson() {
        KafkaTemplate<String, String> kafkaTemplate = Mockito.mock(KafkaTemplate.class);
        KinesisClient kinesisClient = Mockito.mock(KinesisClient.class);
        ObjectMapper objectMapper = Mockito.mock(ObjectMapper.class);
        IngestServiceImpl service = new IngestServiceImpl(kafkaTemplate, kinesisClient, objectMapper, "topic", "stream");

        EventEnvelope envelope = new EventEnvelope();
        envelope.setTenantId("t-1");

        assertThatThrownBy(() -> service.publishToKafka(envelope))
            .isInstanceOf(IllegalArgumentException.class);
    }
}
