package com.datatrust360.storage;

import com.datatrust360.common.AuditLogRequest;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Unit tests for storage controller audit persistence.
 *
 * <p>Importance: Ensures audit requests map correctly to persisted entities.</p>
 * <p>Alternatives: Use integration tests only, but unit tests isolate mapping logic.</p>
 */
class StorageControllerTest {

    /**
     * Verifies tenant ID strings are parsed into numeric IDs for audit logs.
     *
     * <p>Importance: Aligns audit logs with SQL tenant schema.</p>
     * <p>Alternatives: Store tenant ID strings, but that diverges from existing schema.</p>
     */
    @Test
    void createAuditParsesTenantId() {
        TenantRepository tenantRepository = Mockito.mock(TenantRepository.class);
        EventDocumentRepository eventRepository = Mockito.mock(EventDocumentRepository.class);
        AuditLogRepository auditLogRepository = Mockito.mock(AuditLogRepository.class);
        StorageController controller = new StorageController(tenantRepository, eventRepository, auditLogRepository);

        AuditLogRequest request = new AuditLogRequest();
        request.setTenantId("42");
        request.setActor("tester");
        request.setAction("INSIGHT_CREATED");

        controller.createAudit(request);

        ArgumentCaptor<AuditLog> captor = ArgumentCaptor.forClass(AuditLog.class);
        Mockito.verify(auditLogRepository).save(captor.capture());
        assertThat(captor.getValue().getTenantId()).isEqualTo(42L);
    }
}
