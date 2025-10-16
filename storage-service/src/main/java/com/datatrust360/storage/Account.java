package com.datatrust360.storage;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

/**
 * SQL entity representing a user account in a tenant.
 *
 * <p>Importance: Accounts anchor RBAC and audit trails for tenant users.</p>
 * <p>Alternatives: Use an external IdP only, but local storage supports internal roles.</p>
 */
@Entity
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String role;
    private Long tenantId;

    /**
     * Returns the account primary key.
     *
     * <p>Importance: Identifies the account for persistence and joins.</p>
     * <p>Alternatives: Use email as PK, but numeric IDs simplify relational integrity.</p>
     */
    public Long getId() { return id; }
    /**
     * Sets the account primary key.
     *
     * <p>Importance: Required for ORM identity management.</p>
     * <p>Alternatives: Client-generated IDs, but database-generated IDs are safer.</p>
     */
    public void setId(Long id) { this.id = id; }

    /**
     * Returns the account email.
     *
     * <p>Importance: Email is a common identifier for authentication and notifications.</p>
     * <p>Alternatives: Use username only, but email is more universal.</p>
     */
    public String getEmail() { return email; }
    /**
     * Sets the account email.
     *
     * <p>Importance: Supports user lookup and messaging.</p>
     * <p>Alternatives: Store email in a separate profile table, but MVP keeps it simple.</p>
     */
    public void setEmail(String email) { this.email = email; }

    /**
     * Returns the account role.
     *
     * <p>Importance: Role enables RBAC decisions for sensitive operations.</p>
     * <p>Alternatives: Use attribute-based access control only, but roles are simpler initially.</p>
     */
    public String getRole() { return role; }
    /**
     * Sets the account role.
     *
     * <p>Importance: Enables role assignment and governance.</p>
     * <p>Alternatives: Compute roles dynamically, but stored roles are easier to audit.</p>
     */
    public void setRole(String role) { this.role = role; }

    /**
     * Returns the owning tenant ID.
     *
     * <p>Importance: Associates the account with a tenant for isolation.</p>
     * <p>Alternatives: Use a foreign key relationship, but a simple ID keeps MVP minimal.</p>
     */
    public Long getTenantId() { return tenantId; }
    /**
     * Sets the owning tenant ID.
     *
     * <p>Importance: Ensures accounts are scoped correctly.</p>
     * <p>Alternatives: Store tenant on a join table, but direct ID is simpler for now.</p>
     */
    public void setTenantId(Long tenantId) { this.tenantId = tenantId; }
}
