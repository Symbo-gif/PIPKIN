# U00 Session 1 — Code & Test: Repository & environment setup

- **Role:** Implementer (fresh context). Do not audit your own work afterward.
- **Branch:** `feature/U00-environment-setup`
- **Depends on:** none
- **Risk:** Low
- **Spec (read first):** `docs/units/U00.md`, `docs/build-plan.md`, `AGENTS.md`
- **Protocol template:** `docs/templates/session-01-code-test.md`
- **Skill:** `.cursor/skills/pipkin-session-code-test/SKILL.md`

## Objective

Close environment setup so U1 can start. Most scaffolding already landed (see `docs/phase-logs/U00-environment-setup/checkpoint.md`). Remaining Session 1 work: compileSdk 36 toolchain, Gradle/CI confirmation, agent-owned remote and branch protection, no feature code.

## Explore (no file edits)

1. Read the spec and build-plan sections this unit owns.
2. Read existing code on the dependency units.
3. Read `.cursor/rules/` (kotlin, testing, architecture, security).

## Plan (write before edits)

Publish a plan covering: files to touch, function signatures, data flow, edge cases, test names. Expected files:

- settings.gradle.kts
- app/build.gradle.kts
- core/build.gradle.kts
- .github/workflows/ci.yml

Unit-specific focus:

- Do not invent feature code
- Keep skeleton compiling
- ktlint/detekt tasks must exist

## TDD

1. Write tests from the spec's named cases and edge list. No mocks that replace the behavior under test.
2. Run tests. Confirm red (missing implementation).
3. Commit: `test: add failing tests for U00 Repository & environment setup`
4. Implement without editing those tests.
5. Commit: `feat: implement U00 Repository & environment setup`
6. Open PR against `develop`. CI green. Zero TODOs.

## Already landed (do not recreate)

Governance, unit specs, 14×4 session briefs, `:core`/`:app` skeleton, CI workflow, Cursor rules and session skills. `:core:test` has been run green locally.

## Remaining before Session 2

- [x] First commit on `main`, then `develop` (bootstrap `0bcb507`)
- [x] `./gradlew :core:test ktlintCheck detekt` green
- [x] `assembleDebug` green where an Android SDK exists (required on CI)
- [ ] Remote + branch protection applied from `docs/governance/branch-protection.md` (agent-owned)

## Exit criteria

- [x] Plan written before edits
- [x] Smoke test `core_module_loads` present
- [ ] CI workflow present and green on the first PR
- [x] Roadmap stage for U00 = Coding (ready for Session 2)

Next: `02-audit.md` in a **different** agent session.
