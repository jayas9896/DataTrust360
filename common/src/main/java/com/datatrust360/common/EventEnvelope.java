package com.datatrust360.common;

import java.time.Instant;
import java.util.Map;

/**
 * Normalized wrapper for operational events across REST and gRPC ingestion paths.
 *
 * <p>Importance: Provides a single schema so downstream streaming, persistence, and analytics
 * components can rely on consistent metadata and payload handling.</p>
 * <p>Alternatives: Use multiple transport-specific DTOs, but that fragments the pipeline
 * and complicates validation and storage mappings.</p>
 */
public class EventEnvelope {
    private String tenantId;
    private String source;
    private String schemaVersion;
    private Instant receivedAt;
    private Map<String, Object> payload;

    /**
     * Returns the tenant identifier that owns the event.
     *
     * <p>Importance: Tenant context is required for multi-tenant isolation and routing.</p>
     * <p>Alternatives: Derive tenant from auth context only, but that does not support
     * fully decoupled ingestion pipelines.</p>
     */
    public String getTenantId() { return tenantId; }
    /**
     * Sets the tenant identifier that owns the event.
     *
     * <p>Importance: Ensures downstream routing and authorization checks have tenant context.</p>
     * <p>Alternatives: Populate from middleware only, but that reduces flexibility
     * for partner bulk ingestion.</p>
     */
    public void setTenantId(String tenantId) { this.tenantId = tenantId; }

    /**
     * Returns the event source (service, agent, or integration).
     *
     * <p>Importance: Source is required for lineage and pipeline observability.</p>
     * <p>Alternatives: Infer from headers, but explicit source improves auditability.</p>
     */
    public String getSource() { return source; }
    /**
     * Sets the event source (service, agent, or integration).
     *
     * <p>Importance: Enables downstream classification and alerting.</p>
     * <p>Alternatives: Compute server-side only, but that can lose partner context.</p>
     */
    public void setSource(String source) { this.source = source; }

    /**
     * Returns the schema version for payload interpretation.
     *
     * <p>Importance: Schema versioning prevents breaking changes in analytics.</p>
     * <p>Alternatives: Embed version in payload fields, but that is harder to validate.</p>
     */
    public String getSchemaVersion() { return schemaVersion; }
    /**
     * Sets the schema version for payload interpretation.
     *
     * <p>Importance: Explicit versioning allows safe evolution across tenants.</p>
     * <p>Alternatives: Use implicit latest version only, but that breaks backward compatibility.</p>
     */
    public void setSchemaVersion(String schemaVersion) { this.schemaVersion = schemaVersion; }

    /**
     * Returns the timestamp when the event was received.
     *
     * <p>Importance: Supports ordering, SLAs, and time-based analytics.</p>
     * <p>Alternatives: Use ingestion time only, but that can differ from source truth.</p>
     */
    public Instant getReceivedAt() { return receivedAt; }
    /**
     * Sets the timestamp when the event was received.
     *
     * <p>Importance: Ensures time-based processing and storage consistency.</p>
     * <p>Alternatives: Auto-populate on storage write, but that loses upstream timing.</p>
     */
    public void setReceivedAt(Instant receivedAt) { this.receivedAt = receivedAt; }

    /**
     * Returns the dynamic payload content.
     *
     * <p>Importance: Holds tenant-specific or evolving data without rigid schemas.</p>
     * <p>Alternatives: Use rigid typed fields, but that reduces flexibility for new event types.</p>
     */
    public Map<String, Object> getPayload() { return payload; }
    /**
     * Sets the dynamic payload content.
     *
     * <p>Importance: Allows ingestion of unstructured or semi-structured data.</p>
     * <p>Alternatives: Store as a raw JSON string, but that complicates downstream queries.</p>
     */
    public void setPayload(Map<String, Object> payload) { this.payload = payload; }
}
