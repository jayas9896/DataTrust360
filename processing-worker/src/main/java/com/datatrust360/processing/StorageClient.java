package com.datatrust360.processing;

import com.datatrust360.common.EventEnvelope;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

/**
 * Client for persisting events into the storage service.
 *
 * <p>Importance: Keeps persistence calls centralized and testable for the worker.</p>
 * <p>Alternatives: Write to Mongo directly, but an API boundary preserves service ownership.</p>
 */
@Service
public class StorageClient {

    private final RestClient restClient;

    /**
     * Creates the client with a preconfigured RestClient.
     *
     * <p>Importance: Allows consistent base URL configuration and easier mocking.</p>
     * <p>Alternatives: Build the RestClient here, but configuration should be centralized.</p>
     */
    public StorageClient(RestClient restClient) {
        this.restClient = restClient;
    }

    /**
     * Persists an event payload in the storage service.
     *
     * <p>Importance: Captures raw events for investigation and long-term analysis.</p>
     * <p>Alternatives: Store only scored results, but raw payloads are needed for forensics.</p>
     */
    public void persistEvent(EventEnvelope envelope) {
        restClient.post()
            .uri("/api/v1/storage/events")
            .body(envelope)
            .retrieve()
            .toBodilessEntity();
    }
}
