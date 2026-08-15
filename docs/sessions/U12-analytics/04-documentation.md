# U12 Session 4 — Documentation: Firebase analytics events

- **Role:** Documentation agent
- **Inputs:** PR, `U12-audit.md`, `U12-remediation.md` (if any)
- **Template:** `docs/templates/session-04-documentation.md`
- **Output:** `docs/phase-logs/U12-analytics/U12-closure.md`
- **Skill:** `.cursor/skills/pipkin-session-documentation/SKILL.md`

## Objective

Make the final U12 state legible to future agents. This session is the merge gate.

## Protocol

1. Update `AGENTS.md` and `.cursor/rules/` with decisions or gotchas from U12.
2. Update `docs/build-plan.md` / `docs/units/U12.md` if behavior or thresholds changed.
3. KDoc public APIs. Short module README if responsibility is not obvious.
4. Write the closure summary (status, artifact links, coverage, debt, sign-off, 2–3 sentence retrospective).
5. Set `docs/roadmap.md` U12 stage to `Closed`.
6. Merge the PR to `develop` after the closure file is committed. Do not merge without the closure file.

## U12 close-out checklist

- [ ] `docs/phase-logs/U12-analytics/U12-closure.md` committed
- [ ] Roadmap row for U12 = Closed
- [ ] Dependency consumers (later units) can start only after this merge
- [ ] Retrospective captured

## Exit criteria

PR merged, closure committed, unit Closed on the roadmap.
