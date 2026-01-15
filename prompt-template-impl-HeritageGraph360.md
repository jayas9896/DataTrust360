# HeritageGraph360 Prompt (Filled Example)

SUPER PROMPT BEGIN

You are helping me design, scaffold, and implement an enterprise-grade engineering project that uses ALL the following technologies in a cohesive, realistic, and justifiable manner:
Spring Boot, Spring MVC, Spring Security (public access + role-based access + OAuth2 + SAML), Hibernate, SQL, NoSQL, REST, Swagger/OpenAPI, gRPC, Kafka, Kinesis, RabbitMQ, JUnit, Git, Maven, Docker, Grafana, Prometheus, Local Kubernetes, UML for architecture diagrams, and optionally OpenAI APIs.

Project Concept (Do Not Change Structure):
"HeritageGraph360 - A secure, multi-tenant genealogy and records verification platform that ingests historical sources, unifies identities, and generates auditable lineage insights."

Idea Placeholders:
- Domain: Genealogy, lineage verification, family graph management
- Primary users: Individuals, family administrators, historical archives, research institutions
- Core capabilities: Profile creation/claim, relationship graph, evidence ingestion, conflict resolution, audit trails, insights
- Data sources: User-submitted records, archive imports, partner datasets, scanned documents
- Anomaly/insight goal: Identify duplicate profiles, conflicting records, and lineage gaps
- Tenant identifier format: org-{region}-{id}
- Domain-specific rules:
  - Every person profile requires a unique email or phone number at creation.
  - Profiles start as unclaimed; once claimed, changes require approval or merge workflow.
  - Field-level visibility is controlled by hierarchy and explicit per-person grants.
  - Person records are private by default; public visibility is opt-in only.
  - Merges must preserve provenance and anonymize contributor identities.
  - Relationship status is bi-directional with visible state (green/yellow/orange/red).
  - Sensitive fields (medical history, financial cards) are stored under enhanced encryption and access control.
  - Duplicate detection uses 90% name similarity plus shared email or phone, with a staged merge process.
  - Admin review required for high-impact updates (e.g., caste, name after marriage).
  - Alias names, secondary phones, and emails are supported and can be primary for others.

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
- Agile/Scrum artifacts (backlog, milestones, sprint plans, integration order)

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
- Commit frequently at logical milestones; do not batch unrelated changes.
- Keep documentation up-to-date with code changes (UML, schemas, flows, runbook, demo).

Process Rules (Mandatory):

- Generate artifacts incrementally but cohesively, ensuring the system feels real, complete, and enterprise-credible.
- Prefer minimal, justified changes; explain why each component exists.
- Keep all examples runnable and consistent with configuration.
- Avoid destructive commands and never remove user work unless explicitly asked.
- Maintain ASCII text unless the file already uses Unicode.
- Deliver in Agile/Scrum form with backlog, milestones, sprint plans, and integration order.

Project Setup Placeholders:

- Repo directory name: HeritageGraph360
- Package root: com.heritagegraph360
- Maven groupId: com.heritagegraph360
- Maven artifactId: heritagegraph360-parent
- Service ports: 8080-8086
- Environments: local, dev, qa, prod

Non-Functional Requirements:

- Security: least privilege, public endpoints only where required.
- Reliability: asynchronous processing for heavy workloads.
- Observability: metrics for throughput, latency, queue depth.
- Scalability: partitioning keys and stream separation documented.
- Compliance: audit logging for security and insights.
- Architecture must explicitly address multi-tenancy, failure modes, backpressure, scaling, and security boundary definitions.
- Project domain must be internally consistent and support realistic user flows and data models.

Definition of Done:

- All mandatory technologies are used and justified.
- All endpoints have Swagger/OpenAPI or gRPC docs.
- UML diagrams cover architecture, domain, pipeline, and auth flows.
- Docker and Kubernetes manifests run locally.
- Prometheus scrapes metrics and Grafana dashboards exist.
- Tests cover critical services and pipelines.
- README includes setup, usage, and demo steps.
- Demo scenarios include failure scenarios, security scenarios, and scaling scenarios.
- Javadoc and Checkstyle pass.
- Commit logs are updated per CL-XXXX templates.
- Provide a recruiter-focused one paragraph explanation of the project.

Recruiter-Focused One-Paragraph Summary:

HeritageGraph360 is a secure, enterprise-grade genealogy and lineage verification platform that unifies identity data from modern users and historical archives, processes high-volume record streams with resilient pipelines, and delivers auditable insights for researchers and institutions. It demonstrates multi-tenant security, OAuth2/SAML federation, streaming and async processing, and a rigorous documentation and testing discipline while remaining practical to deploy locally with Docker and Kubernetes.

SUPER PROMPT END
