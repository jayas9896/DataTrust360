# Demo Scenarios

## Scenario 1: Public Landing and Auth Gateway
1) Start services.
2) Visit `http://localhost:8080/public/landing`.
3) Verify public response without authentication.

## Scenario 2: REST Ingestion -> Kafka -> RabbitMQ
1) Send REST event:

```bash
curl -X POST http://localhost:8081/api/v1/events \
  -H "Content-Type: application/json" \
  -d '{"tenantId":"1","source":"agent","schemaVersion":"v1","receivedAt":"2025-01-01T00:00:00Z","payload":{"latencyMs":120}}'
```

2) Verify processing worker logs show scoring.

## Scenario 3: Partner Ingestion -> Kinesis -> Worker
1) Initialize LocalStack Kinesis:

```bash
powershell -ExecutionPolicy Bypass -File scripts/init-localstack.ps1
```

2) Send partner event:

```bash
curl -X POST http://localhost:8081/api/v1/partners/events \
  -H "Content-Type: application/json" \
  -d '{"tenantId":"2","source":"partner","schemaVersion":"v1","receivedAt":"2025-01-01T00:00:00Z","payload":{"status":"ok"}}'
```

3) Verify processing worker logs show Kinesis record processing.

## Scenario 4: gRPC Bulk Ingestion

```bash
grpcurl -plaintext \
  -d '{"events": [{"tenant_id": "1", "source": "agent", "schema_version": "v1", "received_at": "2025-01-01T00:00:00Z", "json_payload": "{\\"latencyMs\\":120}"}]}' \
  localhost:9090 BulkIngestService/IngestBatch
```

## Scenario 5: Storage Audit Logs

```bash
curl -X POST http://localhost:8083/api/v1/storage/audit \
  -H "Content-Type: application/json" \
  -d '{"tenantId":"1","actor":"openai","action":"INSIGHT_CREATED","occurredAt":"2025-01-01T00:00:00Z","details":"Example insight"}'
```

## Scenario 6: Observability
1) Access Prometheus `http://localhost:9090`.
2) Access Grafana `http://localhost:3000`.
