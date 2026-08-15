# U03 Session 4 — Documentation: Offline-progress reconciliation

- **Role:** Documentation agent
- **Inputs:** PR, `U03-audit.md`, `U03-remediation.md` (if any)
- **Template:** `docs/templates/session-04-documentation.md`
- **Output:** `docs/phase-logs/U03-offline-reconciliation/U03-closure.md`
- **Skill:** `.cursor/skills/pipkin-session-documentation/SKILL.md`

## Objective

Make the final U03 state legible to future agents. This session is the merge gate.

## Protocol

1. Update `AGENTS.md` and `.cursor/rules/` with decisions or gotchas from U03.
2. Update `docs/build-plan.md` / `docs/units/U03.md` if behavior or thresholds changed.
3. KDoc public APIs. Short module README if responsibility is not obvious.
4. Write the closure summary (status, artifact links, coverage, debt, sign-off, 2–3 sentence retrospective).
5. Set `docs/roadmap.md` U03 stage to `Closed`.
6. Merge the PR to `develop` after the closure file is committed. Do not merge without the closure file.

## U03 close-out checklist

- [ ] `docs/phase-logs/U03-offline-reconciliation/U03-closure.md` committed
- [ ] Roadmap row for U03 = Closed
- [ ] Dependency consumers (later units) can start only after this merge
- [ ] Retrospective captured

## Exit criteria

PR merged, closure committed, unit Closed on the roadmap.
