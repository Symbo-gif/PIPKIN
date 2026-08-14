# PipKin

Android virtual pet. Persistent care, honest offline time, evolution from how you look after it.

## Start here

| File | Role |
|------|------|
| [AGENTS.md](AGENTS.md) | Canonical rules for every coding agent |
| [GOVERNANCE.md](GOVERNANCE.md) | Roles, gates, branching, four-session loop |
| [docs/build-plan.md](docs/build-plan.md) | Product and technical ground truth |
| [docs/roadmap.md](docs/roadmap.md) | Unit status tracker |
| [agentic-dev-methodology.md](agentic-dev-methodology.md) | Full cycle methodology |

## How work happens

Every unit (U00–U13) uses four sessions: **Code & Test → Audit → Remediation → Documentation**.

1. Open `docs/sessions/<unit>/` and the unit spec in `docs/units/`.
2. Follow the matching skill under `.cursor/skills/pipkin-session-*`.
3. Put audit, remediation, and closure artifacts in `docs/phase-logs/<unit>/`.
4. Merge to `develop` only after Session 4 closure is committed.

## Modules

- `:core` — pure Kotlin simulation (no Android).
- `:app` — Room, WorkManager, notifications, Compose, Firebase.

Package: `com.pipkin`.
