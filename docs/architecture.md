# Architecture Deep Dive

## Service Responsibilities
- auth-gateway: public endpoints, OAuth2/SAML login, RBAC enforcement.
- ingest-rest: REST ingestion, Kafka internal stream, Kinesis partner stream.
- ingest-grpc: bulk ingestion for high-throughput clients.
- processing-worker: Kafka consumers, Kinesis poller, RabbitMQ scoring, OpenAI insights.
- storage-service: SQL metadata and audit logs, NoSQL event payloads.

## Data Flow
1) Ingestion via REST or gRPC.
2) Kafka for internal low-latency streams.
3) Kinesis for partner/edge ingestion.
4) Worker persists events to NoSQL and enqueues scoring jobs.
5) Optional OpenAI insights written to SQL audit logs.

## Tech Justification (Expanded)
- Kafka vs Kinesis: Kafka for internal, low-latency and replayable pipelines; Kinesis for partner ingest and AWS-managed scalability.
- RabbitMQ: async work queues decoupling ingestion from scoring.
- SQL + Hibernate: relational data with strong constraints for tenants, accounts, policies, and audits.
- MongoDB: flexible event payload storage with evolving schemas.
- gRPC: efficient bulk ingestion.
- OpenAPI: standard REST discoverability.

## Multi-Tenancy
- Tenant ID carried through EventEnvelope and storage documents.
- Audit logs link insights to tenant context.
