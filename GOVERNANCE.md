# PipKin Governance

This is the human- and agent-facing process contract. Product behavior lives in `docs/build-plan.md`. Agent coding rules live in `AGENTS.md`. Cycle mechanics live in `agentic-dev-methodology.md`.

## 1. Roles

| Role | Who | Authority |
|------|-----|-----------|
| Human lead | MichaelMaillet | Approves Session 1 plans; reviews Critical audit findings; merges to `develop`/`main` |
| Implementer | Fresh agent session per unit | Session 1 only: explore, plan, TDD, PR against `develop` |
| Auditor | Different session/context than implementer | Session 2: independent audit report and verdict |
| Remediator | Implementer or fresh agent | Session 3: fix findings with regression tests |
| Documentation agent | Any agent with the three prior logs | Session 4: closure, rule updates, merge gate |

An agent must not audit its own implementation. Critical/High remediations must be re-verified by an auditor session before sign-off.

## 2. Four-session loop (every unit, no exceptions)

```
Session 1 Code & Test → Session 2 Audit → Session 3 Remediation → Session 4 Documentation → merge to develop
```

| Session | Brief | Required artifact | Exit gate |
|---------|-------|-------------------|-----------|
| 1 Code & Test | `docs/sessions/<unit>/01-code-and-test.md` | PR with separated `test:` then `feat:` commits | Human approved the plan; CI green; no TODOs |
| 2 Audit | `docs/sessions/<unit>/02-audit.md` | `docs/phase-logs/<unit>/<unit>-audit.md` | Verdict recorded (Pass / Conditional Pass / Fail) |
| 3 Remediation | `docs/sessions/<unit>/03-remediation.md` | `docs/phase-logs/<unit>/<unit>-remediation.md` | All Critical/High fixed and re-audited; Medium/Low fixed or logged as debt |
| 4 Documentation | `docs/sessions/<unit>/04-documentation.md` | `docs/phase-logs/<unit>/<unit>-closure.md` | Closure committed; then merge to `develop` |

Skip Session 3 only when Session 2 verdict is Pass with zero findings. U3 and U4 still require a Critical re-audit even with zero findings.

## 3. Branching

- `main` — release history only.
- `develop` — integration branch. Units merge here after Session 4.
- `feature/Uxx-short-name` — one unit, one branch, one four-session cycle.
- No direct pushes to `main` or `develop`.
- PRs into `develop` require passing CI and an audit sign-off (Pass or Conditional Pass with debt logged).
- PRs into `main` require all units for that roadmap phase to be Closed.

Git worktrees are the supported way to run independent units in parallel. Do not start a dependent unit until its upstream units are Closed.

## 4. Checkpoints the human must hit

1. Session 1 Plan Mode output — approve before files are modified.
2. Session 2 verdict — required review if any Critical finding exists.
3. Session 4 merge — human merges (or explicitly authorizes merge) after closure is committed.

## 5. Status vocabulary

Use only these stage names in `docs/roadmap.md`:

`Not started` → `Coding` → `Audit` → `Remediation` → `Documentation` → `Closed`

A roadmap phase (Prototype, Offline/Notifications, Evolution/Mini-games, Polish, Beta, Launch) is done only when every constituent unit is `Closed`.

## 6. Debloat pass

Every two weeks, run a repo-wide cleanup with no new features: drift between `AGENTS.md` and the code, dead modules, unused deps, stale TODOs. Log it under `docs/phase-logs/debloat/`.

## 7. Spec change rule

If implementation or audit shows the spec is wrong or ambiguous:

1. Stop coding the workaround.
2. Propose the change in the unit's phase-log.
3. Update `docs/build-plan.md` in the same PR that implements the new truth.
4. Note the change in Session 4 closure.

## 8. High-risk units

- **U3 Offline reconciliation** and **U4 Evolution/care mistakes** are safety-critical to PipKin's feel. Mandatory property/fuzz tests (U3). Mandatory Critical re-audit even if clean.
- **U6 Notifications** must be audited against Android Live Update guidance: no repeat or non-actionable spam.
- **U12 Analytics** event schemas must match the beta-tuning plan in `docs/build-plan.md`. A mismatch invalidates beta.

## 9. Starting a session

1. Read `AGENTS.md`, `GOVERNANCE.md`, and the unit spec in `docs/units/`.
2. Open the matching brief under `docs/sessions/<unit>/`.
3. Follow the session skill in `.cursor/skills/pipkin-session-*`.
4. Do not begin Session N+1 until Session N exit criteria are met and the artifact is in `docs/phase-logs/`.
