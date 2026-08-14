# U06 Session 3 — Remediation: Notifications + Live Update

- **Role:** Remediator (implementer or fresh session; fresh preferred for Critical)
- **Input:** `docs/phase-logs/U06-notifications/U06-audit.md`
- **Template:** `docs/templates/session-03-remediation.md`
- **Output:** `docs/phase-logs/U06-notifications/U06-remediation.md`
- **Skill:** `.cursor/skills/pipkin-session-remediation/SKILL.md`

## Objective

Close every audit finding with a tracked fix. Critical/High block merge.

## Protocol

1. List findings as tasks. Severity order: Critical → High → Medium → Low.
2. For each fix: write a regression test that fails on the defect, then fix the code. Commits: `test: regress U06 <finding-id>` then `fix: U06 <finding-id>`.
3. If the spec is wrong, stop and update `docs/build-plan.md` / `docs/units/U06.md` — do not silently reinterpret.
4. Re-run the full test suite.
5. Fill the remediation log (finding → commit → test name → status).
6. Independent re-audit of Critical/High. Re-audit only Critical/High findings after remediation.

## U06 notes

Risk level: High.
Focus leftover defects against:

- [ ] No repeat non-actionable spam
- [ ] Dedupe until condition clears
- [ ] Live Update updates in place
- [ ] Actionable copy only

Medium/Low may be deferred only with rationale in the log and a follow-up unit id.

## Exit criteria

- [ ] Remediation log committed
- [ ] Critical/High re-audited pass
- [ ] Full suite green
- [ ] Roadmap stage = Remediation (ready for Session 4)

Next: `04-documentation.md`.
