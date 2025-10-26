# Testing Strategy

## Goals
- Validate ingestion, persistence, and async processing flows.
- Catch serialization and schema regressions early.
- Provide interview-ready coverage narratives.

## Unit Tests
- DTO and serialization behavior (e.g., EventEnvelope serialization failures).
- Worker logic (e.g., tenant extraction, queue publishing).
- Storage mapping and validation logic.

## Integration Tests
- Ingest REST to Kafka publish (Testcontainers Kafka).
- Storage API -> PostgreSQL/Mongo persistence (Testcontainers).
- RabbitMQ queue processing with a test worker.

## Contract Tests
- REST API contract snapshots (OpenAPI diff checks).
- gRPC proto compatibility checks.

## Observability Tests
- Smoke tests to ensure `/actuator/prometheus` exposes metrics.
- Grafana dashboard JSON validation.

## Suggested CI Stages
1) Unit tests (`mvn -q test`)
2) Integration tests (`mvn -Pintegration test`)
3) Contract checks (OpenAPI and proto)
4) Docker image build
