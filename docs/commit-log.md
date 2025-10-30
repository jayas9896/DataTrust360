# Commit Log (Learning Notes)

This log uses sequential IDs so each entry can be documented in the same commit.

## CL-0001 - Initialize DataTrust360 multi-module scaffold

- Why: Establish a realistic enterprise-grade baseline to anchor all subsequent features and learning tasks.
- What: Added multi-module Maven structure, core services, security placeholders, ingestion pipelines, storage models, docs/UML, tests, Docker/K8s, and observability assets.
- How: Created module POMs and service skeletons, wired initial security and pipelines, and added storage models plus deployment/observability assets.
- Order/Priority: Scaffold first to create a stable backbone for all later security, pipeline, and infra work. Starting with structure reduces rework when adding deeper features.
- Details: [docs/change-log-detailed/CL-0001.md](change-log-detailed/CL-0001.md)

## CL-0002 - Add Agile/Scrum plan doc

- Why: Capture delivery structure and team cadence early, mirroring real enterprise planning.
- What: Added Agile/Scrum plan doc and linked it from the README.
- How: Wrote a structured plan with roles, cadence, DoD, backlog, and releases, then linked it.
- Order/Priority: After baseline scaffold, planning docs make sense because the architecture exists to drive realistic epics and stories.
- Details: [docs/change-log-detailed/CL-0002.md](change-log-detailed/CL-0002.md)

## CL-0003 - Add mandatory Javadoc across codebase

- Why: Enforce strong documentation habits and make code self-explanatory for interview readiness.
- What: Added Javadocs to all Java classes/methods and codified the policy in coding standards.
- How: Added Javadocs with Importance/Alternatives and documented the rule in coding standards, then linked it.
- Order/Priority: Applied after core code was in place to avoid duplicating docs while the skeleton was still moving.
- Details: [docs/change-log-detailed/CL-0003.md](change-log-detailed/CL-0003.md)

## CL-0004 - Enforce mandatory Javadoc via Checkstyle

- Why: Prevent regressions and ensure documentation stays mandatory.
- What: Added Checkstyle configuration and Maven enforcement during validate.
- How: Added Checkstyle rules for class/method Javadocs and wired the plugin into Maven.
- Order/Priority: Enforcement only makes sense after the codebase already complies, so it followed the Javadoc update.
- Details: [docs/change-log-detailed/CL-0004.md](change-log-detailed/CL-0004.md)

## CL-0005 - Add sequential commit learning log

- Why: Make commit history a learning narrative that explains intent, features, and ordering.
- What: Added `docs/commit-log.md` with per-commit summaries.
- How: Wrote the log entries and linked it from the README for visibility.
- Order/Priority: Added after core and documentation policies to reflect the existing commit history.
- Details: [docs/change-log-detailed/CL-0005.md](change-log-detailed/CL-0005.md)

## CL-0006 - Add detailed per-commit change logs

- Why: Provide deeper, interview-ready explanations for each commit beyond the main summary.
- What: Added per-commit detailed docs and expanded the main commit log with How/Details.
- How: Created `docs/change-log-detailed/` files for prior entries and linked them from the main log.
- Order/Priority: Added after the base commit log existed to avoid documenting a log that did not yet exist.
- Details: [docs/change-log-detailed/CL-0006.md](change-log-detailed/CL-0006.md)

## CL-0007 - Add navigation links between commit logs

- Why: Improve discoverability by making navigation between summary and detailed logs frictionless.
- What: Added cross-links in the main log and back-links in detailed files.
- How: Converted plain paths to clickable links and standardized back references.
- Order/Priority: Added after detailed logs existed so linking targets were stable.
- Details: [docs/change-log-detailed/CL-0007.md](change-log-detailed/CL-0007.md)

## CL-0008 - Add .git-keep files for empty directories

- Why: Preserve intended repository structure for empty folders and keep learning docs complete.
- What: Added `.git-keep` files to empty directories and backfilled missing detailed logs.
- How: Detected empty folders, added `.git-keep`, and created detailed log entries for recent changes.
- Order/Priority: Filled missing documentation first, then preserved structure to prevent future drift.
- Details: [docs/change-log-detailed/CL-0008.md](change-log-detailed/CL-0008.md)

## CL-0009 - Document .git-keep commit details

- Why: Keep the learning log complete by documenting the latest structural update.
- What: Added a main log entry and detailed log for the `.git-keep` change.
- How: Appended a new entry to the main log and created a corresponding detailed file.
- Order/Priority: Updated documentation immediately after the structural change to avoid gaps.
- Details: [docs/change-log-detailed/CL-0009.md](change-log-detailed/CL-0009.md)

## CL-0010 - Log prior commit details

