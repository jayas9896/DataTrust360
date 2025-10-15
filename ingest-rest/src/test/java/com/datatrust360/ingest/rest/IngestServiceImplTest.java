package com.datatrust360.ingest.rest;

import com.datatrust360.common.EventEnvelope;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.kafka.core.KafkaTemplate;
import software.amazon.awssdk.services.kinesis.KinesisClient;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class IngestServiceImplTest {

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
