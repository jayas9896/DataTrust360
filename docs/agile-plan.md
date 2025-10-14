# Agile/Scrum Plan

## Product Vision
DataTrust360 delivers secure, multi-tenant event ingestion, insights, and anomaly analysis for enterprise operational telemetry.

## Roles
- Product Owner: defines roadmap, prioritizes backlog.
- Scrum Master: removes blockers, enforces cadence.
- Engineering Team: implements services, pipelines, and observability.

## Cadence
- Sprint length: 2 weeks
- Ceremonies: planning (2h), daily standup (15m), review (1h), retro (1h)

## Definition of Done
- Code merged with tests passing
- Security configuration validated for public + RBAC + OAuth2 + SAML
- API docs updated (OpenAPI/gRPC)
- Observability metrics exposed
- Deploy manifests updated

## Backlog (Epics -> Stories)

### Epic 1: Platform Foundation
- Story: Maven multi-module scaffold
- Story: Base Spring Boot services with health endpoints
- Story: Shared DTOs and config standards

### Epic 2: Security & Identity
- Story: Public landing endpoints
- Story: RBAC for admin + viewer
- Story: OAuth2 login integration
- Story: SAML federation integration

### Epic 3: Ingestion Pipelines
- Story: REST ingestion to Kafka
- Story: Partner REST to Kinesis
- Story: gRPC bulk ingestion to Kafka
- Story: Retry and error handling strategy

### Epic 4: Async Processing
- Story: Kafka consumers -> RabbitMQ jobs
- Story: Anomaly scoring worker
- Story: Insight generation flow

### Epic 5: Storage Layer
- Story: SQL schema for tenants/accounts/policies/audit
- Story: NoSQL payload storage
- Story: Storage API endpoints

### Epic 6: Observability & Ops
- Story: Prometheus scraping
- Story: Grafana dashboard
- Story: Docker compose dev stack
- Story: Kubernetes manifests

## Release Plan (High-Level)
- Release 0.1: Scaffolding + baseline pipelines
- Release 0.2: Security + storage services
- Release 0.3: Async processing + insights
- Release 0.4: Observability + Kubernetes

## Risks
- OAuth2/SAML provider integration complexity
- Local Kinesis emulation differences (LocalStack)
- Multi-tenant isolation enforcement

## Metrics
- Ingestion throughput (events/sec)
- Processing latency (p95)
- Auth success/failure rate
- Queue backlog depth
