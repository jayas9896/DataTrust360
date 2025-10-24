package com.datatrust360.processing;

import com.datatrust360.common.AuditLogRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.time.Instant;

/**
 * Generates natural language insights using the OpenAI API.
 *
 * <p>Importance: Converts raw anomaly signals into readable explanations for analysts.</p>
 * <p>Alternatives: Use rule-based templates only, but LLMs provide richer summaries.</p>
 */
@Service
public class OpenAiInsightService {

    private final RestClient restClient;
    private final StorageClient storageClient;
    private final boolean enabled;
    private final String model;

    /**
     * Creates the insight service with API client configuration.
     *
     * <p>Importance: Keeps external API configuration centralized.</p>
     * <p>Alternatives: Hardcode model and URLs, but configuration keeps environments flexible.</p>
     */
    public OpenAiInsightService(
        RestClient openAiRestClient,
        StorageClient storageClient,
        @Value("${processing.openai.enabled:false}") boolean enabled,
        @Value("${processing.openai.model:gpt-4o-mini}") String model
    ) {
        this.restClient = openAiRestClient;
        this.storageClient = storageClient;
        this.enabled = enabled;
        this.model = model;
    }

    /**
     * Generates and stores an insight for a payload if OpenAI is enabled.
     *
     * <p>Importance: Adds analyst-friendly context to anomaly signals.</p>
     * <p>Alternatives: Skip insight generation, but this reduces dashboard usefulness.</p>
     */
    public void generateInsight(String tenantId, String payload) {
        if (!enabled) {
            return;
        }
        String insight = callOpenAi(payload);
        AuditLogRequest request = new AuditLogRequest();
        request.setTenantId(tenantId);
        request.setActor("openai");
        request.setAction("INSIGHT_CREATED");
        request.setOccurredAt(Instant.now());
        request.setDetails(insight);
        storageClient.persistAudit(request);
    }

    /**
     * Calls the OpenAI API with a minimal prompt and returns the response text.
     *
     * <p>Importance: Encapsulates API interaction to keep workers focused on workflow.</p>
     * <p>Alternatives: Use the official SDK, but HTTP keeps dependencies light.</p>
     */
    private String callOpenAi(String payload) {
        OpenAiRequest request = new OpenAiRequest(model,
            "Summarize anomalies from this payload in 2 sentences: " + payload);
        OpenAiResponse response = restClient.post()
            .uri("/v1/responses")
            .body(request)
            .retrieve()
            .body(OpenAiResponse.class);
        if (response == null || response.outputText() == null) {
            return "No insight generated";
        }
        return response.outputText();
    }

    /**
     * Request model for OpenAI responses API.
     *
     * <p>Importance: Minimal request shape for LLM calls.</p>
     * <p>Alternatives: Use chat completions API, but responses API is simpler.</p>
     */
    public record OpenAiRequest(String model, String input) {
    }

    /**
     * Response model for OpenAI responses API.
     *
     * <p>Importance: Maps the text output for downstream storage.</p>
     * <p>Alternatives: Parse raw JSON, but typed responses improve safety.</p>
     */
    public record OpenAiResponse(String output_text) {
        /**
         * Returns the output text field in a Java-friendly format.
         *
         * <p>Importance: Avoids leaking API-specific naming into business logic.</p>
         * <p>Alternatives: Access the raw field directly, but this method improves readability.</p>
         */
        public String outputText() {
            return output_text;
        }
    }
}
