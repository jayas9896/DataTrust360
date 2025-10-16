package com.datatrust360.storage;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Unit tests for MongoDB event document model.
 *
 * <p>Importance: Validates basic object behavior for storage entities.</p>
 * <p>Alternatives: Rely on integration tests only, but unit tests are faster and focused.</p>
 */
class EventDocumentTest {

    /**
     * Verifies tenant ID is stored and returned correctly.
     *
     * <p>Importance: Ensures tenant isolation metadata is preserved.</p>
     * <p>Alternatives: Skip entity tests, but regressions are easier to catch early.</p>
     */
    @Test
    void eventDocumentStoresPayload() {
        EventDocument doc = new EventDocument();
        doc.setTenantId("t-1");
        assertThat(doc.getTenantId()).isEqualTo("t-1");
    }
}
