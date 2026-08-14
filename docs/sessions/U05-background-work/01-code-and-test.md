# U05 Session 1 — Code & Test: WorkManager & AlarmManager

- **Role:** Implementer (fresh context). Do not audit your own work afterward.
- **Branch:** `feature/U05-background-work`
- **Depends on:** U3 Closed
- **Risk:** Medium
- **Spec (read first):** `docs/units/U05.md`, `docs/build-plan.md`, `AGENTS.md`
- **Protocol template:** `docs/templates/session-01-code-test.md`
- **Skill:** `.cursor/skills/pipkin-session-code-test/SKILL.md`

## Objective

Produce the U05 implementation and its tests with TDD. No production code until tests exist, fail for the expected reason, and are committed.

## Explore (no file edits)

1. Read the spec and build-plan sections this unit owns.
2. Read existing code on the dependency units.
3. Read `.cursor/rules/` (kotlin, testing, architecture, security).

## Plan (human approval required before edits)

Publish a plan covering: files to touch, function signatures, data flow, edge cases, test names. Expected files:

- app/.../work/*.kt

Unit-specific focus:

- Periodic worker 15-30 min
- Alarm for 20-minute hunger-zero window

## TDD

1. Write tests from the spec's named cases and edge list. No mocks that replace the behavior under test.
2. Run tests. Confirm red (missing implementation).
3. Commit: `test: add failing tests for U05 WorkManager & AlarmManager`
4. Implement without editing those tests.
5. Commit: `feat: implement U05 WorkManager & AlarmManager`
6. Open PR against `develop`. CI green. Zero TODOs.

## Exit criteria

- [ ] Plan approved by human lead
- [ ] Separate test then feat commits
- [ ] Spec tests named by behavior
- [ ] CI green
- [ ] Roadmap stage for U05 = Coding (ready for Session 2)

Next: `02-audit.md` in a **different** agent session.
