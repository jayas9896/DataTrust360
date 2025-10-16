package com.datatrust360.storage;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.util.Map;

/**
 * MongoDB document for storing dynamic event payloads.
 *
 * <p>Importance: Supports flexible schemas for unstructured operational data.</p>
 * <p>Alternatives: Store payloads in SQL JSON columns, but MongoDB offers simpler
 * document storage and indexing for variable fields.</p>
 */
@Document(collection = "event_payloads")
public class EventDocument {

    @Id
    private String id;
    private String tenantId;
    private String source;
    private String schemaVersion;
    private Instant receivedAt;
    private Map<String, Object> payload;

    /**
     * Returns the document identifier.
     *
     * <p>Importance: Allows direct retrieval of payloads by ID.</p>
     * <p>Alternatives: Use composite keys, but a single ID simplifies storage.</p>
     */
    public String getId() { return id; }
    /**
     * Sets the document identifier.
     *
     * <p>Importance: Required for persistence operations.</p>
     * <p>Alternatives: Auto-generate without setters, but explicit setters help tests.</p>
     */
    public void setId(String id) { this.id = id; }

    /**
     * Returns the tenant ID associated with the payload.
     *
     * <p>Importance: Enforces multi-tenant isolation in NoSQL storage.</p>
     * <p>Alternatives: Use per-tenant collections, but a shared collection is simpler to manage.</p>
     */
    public String getTenantId() { return tenantId; }
    /**
     * Sets the tenant ID associated with the payload.
     *
     * <p>Importance: Ensures tenant context is stored with the event.</p>
     * <p>Alternatives: Infer from upstream metadata, but explicit storage aids auditing.</p>
     */
    public void setTenantId(String tenantId) { this.tenantId = tenantId; }

    /**
     * Returns the source of the event payload.
     *
     * <p>Importance: Provides lineage for analytics and filtering.</p>
     * <p>Alternatives: Store only in metadata tables, but co-locating simplifies queries.</p>
     */
    public String getSource() { return source; }
    /**
     * Sets the source of the event payload.
     *
     * <p>Importance: Supports downstream categorization.</p>
     * <p>Alternatives: Derive from headers, but that might not be persisted.</p>
     */
    public void setSource(String source) { this.source = source; }

    /**
     * Returns the schema version for the payload.
     *
     * <p>Importance: Helps interpret dynamic fields over time.</p>
     * <p>Alternatives: Use implicit latest schema, but that risks misinterpretation.</p>
     */
    public String getSchemaVersion() { return schemaVersion; }
    /**
     * Sets the schema version for the payload.
     *
     * <p>Importance: Enables safe schema evolution.</p>
     * <p>Alternatives: Store version within payload only, but separate metadata is clearer.</p>
     */
    public void setSchemaVersion(String schemaVersion) { this.schemaVersion = schemaVersion; }

    /**
     * Returns the time the event was received.
     *
     * <p>Importance: Supports time-based querying and retention policies.</p>
     * <p>Alternatives: Use database insertion time, but that may differ from ingest time.</p>
     */
    public Instant getReceivedAt() { return receivedAt; }
    /**
     * Sets the time the event was received.
     *
     * <p>Importance: Preserves original ingest timing.</p>
     * <p>Alternatives: Compute on write, but that loses source timing accuracy.</p>
     */
    public void setReceivedAt(Instant receivedAt) { this.receivedAt = receivedAt; }

    /**
     * Returns the dynamic payload map.
     *
     * <p>Importance: Stores flexible event data without rigid schemas.</p>
     * <p>Alternatives: Store as a single JSON string, but maps enable query operators.</p>
     */
    public Map<String, Object> getPayload() { return payload; }
    /**
     * Sets the dynamic payload map.
     *
     * <p>Importance: Captures unstructured event data.</p>
     * <p>Alternatives: Flatten into fixed columns, but that reduces flexibility.</p>
     */
    public void setPayload(Map<String, Object> payload) { this.payload = payload; }
}
