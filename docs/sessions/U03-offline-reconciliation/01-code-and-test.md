# U03 Session 1 — Code & Test: Offline-progress reconciliation

- **Role:** Implementer (fresh context). Do not audit your own work afterward.
- **Branch:** `feature/U03-offline-reconciliation`
- **Depends on:** U2 Closed
- **Risk:** Critical
- **Spec (read first):** `docs/units/U03.md`, `docs/build-plan.md`, `AGENTS.md`
- **Protocol template:** `docs/templates/session-01-code-test.md`
- **Skill:** `.cursor/skills/pipkin-session-code-test/SKILL.md`

## Objective

Produce the U03 implementation and its tests with TDD. No production code until tests exist, fail for the expected reason, and are committed.

## Explore (no file edits)

1. Read the spec and build-plan sections this unit owns.
2. Read existing code on the dependency units.
3. Read `.cursor/rules/` (kotlin, testing, architecture, security).

## Plan (write before edits)

Publish a plan covering: files to touch, function signatures, data flow, edge cases, test names. Expected files:

- core/.../offline/OfflineReconciler.kt

Unit-specific focus:

- OfflineReconciler.reconcile
- min(elapsed, 72h)
- Fuzz tests mandatory

## TDD

1. Write tests from the spec's named cases and edge list. No mocks that replace the behavior under test.
2. Run tests. Confirm red (missing implementation).
3. Commit: `test: add failing tests for U03 Offline-progress reconciliation`
4. Implement without editing those tests.
5. Commit: `feat: implement U03 Offline-progress reconciliation`
6. Open PR against `develop`. CI green. Zero TODOs.

## Exit criteria

- [ ] Plan written before edits
- [ ] Separate test then feat commits
- [ ] Spec tests named by behavior
- [ ] CI green
- [ ] Roadmap stage for U03 = Coding (ready for Session 2)

Next: `02-audit.md` in a **different** agent session.
