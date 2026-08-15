# U06 Session 4 — Documentation: Notifications + Live Update

- **Role:** Documentation agent
- **Inputs:** PR, `U06-audit.md`, `U06-remediation.md` (if any)
- **Template:** `docs/templates/session-04-documentation.md`
- **Output:** `docs/phase-logs/U06-notifications/U06-closure.md`
- **Skill:** `.cursor/skills/pipkin-session-documentation/SKILL.md`

## Objective

Make the final U06 state legible to future agents. This session is the merge gate.

## Protocol

1. Update `AGENTS.md` and `.cursor/rules/` with decisions or gotchas from U06.
2. Update `docs/build-plan.md` / `docs/units/U06.md` if behavior or thresholds changed.
3. KDoc public APIs. Short module README if responsibility is not obvious.
4. Write the closure summary (status, artifact links, coverage, debt, sign-off, 2–3 sentence retrospective).
5. Set `docs/roadmap.md` U06 stage to `Closed`.
6. Merge the PR to `develop` after the closure file is committed. Do not merge without the closure file.

## U06 close-out checklist

- [ ] `docs/phase-logs/U06-notifications/U06-closure.md` committed
- [ ] Roadmap row for U06 = Closed
- [ ] Dependency consumers (later units) can start only after this merge
- [ ] Retrospective captured

## Exit criteria

PR merged, closure committed, unit Closed on the roadmap.
