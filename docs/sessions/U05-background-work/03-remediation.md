# U05 Session 3 — Remediation: WorkManager & AlarmManager

- **Role:** Remediator (implementer or fresh session; fresh preferred for Critical)
- **Input:** `docs/phase-logs/U05-background-work/U05-audit.md`
- **Template:** `docs/templates/session-03-remediation.md`
- **Output:** `docs/phase-logs/U05-background-work/U05-remediation.md`
- **Skill:** `.cursor/skills/pipkin-session-remediation/SKILL.md`

## Objective

Close every audit finding with a tracked fix. Critical/High block merge.

## Protocol

1. List findings as tasks. Severity order: Critical → High → Medium → Low.
2. For each fix: write a regression test that fails on the defect, then fix the code. Commits: `test: regress U05 <finding-id>` then `fix: U05 <finding-id>`.
3. If the spec is wrong, stop and update `docs/build-plan.md` / `docs/units/U05.md` — do not silently reinterpret.
4. Re-run the full test suite.
5. Fill the remediation log (finding → commit → test name → status).
6. Independent re-audit of Critical/High. Re-audit only Critical/High findings after remediation.

## U05 notes

Risk level: Medium.
Focus leftover defects against:

- [ ] Worker calls reconciler then persists
- [ ] Unique periodic work
- [ ] Alarms cancelled when clear
- [ ] No main-thread I/O

Medium/Low may be deferred only with rationale in the log and a follow-up unit id.

## Exit criteria

- [ ] Remediation log committed
- [ ] Critical/High re-audited pass
- [ ] Full suite green
- [ ] Roadmap stage = Remediation (ready for Session 4)

Next: `04-documentation.md`.
