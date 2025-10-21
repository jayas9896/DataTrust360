# Coding Standards

## Javadoc Policy (Mandatory)

- Every Java class, interface, and method must include Javadoc.
- Each Javadoc block must include:
  - A short summary.
  - An "Importance" sentence describing why the element matters.
  - An "Alternatives" sentence describing a reasonable alternative when applicable.
- Keep explanations concise and use clear language.
- Apply this policy to all new modules and future code changes.

## Commit Log Policy (Mandatory)

- Every commit must include a new sequential entry in `docs/commit-log.md`.
- Every commit must include a detailed file in `docs/change-log-detailed/` named with the new sequential ID.
- Use the following template for both main log entries and detailed files:

Main log entry template:

```
## CL-XXXX - Short summary

- Why: ...
- What: ...
- How: ...
- Order/Priority: ...
- Details: [docs/change-log-detailed/CL-XXXX.md](change-log-detailed/CL-XXXX.md)
```

Detailed file template:

```
# Detailed Change Log: CL-XXXX

Back to main log: [docs/commit-log.md](../commit-log.md)

## Commit Summary
...

## Why
...

## What Changed
- ...

## How (Implementation Details)
- ...

## Files Touched
- ...
```
