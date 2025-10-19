# Commit Log (Learning Notes)

This log explains each commit in sequence: why it was done, what it added, and why that order was chosen.

## 80c6b4b - Initialize DataTrust360 multi-module scaffold

- Why: Establish a realistic enterprise-grade baseline to anchor all subsequent features and learning tasks.
- What: Added multi-module Maven structure, core services, security placeholders, ingestion pipelines, storage models, docs/UML, tests, Docker/K8s, and observability assets.
- How: Created module POMs and service skeletons, wired initial security and pipelines, and added storage models plus deployment/observability assets.
- Order/Priority: Scaffold first to create a stable backbone for all later security, pipeline, and infra work. Starting with structure reduces rework when adding deeper features.
- Details: [docs/change-log-detailed/80c6b4b.md](change-log-detailed/80c6b4b.md)

## 97c5b3d - Add Agile/Scrum plan doc

- Why: Capture delivery structure and team cadence early, mirroring real enterprise planning.
- What: Added Agile/Scrum plan doc and linked it from the README.
- How: Wrote a structured plan with roles, cadence, DoD, backlog, and releases, then linked it.
- Order/Priority: After baseline scaffold, planning docs make sense because the architecture exists to drive realistic epics and stories.
- Details: [docs/change-log-detailed/97c5b3d.md](change-log-detailed/97c5b3d.md)

## 8f0cf30 - Add mandatory Javadoc across codebase

- Why: Enforce strong documentation habits and make code self-explanatory for interview readiness.
- What: Added Javadocs to all Java classes/methods and codified the policy in coding standards.
- How: Added Javadocs with Importance/Alternatives and documented the rule in coding standards, then linked it.
- Order/Priority: Applied after core code was in place to avoid duplicating docs while the skeleton was still moving.
- Details: [docs/change-log-detailed/8f0cf30.md](change-log-detailed/8f0cf30.md)

## 984acad - Enforce mandatory Javadoc via Checkstyle

- Why: Prevent regressions and ensure documentation stays mandatory.
- What: Added Checkstyle configuration and Maven enforcement during validate.
- How: Added Checkstyle rules for class/method Javadocs and wired the plugin into Maven.
- Order/Priority: Enforcement only makes sense after the codebase already complies, so it followed the Javadoc update.
- Details: [docs/change-log-detailed/984acad.md](change-log-detailed/984acad.md)

## 1109f80 - Add sequential commit learning log

- Why: Make commit history a learning narrative that explains intent, features, and ordering.
- What: Added `docs/commit-log.md` with per-commit summaries.
- How: Wrote the log entries and linked it from the README for visibility.
- Order/Priority: Added after core and documentation policies to reflect the existing commit history.
- Details: [docs/change-log-detailed/1109f80.md](change-log-detailed/1109f80.md)

## 5531777 - Add detailed per-commit change logs

- Why: Provide deeper, interview-ready explanations for each commit beyond the main summary.
- What: Added per-commit detailed docs and expanded the main commit log with How/Details.
- How: Created `docs/change-log-detailed/` files for prior commits and linked them from the main log.
- Order/Priority: Added after the base commit log existed to avoid documenting a log that did not yet exist.
- Details: [docs/change-log-detailed/5531777.md](change-log-detailed/5531777.md)

## 3474301 - Add navigation links between commit logs

- Why: Improve discoverability by making navigation between summary and detailed logs frictionless.
- What: Added cross-links in the main log and back-links in detailed files.
- How: Converted plain paths to clickable links and standardized back references.
- Order/Priority: Added after detailed logs existed so linking targets were stable.
- Details: [docs/change-log-detailed/3474301.md](change-log-detailed/3474301.md)

## aa4a293 - Add .git-keep files for empty directories

- Why: Preserve intended repository structure for empty folders and keep learning docs complete.
- What: Added `.git-keep` files to empty directories and backfilled missing detailed logs.
- How: Detected empty folders, added `.git-keep`, and created detailed log entries for recent commits.
- Order/Priority: Filled missing documentation first, then preserved structure to prevent future drift.
- Details: [docs/change-log-detailed/aa4a293.md](change-log-detailed/aa4a293.md)
