package com.datatrust360.common;

import java.time.Instant;

/**
 * Request payload for creating audit log entries in the storage service.
 *
 * <p>Importance: Provides a stable contract for audit logging across services.</p>
 * <p>Alternatives: Use service-specific entities, but shared DTOs reduce coupling issues.</p>
 */
public class AuditLogRequest {
    private String tenantId;
    private String actor;
    private String action;
    private Instant occurredAt;
    private String details;

    /**
     * Returns the tenant identifier for the audit entry.
     *
     * <p>Importance: Supports tenant-scoped audit queries and compliance.</p>
     * <p>Alternatives: Infer tenant from auth context, but explicit IDs support async jobs.</p>
     */
    public String getTenantId() { return tenantId; }

    /**
     * Sets the tenant identifier for the audit entry.
     *
     * <p>Importance: Ensures multi-tenant isolation is preserved in audit logs.</p>
     * <p>Alternatives: Derive from payload context, but explicit values are more reliable.</p>
     */
    public void setTenantId(String tenantId) { this.tenantId = tenantId; }

    /**
     * Returns the actor responsible for the action.
     *
     * <p>Importance: Captures accountability for sensitive operations.</p>
     * <p>Alternatives: Store only system actors, but user attribution is valuable for audits.</p>
     */
    public String getActor() { return actor; }

    /**
     * Sets the actor responsible for the action.
     *
     * <p>Importance: Enables compliance reporting and incident review.</p>
     * <p>Alternatives: Use a separate identity table, but inline values are simple for MVP.</p>
     */
    public void setActor(String actor) { this.actor = actor; }

    /**
     * Returns the action string (e.g., INSIGHT_CREATED).
     *
     * <p>Importance: Standardizes audit semantics for downstream reporting.</p>
     * <p>Alternatives: Use numeric codes only, but strings improve readability.</p>
     */
    public String getAction() { return action; }

    /**
     * Sets the action string for the audit entry.
     *
     * <p>Importance: Ensures consistent action labeling for reporting.</p>
     * <p>Alternatives: Use enums, but strings are flexible across services.</p>
     */
    public void setAction(String action) { this.action = action; }

    /**
     * Returns the timestamp when the action occurred.
     *
     * <p>Importance: Preserves ordering for compliance timelines.</p>
     * <p>Alternatives: Use ingestion time only, but action time is more accurate.</p>
     */
    public Instant getOccurredAt() { return occurredAt; }

    /**
     * Sets the timestamp when the action occurred.
     *
     * <p>Importance: Enables time-based audit queries.</p>
     * <p>Alternatives: Fill on server, but client-supplied timestamps reflect processing time.</p>
     */
    public void setOccurredAt(Instant occurredAt) { this.occurredAt = occurredAt; }

    /**
     * Returns additional details for the audit entry.
     *
     * <p>Importance: Captures context such as generated insights or metadata.</p>
     * <p>Alternatives: Store details in a separate table, but inline fields simplify storage.</p>
     */
    public String getDetails() { return details; }

    /**
     * Sets additional details for the audit entry.
     *
     * <p>Importance: Keeps audit entries self-contained for review.</p>
     * <p>Alternatives: Omit details, but that limits forensic value.</p>
     */
    public void setDetails(String details) { this.details = details; }
}
