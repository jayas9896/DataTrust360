package com.datatrust360.storage;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * JPA repository for audit log records.
 *
 * <p>Importance: Provides CRUD access to audit events for compliance workflows.</p>
 * <p>Alternatives: Use manual JDBC queries, but Spring Data reduces boilerplate.</p>
 */
public interface AuditLogRepository extends JpaRepository<AuditLog, Long> {
}
