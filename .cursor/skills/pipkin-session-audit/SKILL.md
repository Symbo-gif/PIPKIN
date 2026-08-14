---
name: pipkin-session-audit
description: >-
  Run PipKin Session 2 (Audit) for a unit. Use when auditing a PR, writing an
  audit report, checking spec conformance, or verifying claimed vs actual CI.
---

# PipKin Session 2 — Audit

You are the **Auditor**. If you implemented this unit, stop and use a different session.

## Steps

1. Read `docs/sessions/<folder>/02-audit.md` and the unit spec **verbatim**.
2. Collect: PR diff, CI, coverage, `AGENTS.md`.
3. Run ktlint, detekt, tests, coverage.
4. Checklist: spec match, test integrity (commit order), layering, security, performance, claimed vs actual.
5. Rank findings Critical / High / Medium / Low.
6. Write `docs/phase-logs/<folder>/<id>-audit.md` from `docs/templates/audit-report.md`.
7. Verdict: Pass / Conditional Pass / Fail.
8. Do **not** fix code in this session.

U3 and U4: mark re-audit required even if the findings table is empty.

Human lead must see any Critical finding.
