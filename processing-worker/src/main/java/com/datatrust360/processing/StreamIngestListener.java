package com.datatrust360.processing;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class StreamIngestListener {

    private final RabbitTemplate rabbitTemplate;

    public StreamIngestListener(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @KafkaListener(topics = "dt360.events.raw", groupId = "processing-worker")
    public void onEvent(String payload) {
        rabbitTemplate.convertAndSend(RabbitConfig.ANOMALY_QUEUE, payload);
    }
}
