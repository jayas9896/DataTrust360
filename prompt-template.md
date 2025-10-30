# Project Super Prompt Template

Use this template to create a new enterprise-grade project with consistent engineering standards. Replace placeholders wrapped in [ ] or { }.

SUPER PROMPT BEGIN

You are helping me design, scaffold, and implement an enterprise-grade engineering project that uses ALL the following technologies in a cohesive, realistic, and justifiable manner:
Spring Boot, Spring MVC, Spring Security (public access + role-based access + OAuth2 + SAML), Hibernate, SQL, NoSQL, REST, Swagger/OpenAPI, gRPC, Kafka, Kinesis, RabbitMQ, JUnit, Git, Maven, Docker, Grafana, Prometheus, Local Kubernetes, UML for architecture diagrams, and optionally OpenAI APIs.

Project Concept (Do Not Change Structure):
"[PROJECT_NAME] - [ONE_LINE_DESCRIPTION]"

Idea Placeholders:
- Domain: [DOMAIN]
- Primary users: [PRIMARY_USERS]
- Core capabilities: [KEY_CAPABILITIES]
- Data sources: [DATA_SOURCES]
- Anomaly/insight goal: [ANOMALY_GOAL]
- Tenant identifier format: [TENANT_ID_FORMAT]

Example placeholder:
"[PROJECT_NAME] - A secure, multi-tenant [DOMAIN] platform that [KEY_CAPABILITIES]."

Non-Negotiable Requirements (Mandatory):

- Do not treat this as a toy app.
- Architecture must justify every technology choice.
- Spring Security must demonstrate:
  - unauthenticated public endpoints
  - role-based restricted endpoints
  - OAuth2 login
  - SAML SSO federation
- Kafka + Kinesis must have clearly different ingestion roles.
- RabbitMQ used for async workloads (e.g., anomaly scoring or reports).
- SQL used via Hibernate for tenants, accounts, RBAC, policies, audit logs.
- NoSQL used for event payloads + dynamic schemas.
- gRPC used for bulk ingestion.
- Swagger/OpenAPI used to document REST APIs.
- JUnit for service and pipeline tests.
- Deployment must use Docker + local Kubernetes.
- Monitoring must use Prometheus + Grafana dashboards.
- UML diagrams for architecture, domain, pipelines, and authentication flows.
- Git + Maven for versioning + build.

Delivery Format (Mandatory):

- UML diagrams (text or mermaid acceptable)
- High-level system architecture
- Technology justification
- Maven multi-module structure
- Spring Security configuration examples
- REST + gRPC API + Swagger examples
- Kafka + Kinesis pipeline design
- RabbitMQ job processor design
- SQL + NoSQL schema definitions
- JUnit testing strategy
- Docker + Kubernetes deployment scripts
- Observability integration (Prometheus, Grafana)
- OpenAI insight feature (prompting + flows)
- Final README for users + demo scenarios

Engineering Standards (Mandatory):

- Javadoc is mandatory for every class, interface, and method. Each Javadoc must include:
  - A short summary.
  - An "Importance" sentence describing why the element matters.
  - An "Alternatives" sentence describing a reasonable alternative when applicable.
- Checkstyle must enforce Javadoc presence for classes and methods.
- A sequential commit log is mandatory:
  - Every commit must include a new entry in `docs/commit-log.md` using the CL-XXXX format.
  - Every commit must include a detailed doc in `docs/change-log-detailed/CL-XXXX.md`.
  - Use the templates in `docs/coding-standards.md`.
- Keep documentation up-to-date with code changes (UML, schemas, flows, runbook, demo).

Process Rules (Mandatory):

- Generate artifacts incrementally but cohesively, ensuring the system feels real, complete, and enterprise-credible.
- Prefer minimal, justified changes; explain why each component exists.
- Keep all examples runnable and consistent with configuration.
- Avoid destructive commands and never remove user work unless explicitly asked.
- Maintain ASCII text unless the file already uses Unicode.

Project Setup Placeholders:

- Repo directory name: [REPO_DIR_NAME]
- Package root: [JAVA_PACKAGE]
- Maven groupId: [MAVEN_GROUP_ID]
- Maven artifactId: [MAVEN_ARTIFACT_ID]
- Service ports: [SERVICE_PORTS]
- Environments: [ENVIRONMENTS]

Non-Functional Requirements:

- Security: least privilege, public endpoints only where required.
- Reliability: asynchronous processing for heavy workloads.
- Observability: metrics for throughput, latency, queue depth.
- Scalability: partitioning keys and stream separation documented.
- Compliance: audit logging for security and insights.

Definition of Done:

- All mandatory technologies are used and justified.
- All endpoints have Swagger/OpenAPI or gRPC docs.
- UML diagrams cover architecture, domain, pipeline, and auth flows.
- Docker and Kubernetes manifests run locally.
- Prometheus scrapes metrics and Grafana dashboards exist.
- Tests cover critical services and pipelines.
- README includes setup, usage, and demo steps.
- Javadoc and Checkstyle pass.
- Commit logs are updated per CL-XXXX templates.

SUPER PROMPT END

Notes:
- Replace all placeholders before use.
- Keep this template in the repo and reuse for future projects.
