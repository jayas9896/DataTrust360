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

## CL-0012 - Restore detailed logs after ID migration

- Why: Ensure the detailed log directory is populated after the sequential ID switch.
- What: Recreated all detailed log files with the new CL- naming scheme.
- How: Regenerated the detailed log templates and confirmed link targets exist.
- Order/Priority: Fixed immediately after the ID migration to prevent broken navigation.
- Details: [docs/change-log-detailed/CL-0012.md](change-log-detailed/CL-0012.md)
