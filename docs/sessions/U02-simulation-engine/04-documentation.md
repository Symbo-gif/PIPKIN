# U02 Session 4 — Documentation: Core decay/tick simulation

- **Role:** Documentation agent
- **Inputs:** PR, `U02-audit.md`, `U02-remediation.md` (if any)
- **Template:** `docs/templates/session-04-documentation.md`
- **Output:** `docs/phase-logs/U02-simulation-engine/U02-closure.md`
- **Skill:** `.cursor/skills/pipkin-session-documentation/SKILL.md`

## Objective

Make the final U02 state legible to future agents. This session is the merge gate.

## Protocol

1. Update `AGENTS.md` and `.cursor/rules/` with decisions or gotchas from U02.
2. Update `docs/build-plan.md` / `docs/units/U02.md` if behavior or thresholds changed.
3. KDoc public APIs. Short module README if responsibility is not obvious.
4. Write the closure summary (status, artifact links, coverage, debt, sign-off, 2–3 sentence retrospective).
5. Set `docs/roadmap.md` U02 stage to `Closed`.
6. Merge the PR to `develop` after the closure file is committed. Do not merge without the closure file.

## U02 close-out checklist

- [ ] `docs/phase-logs/U02-simulation-engine/U02-closure.md` committed
- [ ] Roadmap row for U02 = Closed
- [ ] Dependency consumers (later units) can start only after this merge
- [ ] Retrospective captured

## Exit criteria

PR merged, closure committed, unit Closed on the roadmap.
