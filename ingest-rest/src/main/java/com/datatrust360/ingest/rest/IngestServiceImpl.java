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

@Service
public class IngestServiceImpl implements IngestService {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final KinesisClient kinesisClient;
    private final ObjectMapper objectMapper;
    private final String kafkaTopic;
    private final String kinesisStream;

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

    @Override
    public void publishToKafka(EventEnvelope envelope) {
        kafkaTemplate.send(kafkaTopic, envelope.getTenantId(), toJson(envelope));
    }

    @Override
    public void publishToKinesis(EventEnvelope envelope) {
        PutRecordRequest request = PutRecordRequest.builder()
            .streamName(kinesisStream)
            .partitionKey(envelope.getTenantId())
            .data(SdkBytes.fromUtf8String(toJson(envelope)))
            .build();
        kinesisClient.putRecord(request);
    }

    private String toJson(EventEnvelope envelope) {
        try {
            return objectMapper.writeValueAsString(envelope);
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException("Unable to serialize event", e);
        }
    }
}
