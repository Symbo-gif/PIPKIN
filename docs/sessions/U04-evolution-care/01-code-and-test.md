# U04 Session 1 — Code & Test: Evolution branching & care mistakes

- **Role:** Implementer (fresh context). Do not audit your own work afterward.
- **Branch:** `feature/U04-evolution-care`
- **Depends on:** U2 Closed
- **Risk:** Critical
- **Spec (read first):** `docs/units/U04.md`, `docs/build-plan.md`, `AGENTS.md`
- **Protocol template:** `docs/templates/session-01-code-test.md`
- **Skill:** `.cursor/skills/pipkin-session-code-test/SKILL.md`

## Objective

Produce the U04 implementation and its tests with TDD. No production code until tests exist, fail for the expected reason, and are committed.

## Explore (no file edits)

1. Read the spec and build-plan sections this unit owns.
2. Read existing code on the dependency units.
3. Read `.cursor/rules/` (kotlin, testing, architecture, security).

## Plan (write before edits)

Publish a plan covering: files to touch, function signatures, data flow, edge cases, test names. Expected files:

- core/.../evolution/*.kt

Unit-specific focus:

- hungerZeroSinceMillis
- stage/branch transitions
- Do not duplicate decay math

## TDD

1. Write tests from the spec's named cases and edge list. No mocks that replace the behavior under test.
2. Run tests. Confirm red (missing implementation).
3. Commit: `test: add failing tests for U04 Evolution branching & care mistakes`
4. Implement without editing those tests.
5. Commit: `feat: implement U04 Evolution branching & care mistakes`
6. Open PR against `develop`. CI green. Zero TODOs.

## Exit criteria

- [ ] Plan written before edits
- [ ] Separate test then feat commits
- [ ] Spec tests named by behavior
- [ ] CI green
- [ ] Roadmap stage for U04 = Coding (ready for Session 2)

Next: `02-audit.md` in a **different** agent session.
