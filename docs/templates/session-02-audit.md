# Session 2 template — Audit

Auditor must be a different session/context than the implementer.

## Header

- Unit / PR:
- Spec ground truth: `docs/build-plan.md` + `docs/units/Uxx.md` (read verbatim)
- Role: Auditor

## Protocol

1. Confirm scope. Re-read the unit spec. Do not audit neighboring units except for breakages.
2. Collect evidence: PR diff, CI, coverage, `AGENTS.md` decisions.
3. Run ktlint, detekt, tests, coverage. Treat as inputs, not the verdict.
4. Manual checklist:
   - Spec conformance (no silent scope cut, no invented behavior)
   - Test integrity (commit order tests-before-feat; edge cases real, not tautological)
   - Layering (no sim math in UI; no Android in `:core`)
   - Security (corrupt saves, no secrets)
   - Performance (bounded ticks, no main-thread I/O)
   - Claimed vs actual (CI and diff vs agent status claims)
5. Rank findings Critical / High / Medium / Low.
6. Write `docs/phase-logs/<unit>/<unit>-audit.md` from `docs/templates/audit-report.md`.
7. Verdict: Pass | Conditional Pass | Fail.

## Exit criteria

- [ ] Audit report committed
- [ ] Verdict recorded on the PR and in `docs/roadmap.md` (stage = Audit, then Remediation or Documentation)
- [ ] U3/U4: schedule Critical re-audit even if findings list is empty
