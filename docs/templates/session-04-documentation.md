# Session 4 template — Documentation

Merge gate: closure doc committed before merge to `develop`.

## Header

- Unit:
- Role: Documentation agent
- Inputs: PR, audit report, remediation log

## Protocol

1. Update `AGENTS.md` / `.cursor/rules/` with new decisions or gotchas.
2. Update `docs/build-plan.md` if thresholds or behavior changed.
3. KDoc on public APIs; module README if the module's responsibility shifted.
4. Write `docs/phase-logs/<unit>/<unit>-closure.md` from `docs/templates/closure.md`.
5. Set `docs/roadmap.md` stage to `Closed`.
6. Documentation agent merges the PR to `develop` (`gh pr merge`).
7. Retrospective: 2–3 sentences in the closure doc.

## Exit criteria

- [ ] Closure committed
- [ ] Roadmap = Closed
- [ ] PR merged to `develop`
