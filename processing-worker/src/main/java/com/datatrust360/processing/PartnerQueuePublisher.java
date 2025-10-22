package com.datatrust360.processing;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

/**
 * Publishes partner ingestion payloads to the anomaly scoring queue.
 *
 * <p>Importance: Keeps Kinesis ingestion aligned with the same async scoring pipeline.</p>
 * <p>Alternatives: Use separate queues for partner data, but a unified queue simplifies ops.</p>
 */
@Component
public class PartnerQueuePublisher {

    private final RabbitTemplate rabbitTemplate;

    /**
     * Creates the publisher with RabbitMQ dependency.
     *
     * <p>Importance: Enables reuse of queue publishing logic across pollers.</p>
     * <p>Alternatives: Publish directly from poller, but this keeps responsibilities clear.</p>
     */
    public PartnerQueuePublisher(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    /**
     * Enqueues the payload for anomaly scoring.
     *
     * <p>Importance: Keeps scoring async and resilient.</p>
     * <p>Alternatives: Inline scoring, but async queues protect ingestion throughput.</p>
     */
    public void enqueue(String payload) {
        rabbitTemplate.convertAndSend(RabbitConfig.ANOMALY_QUEUE, payload);
    }
}
