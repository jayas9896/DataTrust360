package com.datatrust360.storage;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class EventDocumentTest {

    @Test
    void eventDocumentStoresPayload() {
        EventDocument doc = new EventDocument();
        doc.setTenantId("t-1");
        assertThat(doc.getTenantId()).isEqualTo("t-1");
    }
}
