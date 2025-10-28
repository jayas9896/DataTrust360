package com.datatrust360.processing;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.verify;

/**
 * Unit tests for anomaly scoring worker insight attribution.
 *
 * <p>Importance: Ensures tenant context is preserved when generating insights.</p>
 * <p>Alternatives: Rely on integration tests only, but unit tests catch regressions faster.</p>
 */
class AnomalyScoringWorkerTest {

    /**
     * Verifies tenant ID is parsed and used when generating insights.
     *
     * <p>Importance: Prevents misattributed audit logs.</p>
     * <p>Alternatives: Use fixed tenant IDs, but that breaks multi-tenant correctness.</p>
     */
    @Test
    void scoreUsesTenantIdFromPayload() {
        OpenAiInsightService insightService = Mockito.mock(OpenAiInsightService.class);
        ObjectMapper objectMapper = new ObjectMapper();
        AnomalyScoringWorker worker = new AnomalyScoringWorker(insightService, objectMapper);

        String payload = "{\"tenantId\":\"t-1\",\"source\":\"agent\",\"schemaVersion\":\"v1\",\"payload\":{}}";

        worker.score(payload);

        verify(insightService).generateInsight("t-1", payload);
    }
}
