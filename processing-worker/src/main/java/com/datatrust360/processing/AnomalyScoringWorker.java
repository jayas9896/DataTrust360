package com.datatrust360.processing;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * Worker that processes anomaly scoring jobs from RabbitMQ.
 *
 * <p>Importance: Separates CPU-heavy scoring from ingestion to keep pipelines responsive.</p>
 * <p>Alternatives: Use synchronous scoring, but that would slow down ingestion throughput.</p>
 */
@Component
public class AnomalyScoringWorker {

    /**
     * Scores a payload from the anomaly queue.
     *
     * <p>Importance: Represents the async job boundary for ML or rules-based scoring.</p>
     * <p>Alternatives: Use a separate scoring microservice, but this keeps the MVP simpler.</p>
     */
    @RabbitListener(queues = RabbitConfig.ANOMALY_QUEUE)
    public void score(String payload) {
        // TODO: call ML/anomaly scoring service and persist results.
        System.out.println("Scoring payload length=" + payload.length());
    }
}
