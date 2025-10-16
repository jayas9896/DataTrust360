package com.datatrust360.storage;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

/**
 * SQL entity representing a tenant organization.
 *
 * <p>Importance: Tenants are the core isolation boundary for multi-tenant data.</p>
 * <p>Alternatives: Store tenant data in NoSQL, but relational constraints aid governance.</p>
 */
@Entity
public class Tenant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String industry;

    /**
     * Returns the tenant primary key.
     *
     * <p>Importance: Used to join tenant data with accounts and policies.</p>
     * <p>Alternatives: Use a UUID string, but numeric IDs simplify relational joins.</p>
     */
    public Long getId() { return id; }
    /**
     * Sets the tenant primary key.
     *
     * <p>Importance: Required for ORM persistence and entity identity.</p>
     * <p>Alternatives: Generate IDs client-side, but database-generated IDs reduce collisions.</p>
     */
    public void setId(Long id) { this.id = id; }

    /**
     * Returns the tenant display name.
     *
     * <p>Importance: Used for admin dashboards and audit trails.</p>
     * <p>Alternatives: Use only IDs, but names improve human readability.</p>
     */
    public String getName() { return name; }
    /**
     * Sets the tenant display name.
     *
     * <p>Importance: Enables tenant identification in UI and reports.</p>
     * <p>Alternatives: Derive from external CRM, but local storage simplifies MVP.</p>
     */
    public void setName(String name) { this.name = name; }

    /**
     * Returns the tenant industry classification.
     *
     * <p>Importance: Drives policy defaults and analytics segmentation.</p>
     * <p>Alternatives: Keep industry in metadata JSON, but structured storage improves queries.</p>
     */
    public String getIndustry() { return industry; }
    /**
     * Sets the tenant industry classification.
     *
     * <p>Importance: Supports compliance and reporting use cases.</p>
     * <p>Alternatives: Infer from activity, but explicit values are more reliable.</p>
     */
    public void setIndustry(String industry) { this.industry = industry; }
}
