# API Examples

## REST Ingestion

```bash
curl -X POST http://localhost:8081/api/v1/events \
  -H "Content-Type: application/json" \
  -d '{
    "tenantId": "1",
    "source": "agent",
    "schemaVersion": "v1",
    "receivedAt": "2025-01-01T00:00:00Z",
    "payload": {"latencyMs": 120}
  }'
```

## Partner REST -> Kinesis

```bash
curl -X POST http://localhost:8081/api/v1/partners/events \
  -H "Content-Type: application/json" \
  -d '{
    "tenantId": "2",
    "source": "partner",
    "schemaVersion": "v1",
    "receivedAt": "2025-01-01T00:00:00Z",
    "payload": {"status": "ok"}
  }'
```

## gRPC Bulk Ingestion

```bash
grpcurl -plaintext \
  -d '{"events": [{"tenant_id": "1", "source": "agent", "schema_version": "v1", "received_at": "2025-01-01T00:00:00Z", "json_payload": "{\\"latencyMs\\":120}"}]}' \
  localhost:9090 BulkIngestService/IngestBatch
```

## Storage API

```bash
curl http://localhost:8083/api/v1/storage/tenants
```

```bash
curl -X POST http://localhost:8083/api/v1/storage/audit \
  -H "Content-Type: application/json" \
  -d '{
    "tenantId": "1",
    "actor": "openai",
    "action": "INSIGHT_CREATED",
    "occurredAt": "2025-01-01T00:00:00Z",
    "details": "Example insight"
  }'
```
