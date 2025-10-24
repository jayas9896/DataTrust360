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

    private final OpenAiInsightService insightService;

    /**
     * Creates the scoring worker with insight generation dependency.
     *
     * <p>Importance: Allows optional insight generation after scoring.</p>
     * <p>Alternatives: Trigger insights in a separate service, but this keeps MVP simpler.</p>
     */
    public AnomalyScoringWorker(OpenAiInsightService insightService) {
        this.insightService = insightService;
    }

    /**
     * Scores a payload from the anomaly queue and triggers optional insights.
     *
     * <p>Importance: Couples scoring with insight generation for analyst workflows.</p>
     * <p>Alternatives: Trigger insights in a separate pipeline, but this keeps the MVP simpler.</p>
     */
    @RabbitListener(queues = RabbitConfig.ANOMALY_QUEUE)
    public void score(String payload) {
        // TODO: call ML/anomaly scoring service and persist results.
        insightService.generateInsight("unknown", payload);
        System.out.println("Scoring payload length=" + payload.length());
    }
}
