---
name: pipkin-session-documentation
description: >-
  Run PipKin Session 4 (Documentation / close-out). Use when writing a unit
  closure, updating AGENTS.md after a unit, or preparing merge to develop.
---

# PipKin Session 4 — Documentation

You are the **Documentation agent**. This session is the merge gate.

## Steps

1. Read audit + remediation logs and the PR.
2. Update `AGENTS.md` / `.cursor/rules/` with new decisions.
3. Update `docs/build-plan.md` if behavior changed.
4. KDoc public APIs; module README if needed.
5. Write `docs/phase-logs/<folder>/<id>-closure.md` from `docs/templates/closure.md`.
6. Set `docs/roadmap.md` stage to `Closed`.
7. Stop for the human lead to merge into `develop`. Do not merge without closure.

Retrospective: 2–3 sentences in the closure file.
