---
name: pipkin-session-code-test
description: >-
  Run PipKin Session 1 (Code & Test) for a unit. Use when implementing a PipKin
  unit, starting TDD, writing failing tests first, or opening a feature PR.
---

# PipKin Session 1 — Code & Test

You are the **Implementer**. Do not audit this unit later in the same session.

## Before any edit

1. Identify the unit id (U00–U13).
2. Read `AGENTS.md`, `GOVERNANCE.md`, `docs/build-plan.md`, `docs/units/Uxx.md`, `docs/sessions/<folder>/01-code-and-test.md`.
3. Explore existing code. Write a Plan (files, signatures, data flow, edge cases, test names).
4. Stop until the human lead has approved the plan.

## TDD order (mandatory)

1. Tests only. No production stubs.
2. Run tests. Confirm they fail because implementation is missing.
3. Commit `test: add failing tests for Uxx …`
4. Implement. Do not change those tests.
5. Commit `feat: implement Uxx …`
6. PR against `develop`. CI green. No TODOs.

## Rules

- `:core` has zero Android imports.
- Hunger decay is 2/hour. Offline cap is 72h. Care mistake is 20 minutes at hunger 0.
- Test names state behavior.
- If context is ~70% full, write `docs/phase-logs/<unit>/checkpoint.md` and stop for a fresh session.
