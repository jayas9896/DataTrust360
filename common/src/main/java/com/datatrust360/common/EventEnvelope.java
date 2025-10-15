package com.datatrust360.common;

import java.time.Instant;
import java.util.Map;

public class EventEnvelope {
    private String tenantId;
    private String source;
    private String schemaVersion;
    private Instant receivedAt;
    private Map<String, Object> payload;

    public String getTenantId() { return tenantId; }
    public void setTenantId(String tenantId) { this.tenantId = tenantId; }

    public String getSource() { return source; }
    public void setSource(String source) { this.source = source; }

    public String getSchemaVersion() { return schemaVersion; }
    public void setSchemaVersion(String schemaVersion) { this.schemaVersion = schemaVersion; }

    public Instant getReceivedAt() { return receivedAt; }
    public void setReceivedAt(Instant receivedAt) { this.receivedAt = receivedAt; }

    public Map<String, Object> getPayload() { return payload; }
    public void setPayload(Map<String, Object> payload) { this.payload = payload; }
}
