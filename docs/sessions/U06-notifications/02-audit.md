# U06 Session 2 — Audit: Notifications + Live Update

- **Role:** Auditor. **Must not** be the Session 1 implementer session.
- **PR / branch:** `feature/U06-notifications`
- **Ground truth:** `docs/units/U06.md` (verbatim) + `docs/build-plan.md`
- **Template:** `docs/templates/session-02-audit.md`
- **Output:** `docs/phase-logs/U06-notifications/U06-audit.md`
- **Skill:** `.cursor/skills/pipkin-session-audit/SKILL.md`

## Objective

Independently verify the PR against the spec and industry quality. Catch hallucinated "all tests passing" claims against real CI and the diff.

## Protocol

1. Re-read the unit spec. Scope is U06 only, plus regressions to dependencies.
2. Collect PR diff, CI, coverage, AGENTS.md decisions.
3. Run ktlint, detekt, unit tests, coverage.
4. Manual checklist (all units) plus the U06 focus list below.
5. Rank findings Critical / High / Medium / Low.
6. Verdict: Pass / Conditional Pass / Fail.
7. Commit the audit report. Do not fix code in this session.

## U06 audit focus

- [ ] No repeat non-actionable spam
- [ ] Dedupe until condition clears
- [ ] Live Update updates in place
- [ ] Actionable copy only
- [ ] Tests were committed before implementation (git log)
- [ ] No business logic in the wrong layer
- [ ] No secrets, no TODOs in claimed-complete code
- [ ] Claimed status matches CI

Re-audit only Critical/High findings after remediation.

## Exit criteria

- [ ] `docs/phase-logs/U06-notifications/U06-audit.md` committed
- [ ] Roadmap stage = Audit (move to Remediation if any findings, else Documentation)
- [ ] Critical findings recorded in the audit report; they block merge until remediated and re-audited

Next: `03-remediation.md` if findings exist; otherwise `04-documentation.md` (U3/U4 still need re-audit note).
