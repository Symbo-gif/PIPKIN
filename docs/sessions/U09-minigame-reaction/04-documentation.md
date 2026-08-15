# U09 Session 4 — Documentation: Mini-game reaction/timing

- **Role:** Documentation agent
- **Inputs:** PR, `U09-audit.md`, `U09-remediation.md` (if any)
- **Template:** `docs/templates/session-04-documentation.md`
- **Output:** `docs/phase-logs/U09-minigame-reaction/U09-closure.md`
- **Skill:** `.cursor/skills/pipkin-session-documentation/SKILL.md`

## Objective

Make the final U09 state legible to future agents. This session is the merge gate.

## Protocol

1. Update `AGENTS.md` and `.cursor/rules/` with decisions or gotchas from U09.
2. Update `docs/build-plan.md` / `docs/units/U09.md` if behavior or thresholds changed.
3. KDoc public APIs. Short module README if responsibility is not obvious.
4. Write the closure summary (status, artifact links, coverage, debt, sign-off, 2–3 sentence retrospective).
5. Set `docs/roadmap.md` U09 stage to `Closed`.
6. Merge the PR to `develop` after the closure file is committed. Do not merge without the closure file.

## U09 close-out checklist

- [ ] `docs/phase-logs/U09-minigame-reaction/U09-closure.md` committed
- [ ] Roadmap row for U09 = Closed
- [ ] Dependency consumers (later units) can start only after this merge
- [ ] Retrospective captured

## Exit criteria

PR merged, closure committed, unit Closed on the roadmap.
