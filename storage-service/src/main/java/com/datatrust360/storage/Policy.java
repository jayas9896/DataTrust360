package com.datatrust360.storage;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

/**
 * SQL entity representing tenant policies for routing and compliance.
 *
 * <p>Importance: Policies define how events are processed and governed per tenant.</p>
 * <p>Alternatives: Hardcode policy rules in code, but stored policies enable dynamic updates.</p>
 */
@Entity
public class Policy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long tenantId;
    private String name;
    private String definition;

    /**
     * Returns the policy primary key.
     *
     * <p>Importance: Needed for persistence and referencing policy configurations.</p>
     * <p>Alternatives: Use policy name as a key, but IDs allow renaming without migration.</p>
     */
    public Long getId() { return id; }
    /**
     * Sets the policy primary key.
     *
     * <p>Importance: Enables ORM identity and relational constraints.</p>
     * <p>Alternatives: Client-generated UUIDs, but DB-generated IDs are simpler here.</p>
     */
    public void setId(Long id) { this.id = id; }

    /**
     * Returns the tenant ID this policy belongs to.
     *
     * <p>Importance: Enforces tenant isolation for governance rules.</p>
     * <p>Alternatives: Use a separate join table, but direct ID simplifies MVP.</p>
     */
    public Long getTenantId() { return tenantId; }
    /**
     * Sets the tenant ID this policy belongs to.
     *
     * <p>Importance: Ensures policies remain scoped to their tenant.</p>
     * <p>Alternatives: Infer from context, but explicit data improves auditability.</p>
     */
    public void setTenantId(Long tenantId) { this.tenantId = tenantId; }

    /**
     * Returns the policy name.
     *
     * <p>Importance: Names support human-readable policy management.</p>
     * <p>Alternatives: Use only IDs, but names improve usability.</p>
     */
    public String getName() { return name; }
    /**
     * Sets the policy name.
     *
     * <p>Importance: Allows operators to manage policies by name.</p>
     * <p>Alternatives: Use tags only, but names provide clear intent.</p>
     */
    public void setName(String name) { this.name = name; }

    /**
     * Returns the policy definition (e.g., JSON or DSL).
     *
     * <p>Importance: Captures the rules applied to ingestion and alerts.</p>
     * <p>Alternatives: Store rules as code, but data-driven policies are more flexible.</p>
     */
    public String getDefinition() { return definition; }
    /**
     * Sets the policy definition (e.g., JSON or DSL).
     *
     * <p>Importance: Enables runtime policy updates without redeployments.</p>
     * <p>Alternatives: Use static config files, but DB storage supports per-tenant changes.</p>
     */
    public void setDefinition(String definition) { this.definition = definition; }
}
