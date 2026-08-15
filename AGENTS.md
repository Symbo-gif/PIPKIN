# PipKin — Agent Operating Rules

PipKin is an Android virtual pet. This file is the canonical contract for every Cursor agent. Read it before writing code. Prefer this file over chat memory when they conflict.

## Mission

Build PipKin through independently cyclable units (U1–U13). Every unit uses the same four-session loop, in order, with no skipped gates:

1. **Code & Test** (TDD: tests first, red confirmed, tests committed, then implementation)
2. **Audit** (independent session/context from the implementer)
3. **Remediation** (fix findings with regression tests; re-audit Critical/High)
4. **Documentation** (closure log, AGENTS/rules updates, then merge to `develop`)

Human lead (MichaelMaillet) owns product intent in `docs/build-plan.md`. Agents write Session 1 plans before editing, record Critical audit findings in the phase-log (they block merge until remediated), and merge to `develop` only after the Session 4 closure doc is committed. Agents own git/GitHub: remotes, branch protection, PRs, and Session 4 merges.

Ground truth for product behavior: `docs/build-plan.md`. Ground truth for process: `agentic-dev-methodology.md` and `GOVERNANCE.md`. Current unit status: `docs/roadmap.md`. Active session briefs: `docs/sessions/`.

## Architecture

- `:core` — pure Kotlin JVM module. Domain models, decay/tick simulation, offline catch-up, evolution/care-mistake logic. **No Android imports.**
- `:app` — Android application. Room persistence, WorkManager/AlarmManager, notifications, Compose UI, Firebase Analytics. Maps Room entities to `:core` domain types.
- Package root: `com.pipkin`.
- UI must not contain simulation math. Compose observes state and dispatches user intents; the engine lives in `:core`.
- Persistence must not contain simulation math. DAOs store and load; `:core` computes the next `PetState`.

## Locked game rules

Do not change these without updating `docs/build-plan.md` and logging the change in the unit's phase-log:

- Hunger decays **2 points per hour**.
- Offline catch-up: a real-world gap of 96 hours is simulated as **at most 72 hours**.
- A care mistake triggers at **exactly 20 minutes** of continuous zero hunger.
- Meters are integers in **0–100** unless a later signed spec says otherwise.

## Naming

- Types: `PascalCase`. Functions/properties: `camelCase`. Constants: `UPPER_SNAKE`.
- Tests name the behavior: `hunger_decays_correctly_after_72_hour_offline_gap_with_cap_applied`.
- Room entities: `*Entity`. Domain models: no suffix (`PetState`, `HistoryLog`). DAOs: `*Dao`.
- Feature branches: `feature/Uxx-short-name` (example: `feature/U02-simulation-engine`).

## Environment / CI

Ground truth for SDK and toolchain versions: `docs/build-plan.md` §4 and `gradle/libs.versions.toml`.

- compileSdk / targetSdk **36** requires AGP **8.9.1** and Gradle **8.11.1** (wrapper in repo).
- `gradlew` must be **executable** in git (`git update-index --chmod=+x gradlew`) so Linux CI can run `./gradlew`; Windows clones may omit the bit and break the first Actions run.
- PR merge gate: CI job **`verify`** (ktlint, detekt, unit tests, `assembleDebug`). The **`instrumented`** workflow job is a placeholder until U13.
- Line-coverage gates (JaCoCo, ≥ 90% on `:core` simulation paths) start at **U2**, not U00.

## Testing (non-negotiable)

- TDD order is mandatory. Do not write production code for a unit until new tests exist, fail for the expected reason, and are committed (`test: add failing tests for Uxx`).
- Do not edit those tests while implementing. Implementation commit is separate (`feat: implement Uxx`).
- Pure logic in `:core` (decay, evolution, offline catch-up) requires **≥ 90% line coverage**.
- Offline reconciliation (U3) requires property-based / fuzz tests.
- U3 and U4 require a Critical-severity re-audit even if the first audit finds nothing.
- No mocks that hide the behavior under test. No tautological tests.

## Forbidden patterns

- Business logic in Compose, Activities, or ViewModels beyond mapping intents ↔ domain calls.
- Android types inside `:core`.
- Hardcoded secrets, API keys, or `google-services.json` in git.
- Silent spec changes. If the spec is wrong, stop and update `docs/build-plan.md`.
- TODOs, placeholders, or stub implementations in code that is claimed complete.
- Direct pushes to `main` or `develop`.
- Self-audit: the auditor session must not be the implementer session.
- Skipping Remediation when Audit has findings, or skipping Documentation before merge.
- Repeat / non-actionable notification spam (Live Update and standard notifications).

## Session hygiene

- Start from the unit's session brief in `docs/sessions/Uxx-*/`.
- If context approaches ~70% saturation, write a checkpoint note in `docs/phase-logs/<unit>/` and open a fresh session.
- After each session, write the required artifact into `docs/phase-logs/<unit>/` using the templates in `docs/templates/`.
- Parallelism is allowed across units whose dependencies are Closed. A single unit's four sessions stay sequential.

## Module map

| Unit | Owner module(s) | Depends on |
|------|-----------------|------------|
| U1 | `:app` data / Room | none |
| U2 | `:core` simulation | U1 |
| U3 | `:core` offline | U2 |
| U4 | `:core` evolution | U2 |
| U5 | `:app` work | U3 |
| U6 | `:app` notifications | U5 |
| U7 | `:app` ui/pet | U1, U2 |
| U8 | `:app` ui/minigames | U7 |
| U9 | `:app` ui/minigames | U7 |
| U10 | `:app` ui + `:core` | U4, U7 |
| U11 | `:app` ui/onboarding + data | U1, U7 |
| U12 | `:app` analytics | U2, U4, U10 |
| U13 | whole repo | all above |
