# U04 Session 2 — Audit: Evolution branching & care mistakes

- **Role:** Auditor. **Must not** be the Session 1 implementer session.
- **PR / branch:** `feature/U04-evolution-care`
- **Ground truth:** `docs/units/U04.md` (verbatim) + `docs/build-plan.md`
- **Template:** `docs/templates/session-02-audit.md`
- **Output:** `docs/phase-logs/U04-evolution-care/U04-audit.md`
- **Skill:** `.cursor/skills/pipkin-session-audit/SKILL.md`

## Objective

Independently verify the PR against the spec and industry quality. Catch hallucinated "all tests passing" claims against real CI and the diff.

## Protocol

1. Re-read the unit spec. Scope is U04 only, plus regressions to dependencies.
2. Collect PR diff, CI, coverage, AGENTS.md decisions.
3. Run ktlint, detekt, unit tests, coverage.
4. Manual checklist (all units) plus the U04 focus list below.
5. Rank findings Critical / High / Medium / Low.
6. Verdict: Pass / Conditional Pass / Fail.
7. Commit the audit report. Do not fix code in this session.

## U04 audit focus

- [ ] Care mistake at exactly 20 minutes zero hunger
- [ ] No double count
- [ ] Branch thresholds match plan
- [ ] Hooks into tick/reconcile
- [ ] Tests were committed before implementation (git log)
- [ ] No business logic in the wrong layer
- [ ] No secrets, no TODOs in claimed-complete code
- [ ] Claimed status matches CI

**Mandatory Critical re-audit even if findings are empty.**

## Exit criteria

- [ ] `docs/phase-logs/U04-evolution-care/U04-audit.md` committed
- [ ] Roadmap stage = Audit (move to Remediation if any findings, else Documentation)
- [ ] Critical findings recorded in the audit report; they block merge until remediated and re-audited

Next: `03-remediation.md` if findings exist; otherwise `04-documentation.md` (U3/U4 still need re-audit note).
