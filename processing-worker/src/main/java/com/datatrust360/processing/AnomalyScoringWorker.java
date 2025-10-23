package com.datatrust360.processing;

import com.datatrust360.common.EventEnvelope;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final Logger logger = LoggerFactory.getLogger(AnomalyScoringWorker.class);

    private final OpenAiInsightService insightService;
    private final ObjectMapper objectMapper;

    /**
     * Creates the scoring worker with insight generation dependency.
     *
     * <p>Importance: Allows optional insight generation after scoring.</p>
     * <p>Alternatives: Trigger insights in a separate service, but this keeps MVP simpler.</p>
     */
    public AnomalyScoringWorker(OpenAiInsightService insightService, ObjectMapper objectMapper) {
        this.insightService = insightService;
        this.objectMapper = objectMapper;
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
        insightService.generateInsight(extractTenantId(payload), payload);
        System.out.println("Scoring payload length=" + payload.length());
    }

    /**
     * Extracts tenant ID from a JSON payload if possible.
     *
     * <p>Importance: Ensures insights are associated with the correct tenant for audit logs.</p>
     * <p>Alternatives: Use a fixed tenant, but that would break multi-tenant attribution.</p>
     */
    private String extractTenantId(String payload) {
        try {
            EventEnvelope envelope = objectMapper.readValue(payload, EventEnvelope.class);
            return envelope.getTenantId();
        } catch (Exception ex) {
            logger.warn("Unable to parse tenantId from payload, defaulting to unknown", ex);
            return "unknown";
        }
    }
}
