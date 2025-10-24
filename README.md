# DataTrust360

Enterprise-grade, multi-tenant event ingestion and analytics platform.

## High-Level Architecture

- Public landing + auth gateway with OAuth2 and SAML federation.
- REST and gRPC ingestion services for structured/unstructured events.
- Kafka for internal real-time event streams; Kinesis for partner/edge ingest.
- RabbitMQ for async scoring and report jobs.
- SQL (PostgreSQL + Hibernate) for tenants, accounts, RBAC, policies, audit logs.
- NoSQL (MongoDB) for event payloads and dynamic schemas.
- Prometheus + Grafana for metrics.
- Docker + local Kubernetes for deployment.

See `docs/diagrams.md` for UML and flows.
See `docs/agile-plan.md` for Agile/Scrum planning.
See `docs/coding-standards.md` for the mandatory Javadoc policy.
See `docs/commit-log.md` for sequential commit learning notes.
See `docs/api-examples.md` for REST and gRPC usage examples.

## Technology Justification

- Spring Boot + MVC: consistent service scaffolding and REST APIs.
- Spring Security: public endpoints, RBAC, OAuth2 login, and SAML SSO.
- Hibernate + SQL: strong consistency and relational constraints for identity and policy data.
- MongoDB: flexible event payload storage with evolving schemas.
- Kafka: low-latency internal event pipeline and replayable topics.
- Kinesis: partner/edge ingestion path with independent scaling and AWS integration.
- RabbitMQ: durable job queue for anomaly scoring and report generation.
- gRPC: efficient bulk ingestion with streaming support.
- Swagger/OpenAPI: consumer-friendly REST API documentation.
- JUnit: service-level unit tests and pipeline validation.
- Prometheus + Grafana: standardized observability.
- Docker + Kubernetes: local cluster parity with production patterns.

## Maven Multi-Module Layout

- `common`: shared DTOs.
- `auth-gateway`: public + secure API gateway, OAuth2 + SAML.
- `ingest-rest`: REST ingestion and Kafka/Kinesis publishers.
- `ingest-grpc`: gRPC bulk ingestion to Kafka.
- `processing-worker`: Kafka consumers and RabbitMQ workers.
- `storage-service`: SQL + NoSQL persistence and storage APIs.

## Security Highlights

- Public endpoints: `/public/**`, Swagger and health.
- Role-protected endpoints: `/api/admin/**` (ADMIN only).
- OAuth2 login: configured in `auth-gateway/src/main/resources/application.yml`.
- SAML SSO: placeholder relying party config in the same file.

## REST API (Swagger)

- `POST /api/v1/events` -> Kafka topic `dt360.events.raw`
- `POST /api/v1/partners/events` -> Kinesis stream `dt360-partner-ingest`
- `GET /api/v1/storage/tenants`
- `POST /api/v1/storage/events`

Swagger UI:

- `http://localhost:8081/swagger-ui/index.html`
- `http://localhost:8083/swagger-ui/index.html`

## gRPC API

Proto: `ingest-grpc/src/main/proto/ingest.proto`

```
service BulkIngestService {
  rpc IngestBatch (IngestBatchRequest) returns (IngestBatchResponse);
}
```

## Kafka + Kinesis Pipeline

- Kafka topics: `dt360.events.raw`, `dt360.events.bulk`
- Kinesis stream: `dt360-partner-ingest`
- Worker consumes raw and bulk Kafka events and polls Kinesis for partner events, then fans out to RabbitMQ queue `dt360.anomaly.scoring`

## RabbitMQ Job Processing

- `processing-worker` pushes scoring jobs to `dt360.anomaly.scoring`
- `AnomalyScoringWorker` simulates scoring and persistence

## SQL + NoSQL Schemas

SQL (PostgreSQL):

```
CREATE TABLE tenant (
  id BIGSERIAL PRIMARY KEY,
  name TEXT,
  industry TEXT
);

CREATE TABLE account (
  id BIGSERIAL PRIMARY KEY,
  email TEXT,
  role TEXT,
  tenant_id BIGINT
);

CREATE TABLE policy (
  id BIGSERIAL PRIMARY KEY,
  tenant_id BIGINT,
  name TEXT,
  definition TEXT
);

CREATE TABLE audit_log (
  id BIGSERIAL PRIMARY KEY,
  tenant_id BIGINT,
  actor TEXT,
  action TEXT,
  occurred_at TIMESTAMP
);
```

MongoDB document:

```
{
  "_id": "...",
  "tenantId": "t-1",
  "source": "agent",
  "schemaVersion": "v1",
  "receivedAt": "2025-01-01T00:00:00Z",
  "payload": {
    "host": "api-1",
    "latencyMs": 120
  }
}
```

## Observability

- Prometheus scrape config: `docker/prometheus.yml`
- Example Grafana dashboard: `docker/grafana-dashboard.json`
- Actuator endpoints expose `/actuator/prometheus`

## Docker and Kubernetes

Dockerfiles are in `docker/`. Kubernetes manifests are in `k8s/`.

Build (example):

```
mvn -q -DskipTests package
```

Run local Kubernetes:

```
kubectl apply -f k8s/
```

## OpenAI Insights (Optional)

- Worker can call OpenAI after anomaly scoring to generate natural language insights.
- Suggested flow in `docs/insights.md`.
- Configure `OPENAI_API_KEY` and enable with `OPENAI_ENABLED=true`.

## Tests

```
mvn -q test
```

## Demo Scenarios

1) Public user visits `/public/landing`.
2) Admin user logs in with OAuth2, accesses `/api/admin/status`.
3) Partner sends bulk events via gRPC; events flow to Kafka.
4) Worker pushes scoring jobs to RabbitMQ.
5) Storage service persists tenant metadata and event payloads.
6) Prometheus scrapes metrics, Grafana shows ingestion throughput.
