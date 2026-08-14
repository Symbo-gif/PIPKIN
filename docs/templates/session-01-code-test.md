# Session 1 template — Code & Test

Copy structure. Fill unit-specific fields from `docs/units/` and `docs/build-plan.md`.

## Header

- Unit:
- Branch: `feature/Uxx-short-name`
- Role: Implementer (fresh context)
- Human gate: Plan Mode must be approved before any file is modified

## Protocol

1. Explore. Read `AGENTS.md`, relevant `.cursor/rules/`, the unit spec, and existing code. No edits.
2. Plan. Write files to touch, signatures, data flow, edge cases. Stop for approval.
3. Tests first. Input/output pairs and named edge cases. No production stubs.
4. Confirm red. Tests fail because implementation is missing, not because tests are broken.
5. Commit: `test: add failing tests for Uxx`.
6. Implement. Do not modify the committed tests.
7. Commit: `feat: implement Uxx`.
8. Open PR against `develop`. CI green. No TODOs.

## Exit criteria

- [ ] Plan approved
- [ ] Test commit then implementation commit (separate)
- [ ] Coverage floors in the unit spec met
- [ ] CI green on the PR
- [ ] `docs/roadmap.md` stage = Coding (until audit starts)
