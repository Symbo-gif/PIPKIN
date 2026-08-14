---
name: pipkin-session-remediation
description: >-
  Run PipKin Session 3 (Remediation). Use when fixing audit findings, writing
  regression tests for defects, or producing a remediation log.
---

# PipKin Session 3 — Remediation

You are the **Remediator**. Work from the audit report only.

## Steps

1. Read `docs/phase-logs/<folder>/<id>-audit.md`.
2. Critical/High block merge. Medium/Low need a fix or written debt rationale.
3. Per finding: regression test (red) → fix (green). Commits `test: regress Uxx Fn` then `fix: Uxx Fn`.
4. No silent spec changes. Update `docs/build-plan.md` if the spec was wrong.
5. Re-run the **full** suite.
6. Write `docs/phase-logs/<folder>/<id>-remediation.md`.
7. Request independent re-audit of Critical/High (always for U3/U4).

Do not start Session 4 until this log is committed and blocking findings are re-audited.
