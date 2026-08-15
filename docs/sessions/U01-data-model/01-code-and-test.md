# U01 Session 1 — Code & Test: Data model & Room persistence

- **Role:** Implementer (fresh context). Do not audit your own work afterward.
- **Branch:** `feature/U01-data-model`
- **Depends on:** U00 Closed
- **Risk:** Medium
- **Spec (read first):** `docs/units/U01.md`, `docs/build-plan.md`, `AGENTS.md`
- **Protocol template:** `docs/templates/session-01-code-test.md`
- **Skill:** `.cursor/skills/pipkin-session-code-test/SKILL.md`

## Objective

Produce the U01 implementation and its tests with TDD. No production code until tests exist, fail for the expected reason, and are committed.

## Explore (no file edits)

1. Read the spec and build-plan sections this unit owns.
2. Read existing code on the dependency units.
3. Read `.cursor/rules/` (kotlin, testing, architecture, security).

## Plan (write before edits)

Publish a plan covering: files to touch, function signatures, data flow, edge cases, test names. Expected files:

- core/.../model/PetState.kt
- app/.../data/*Entity.kt
- app/.../data/*Dao.kt

Unit-specific focus:

- PetState and HistoryLog domain in :core
- Room entities/DAOs in :app
- Three save slots

## TDD

1. Write tests from the spec's named cases and edge list. No mocks that replace the behavior under test.
2. Run tests. Confirm red (missing implementation).
3. Commit: `test: add failing tests for U01 Data model & Room persistence`
4. Implement without editing those tests.
5. Commit: `feat: implement U01 Data model & Room persistence`
6. Open PR against `develop`. CI green. Zero TODOs.

## Exit criteria

- [x] Plan written before edits
- [x] Separate test then feat commits
- [x] Spec tests named by behavior
- [x] CI green
- [x] Roadmap stage for U01 = Coding (ready for Session 2)

Next: `02-audit.md` in a **different** agent session.
