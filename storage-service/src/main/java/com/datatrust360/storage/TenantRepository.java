package com.datatrust360.storage;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * JPA repository for tenant entities.
 *
 * <p>Importance: Provides CRUD access to tenant records with Spring Data.</p>
 * <p>Alternatives: Use manual DAO implementations, but Spring Data reduces boilerplate.</p>
 */
public interface TenantRepository extends JpaRepository<Tenant, Long> {
}
