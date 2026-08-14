# Session 3 template — Remediation

Skip this session only when Session 2 is Pass with zero findings (except U3/U4 still get a re-audit note).

## Header

- Unit:
- Input: `docs/phase-logs/<unit>/<unit>-audit.md`
- Role: Remediator

## Protocol

1. One remediation task per finding. Critical/High block merge.
2. Medium/Low may be deferred only with written rationale in the remediation log.
3. For each fix: regression test that reproduces the defect (red), then fix (green).
4. No silent spec changes. Raise spec issues; update `docs/build-plan.md` if the human agrees.
5. Re-run the full test suite.
6. Write `docs/phase-logs/<unit>/<unit>-remediation.md`.
7. Critical/High: independent re-audit of those fixes.

## Exit criteria

- [ ] Every Critical/High finding has fix commit, regression test name, and re-audit sign-off
- [ ] Medium/Low fixed or accepted as debt with rationale
- [ ] Full suite green
