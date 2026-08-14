# U13 Session 4 — Documentation: End-to-end integration + beta build

- **Role:** Documentation agent
- **Inputs:** PR, `U13-audit.md`, `U13-remediation.md` (if any)
- **Template:** `docs/templates/session-04-documentation.md`
- **Output:** `docs/phase-logs/U13-integration-beta/U13-closure.md`
- **Skill:** `.cursor/skills/pipkin-session-documentation/SKILL.md`

## Objective

Make the final U13 state legible to future agents. This session is the merge gate.

## Protocol

1. Update `AGENTS.md` and `.cursor/rules/` with decisions or gotchas from U13.
2. Update `docs/build-plan.md` / `docs/units/U13.md` if behavior or thresholds changed.
3. KDoc public APIs. Short module README if responsibility is not obvious.
4. Write the closure summary (status, artifact links, coverage, debt, sign-off, 2–3 sentence retrospective).
5. Set `docs/roadmap.md` U13 stage to `Closed`.
6. Human lead merges to `develop`. Do not merge without the closure file.

## U13 close-out checklist

- [ ] `docs/phase-logs/U13-integration-beta/U13-closure.md` committed
- [ ] Roadmap row for U13 = Closed
- [ ] Dependency consumers (later units) can start only after this merge
- [ ] Retrospective captured

## Exit criteria

PR merged, closure committed, unit Closed on the roadmap.
