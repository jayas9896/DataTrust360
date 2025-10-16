package com.datatrust360.storage;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.Instant;

/**
 * SQL entity capturing security and operational audit events.
 *
 * <p>Importance: Audit logs provide traceability for compliance and incident response.</p>
 * <p>Alternatives: Log only to files, but persisted audit records are queryable and durable.</p>
 */
@Entity
public class AuditLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long tenantId;
    private String actor;
    private String action;
    private Instant occurredAt;

    /**
     * Returns the audit log primary key.
     *
     * <p>Importance: Identifies audit records for retrieval.</p>
     * <p>Alternatives: Use composite keys, but a surrogate key is simpler.</p>
     */
    public Long getId() { return id; }
    /**
     * Sets the audit log primary key.
     *
     * <p>Importance: Required for ORM identity handling.</p>
     * <p>Alternatives: Client-generated IDs, but DB-generated IDs reduce collision risk.</p>
     */
    public void setId(Long id) { this.id = id; }

    /**
     * Returns the tenant ID associated with this audit event.
     *
     * <p>Importance: Enables tenant-scoped audit queries.</p>
     * <p>Alternatives: Store in a separate mapping table, but direct ID is simpler.</p>
     */
    public Long getTenantId() { return tenantId; }
    /**
     * Sets the tenant ID associated with this audit event.
     *
     * <p>Importance: Ensures audit entries are isolated by tenant.</p>
     * <p>Alternatives: Use global logs only, but that complicates tenant compliance reporting.</p>
     */
    public void setTenantId(Long tenantId) { this.tenantId = tenantId; }

    /**
     * Returns the actor that performed the action.
     *
     * <p>Importance: Tracks who initiated security-relevant changes.</p>
     * <p>Alternatives: Store only system actions, but actor identity is essential for audits.</p>
     */
    public String getActor() { return actor; }
    /**
     * Sets the actor that performed the action.
     *
     * <p>Importance: Supports accountability in audit trails.</p>
     * <p>Alternatives: Derive from auth logs, but storing inline reduces joins.</p>
     */
    public void setActor(String actor) { this.actor = actor; }

    /**
     * Returns the action description.
     *
     * <p>Importance: Describes the operation for compliance and debugging.</p>
     * <p>Alternatives: Use numeric codes only, but text improves readability.</p>
     */
    public String getAction() { return action; }
    /**
     * Sets the action description.
     *
     * <p>Importance: Ensures audit events are human-interpretable.</p>
     * <p>Alternatives: Use structured enums, but a string allows flexibility across domains.</p>
     */
    public void setAction(String action) { this.action = action; }

    /**
     * Returns the timestamp when the action occurred.
     *
     * <p>Importance: Supports time-based audit analysis and incident timelines.</p>
     * <p>Alternatives: Use ingestion time only, but recorded occurrence time is more precise.</p>
     */
    public Instant getOccurredAt() { return occurredAt; }
    /**
     * Sets the timestamp when the action occurred.
     *
     * <p>Importance: Preserves accurate audit chronology.</p>
     * <p>Alternatives: Populate on write, but caller-provided time supports external events.</p>
     */
    public void setOccurredAt(Instant occurredAt) { this.occurredAt = occurredAt; }
}
