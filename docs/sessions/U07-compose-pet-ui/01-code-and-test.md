# U07 Session 1 — Code & Test: Compose main pet screen

- **Role:** Implementer (fresh context). Do not audit your own work afterward.
- **Branch:** `feature/U07-compose-pet-ui`
- **Depends on:** U1 and U2 Closed
- **Risk:** Medium
- **Spec (read first):** `docs/units/U07.md`, `docs/build-plan.md`, `AGENTS.md`
- **Protocol template:** `docs/templates/session-01-code-test.md`
- **Skill:** `.cursor/skills/pipkin-session-code-test/SKILL.md`

## Objective

Produce the U07 implementation and its tests with TDD. No production code until tests exist, fail for the expected reason, and are committed.

## Explore (no file edits)

1. Read the spec and build-plan sections this unit owns.
2. Read existing code on the dependency units.
3. Read `.cursor/rules/` (kotlin, testing, architecture, security).

## Plan (human approval required before edits)

Publish a plan covering: files to touch, function signatures, data flow, edge cases, test names. Expected files:

- app/.../ui/pet/*.kt

Unit-specific focus:

- Meters + five actions
- Reconcile on start
- Placeholder pet art OK

## TDD

1. Write tests from the spec's named cases and edge list. No mocks that replace the behavior under test.
2. Run tests. Confirm red (missing implementation).
3. Commit: `test: add failing tests for U07 Compose main pet screen`
4. Implement without editing those tests.
5. Commit: `feat: implement U07 Compose main pet screen`
6. Open PR against `develop`. CI green. Zero TODOs.

## Exit criteria

- [ ] Plan approved by human lead
- [ ] Separate test then feat commits
- [ ] Spec tests named by behavior
- [ ] CI green
- [ ] Roadmap stage for U07 = Coding (ready for Session 2)

Next: `02-audit.md` in a **different** agent session.