- Why: Maintain a continuous narrative by documenting the previous log update.
- What: Added a main log entry and detailed file for the prior documentation change.
- How: Appended an entry to the main log and created a new detailed file.
- Order/Priority: Ensured no gaps before switching formats.
- Details: [docs/change-log-detailed/CL-0010.md](change-log-detailed/CL-0010.md)

## CL-0011 - Switch commit logs to sequential IDs

- Why: Avoid infinite follow-up commits caused by hash-based logging while preserving sequential learning notes.
- What: Renamed detailed logs to sequential IDs and updated all links and entries accordingly.
- How: Rewrote the main log to use CL- IDs and regenerated detailed files with consistent templates.
- Order/Priority: Applied after previous entries were complete to minimize rewrite churn.
- Details: [docs/change-log-detailed/CL-0011.md](change-log-detailed/CL-0011.md)

## CL-0012 - Attempt to restore detailed logs after ID migration

- Why: Repair missing detailed files after the ID migration.
- What: Updated the main log and attempted to regenerate detailed logs.
- How: Regenerated the main log and re-ran the log generation step.
- Order/Priority: Attempted immediately after the migration to keep links working.
- Details: [docs/change-log-detailed/CL-0012.md](change-log-detailed/CL-0012.md)

## CL-0013 - Restore detailed logs after ID migration

- Why: Ensure the detailed log directory is populated after the sequential ID switch.
- What: Recreated all detailed log files with the new CL- naming scheme.
- How: Regenerated the detailed log templates and confirmed link targets exist.
- Order/Priority: Fixed immediately after the failed attempt to prevent broken navigation.
- Details: [docs/change-log-detailed/CL-0013.md](change-log-detailed/CL-0013.md)

## CL-0014 - Update sequential log entries

- Why: Align the log sequence after the ID migration fixes.
- What: Added missing entries to cover recent log adjustments.
- How: Appended new sequential entries and refreshed detailed files.
- Order/Priority: Applied after log restoration to keep ordering consistent.
- Details: [docs/change-log-detailed/CL-0014.md](change-log-detailed/CL-0014.md)

## CL-0015 - Record log update for CL-0014

- Why: Keep the learning log current without leaving gaps after an entry update.
- What: Added the entry and detailed file describing the log alignment work.
- How: Appended the new entry and created its detailed counterpart.
- Order/Priority: Documented immediately so the sequence remains continuous.
- Details: [docs/change-log-detailed/CL-0015.md](change-log-detailed/CL-0015.md)

## CL-0016 - Require commit log templates for future commits

- Why: Ensure consistent, interview-ready documentation for every change.
- What: Added a mandatory commit log template to coding standards.
- How: Documented the required format in `docs/coding-standards.md`.
- Order/Priority: Applied after switching to sequential IDs so the format is stable.
- Details: [docs/change-log-detailed/CL-0016.md](change-log-detailed/CL-0016.md)

## CL-0017 - Persist ingested events to storage service

- Why: Preserve raw events for forensics and downstream analytics while keeping ingestion decoupled.
- What: Added storage persistence from processing worker and a storage API endpoint to accept events.
- How: Added a storage client and REST endpoint, mapped envelopes to documents, and wired config for service URL.
- Order/Priority: Implemented after pipeline scaffolding to connect ingestion to durable storage.
- Details: [docs/change-log-detailed/CL-0017.md](change-log-detailed/CL-0017.md)

## CL-0018 - Add Kinesis polling for partner ingestion

- Why: Process partner/edge ingestion events separately from Kafka while reusing the same scoring pipeline.
- What: Added a Kinesis poller in the processing worker, config wiring, and queue publisher reuse.
- How: Introduced a scheduled poller, Kinesis client config, and shared queue publisher for scoring.
- Order/Priority: Implemented after storage persistence to ensure partner events are durable before scoring.
- Details: [docs/change-log-detailed/CL-0018.md](change-log-detailed/CL-0018.md)

## CL-0019 - Add OpenAI insights and audit logging

- Why: Provide natural language summaries for anomaly events and capture them for compliance.
- What: Added OpenAI client integration, audit log endpoint, and storage of insights.
- How: Introduced OpenAI service, audit DTO, audit repository, and storage API wiring.
- Order/Priority: Implemented after storage persistence so insights can be stored centrally.
- Details: [docs/change-log-detailed/CL-0019.md](change-log-detailed/CL-0019.md)

## CL-0020 - Attribute insights to tenant IDs

- Why: Ensure audit logs and insights are correctly tied to tenant context.
- What: Parse tenant IDs from payloads before generating insights.
- How: Added payload parsing in the scoring worker and defaulted safely on failures.
- Order/Priority: Implemented after OpenAI integration to improve audit quality.
- Details: [docs/change-log-detailed/CL-0020.md](change-log-detailed/CL-0020.md)

## CL-0021 - Process bulk Kafka events in worker

- Why: Ensure gRPC bulk ingestion is processed through the same pipeline as REST events.
- What: Added a Kafka listener for the bulk topic and updated pipeline docs.
- How: Reused existing parsing, storage persistence, and queueing logic.
- Order/Priority: Added after storage and insight integration to reuse the mature pipeline path.
- Details: [docs/change-log-detailed/CL-0021.md](change-log-detailed/CL-0021.md)

## CL-0022 - Add API usage examples

- Why: Provide interview-ready, runnable examples for key endpoints and gRPC calls.
- What: Added a dedicated API examples doc and linked it from the README.
- How: Documented curl and grpcurl samples for REST, partner ingest, gRPC, and storage APIs.
- Order/Priority: Added after core endpoints stabilized to avoid churn in examples.
- Details: [docs/change-log-detailed/CL-0022.md](change-log-detailed/CL-0022.md)

## CL-0023 - Add testing strategy doc

- Why: Capture an enterprise-grade testing approach for interviews and execution.
- What: Added a testing strategy document and linked it from the README.
- How: Documented unit, integration, contract, and observability test layers.
- Order/Priority: Added after API examples to align tests with documented usage.
- Details: [docs/change-log-detailed/CL-0023.md](change-log-detailed/CL-0023.md)

## CL-0024 - Add Kubernetes dependencies manifest

- Why: Enable local Kubernetes deployments with required data and messaging services.
- What: Added a dependencies manifest and namespaced all app resources.
- How: Created deployments/services for Kafka, Zookeeper, Postgres, MongoDB, RabbitMQ, and LocalStack.
- Order/Priority: Added after app manifests so dependencies align with service expectations.
- Details: [docs/change-log-detailed/CL-0024.md](change-log-detailed/CL-0024.md)

## CL-0025 - Add Kubernetes observability manifests

- Why: Provide in-cluster Prometheus and Grafana for local Kubernetes monitoring.
- What: Added observability deployments, services, and Prometheus config.
- How: Defined ConfigMap-based Prometheus config and Grafana deployment in the namespace.
- Order/Priority: Added after dependencies to ensure targets exist before scraping.
- Details: [docs/change-log-detailed/CL-0025.md](change-log-detailed/CL-0025.md)

## CL-0026 - Add LocalStack init script

- Why: Ensure the Kinesis stream exists for partner ingestion during local development.
- What: Added a script to create the LocalStack Kinesis stream and documented its use.
- How: Created a PowerShell script that calls AWS CLI against LocalStack.
- Order/Priority: Added after Kinesis polling so local setup supports the pipeline.
- Details: [docs/change-log-detailed/CL-0026.md](change-log-detailed/CL-0026.md)

## CL-0027 - Add unit tests for insight and audit mapping

- Why: Validate tenant attribution and audit mapping logic for interview-ready quality.
- What: Added unit tests for anomaly scoring insight attribution and audit log mapping.
- How: Verified tenant parsing in the worker and audit ID parsing in the storage controller.
- Order/Priority: Added after the related features to lock in expected behavior.
- Details: [docs/change-log-detailed/CL-0027.md](change-log-detailed/CL-0027.md)

## CL-0028 - Add demo scenarios doc

- Why: Provide runnable end-to-end scenarios for interview demonstrations.
- What: Added a demo scenarios document and linked it from the README.
- How: Documented step-by-step flows for REST, gRPC, Kinesis, and observability.
- Order/Priority: Added after APIs and infrastructure steps were documented.
- Details: [docs/change-log-detailed/CL-0028.md](change-log-detailed/CL-0028.md)

## CL-0029 - Update audit schema documentation

- Why: Keep docs aligned with the expanded audit log model.
- What: Added the audit log details field to UML and SQL schema docs.
- How: Updated UML class diagram and SQL schema snippet in README.
- Order/Priority: Added after audit logging implementation to prevent doc drift.
- Details: [docs/change-log-detailed/CL-0029.md](change-log-detailed/CL-0029.md)

## CL-0030 - Update OpenAI insight documentation

- Why: Keep insight docs aligned with the audit logging and configuration flow.
- What: Updated insight storage and configuration notes.
- How: Added audit endpoint reference and updated action name and config hints.
- Order/Priority: Updated after OpenAI integration and audit endpoint were added.
- Details: [docs/change-log-detailed/CL-0030.md](change-log-detailed/CL-0030.md)

## CL-0031 - Add operations runbook

- Why: Provide a practical guide for running and troubleshooting the platform.
- What: Added a runbook document and linked it from the README.
- How: Documented Docker Compose, Kubernetes steps, and troubleshooting tips.
- Order/Priority: Added after demo scenarios so operational guidance is aligned with usage.
- Details: [docs/change-log-detailed/CL-0031.md](change-log-detailed/CL-0031.md)
