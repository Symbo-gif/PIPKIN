# Multi-Agent Cursor Development Methodology — Android Virtual Pet Game

This document expands the build plan into an execution-ready methodology for a team of Cursor coding agents, structured around a strict four-session cycle applied to every feature/phase: **Code & Test → Audit → Remediation → Documentation**. It assumes agents operate largely autonomously with human checkpoints at cycle boundaries.

## 1. Repository & Environment Setup (Phase 0, do once)

- Initialize Git repo with `main`, `develop`, and short-lived `feature/*` branches; use Git worktrees so multiple Cursor agents can work in parallel without file collisions[cite:56][cite:58].
- Create `AGENTS.md` at repo root: canonical rules file read by every agent, containing coding standards, architecture decisions, module boundaries, naming conventions, and forbidden patterns (mirrors the CLAUDE.md/AGENTS.md pattern used in 2026 agentic workflows)[cite:56][cite:58].
- Create `.cursor/rules/` directory with scoped rule files: `kotlin-style.md`, `testing-standards.md`, `android-architecture.md`, `security.md`. Keep critical review criteria in rule files, not just chat, so any agent picks them up automatically[cite:53].
- Set up CI (GitHub Actions): lint (ktlint/detekt), unit test run, instrumented test run, build verification, on every PR[cite:47].
- Define branch protection: no direct pushes to `main`/`develop`; PRs require passing CI + at least one audit sign-off before merge[cite:47][cite:52].
- Establish a `/docs/phase-logs/` directory where every phase's audit report, remediation log, and closing documentation is committed as permanent record (traceability for the audit-remediation loop)[cite:60][cite:52].

## 2. Task Decomposition Into Phases

Break the build plan's 7 milestones into granular, independently cyclable units so each can go through the full 4-session methodology without becoming unwieldy. Recommended decomposition:

| Unit ID | Scope | Depends On |
|---|---|---|
| U1 | Data model & Room persistence layer (PetState, HistoryLog entities, DAOs) | none |
| U2 | Core decay/tick simulation engine (pure Kotlin, no Android deps) | U1 |
| U3 | Offline-progress reconciliation logic (timestamp delta → catch-up simulation) | U2 |
| U4 | Evolution/branching logic + care-mistake tracking | U2 |
| U5 | WorkManager periodic checks + AlarmManager critical alerts | U3 |
| U6 | Notification layer (standard + Live Update) | U5 |
| U7 | Compose UI: main pet screen, meters, action buttons | U1, U2 |
| U8 | Mini-game 1 (Catch the Food) | U7 |
| U9 | Mini-game 2 (reaction/timing) | U7 |
| U10 | Death/rebirth flow + pet history log | U4, U7 |
| U11 | Onboarding + save-slot/local persistence wiring | U1, U7 |
| U12 | Analytics instrumentation (Firebase events) | U2, U4, U10 |
| U13 | End-to-end integration pass + beta build | all above |

Each unit is assigned to one agent (or an agent pair: implementer + test-writer) and run through the full four-session cycle before merge into `develop`.

## 3. Session 1 — Coding & Testing (paired, TDD-first)

**Objective:** Produce implementation and its test suite together, written to industry-standard rigor, following strict TDD ordering.

**Protocol per unit (based on Cursor's own agent best practices and TDD-with-agents research)[cite:51][cite:48][cite:54]:**

1. **Explore.** Agent reads relevant existing code, the unit's spec section in the build plan, and relevant `.cursor/rules/` files. No code written yet.
2. **Plan Mode.** Agent produces a written implementation plan (files to touch, function signatures, data flow, edge cases) and posts it for human/lead-agent approval before any file is modified[cite:51][cite:56].
3. **Write tests first.** Agent writes unit tests based on explicit input/output pairs and edge cases (e.g., "hunger decays 2 points/hour," "offline gap of 96 hours capped at 72 hours simulated," "care mistake triggers at exactly 20 minutes of zero-hunger"). Explicitly instruct the agent not to write any mock/stub implementation logic at this stage[cite:51].
4. **Confirm red state.** Agent runs the test suite and confirms all new tests fail for the expected reason (missing implementation, not a broken test). Do not allow implementation code to be written yet[cite:51].
5. **Commit tests** as a distinct commit (`test: add failing tests for <unit>`)[cite:51].
6. **Implement.** Agent writes implementation code to satisfy the tests, explicitly instructed not to modify the committed tests. Iterate until all tests pass[cite:51].
7. **Commit implementation** separately (`feat: implement <unit>`)[cite:51].
8. **Test coverage requirements (non-negotiable minimums):**
   - Unit tests: all pure logic (decay curves, evolution branching, offline-catchup math) ≥ 90% line coverage.
   - Integration tests: Room DAO round-trips, WorkManager scheduling triggers.
   - Instrumented UI tests (Compose test framework): critical user flows (feed action updates meter, death screen appears, notification fires).
   - Property-based/fuzz tests for the offline-progress reconciliation function given its correctness is safety-critical to the whole game feel.
9. **Descriptive naming.** Test names must state the exact behavior under test (e.g., `hunger_decays_correctly_after_72_hour_offline_gap_with_cap_applied`), per agentic-TDD guidance that clear names improve agent accuracy on subsequent iterations[cite:54].
10. **Context hygiene.** If the agent's context window approaches ~70% saturation mid-unit, checkpoint progress into a short summary note in `/docs/phase-logs/`, then open a fresh agent session rather than continuing in a degraded context[cite:53].

**Session 1 exit criteria:** all tests green in CI, PR opened against `develop`, implementation and test commits present and separated, no TODOs/placeholders left in code.

## 4. Session 2 — Audit Phase

**Objective:** Independently verify the unit's actual state against both the build plan's claimed scope and industry code-quality standards — performed by a *different* agent/session than the one that implemented it, to avoid self-grading bias.

**Protocol (adapted from SDLC/code-quality audit methodology)[cite:52][cite:55][cite:60]:**

1. **Define audit scope.** Confirm which unit/PR is under audit and re-read its original spec section verbatim from the build plan document — this is the ground truth the implementation is measured against[cite:52].
2. **Evidence collection.** Audit agent gathers: the PR diff, CI results, test coverage report, and any linked design decisions in `AGENTS.md`[cite:52][cite:60].
3. **Automated analysis.** Run static analysis (detekt/ktlint), dependency vulnerability scan, and coverage tooling; treat these as objective inputs, not final verdicts[cite:55][cite:60].
4. **Manual review against checklist**, covering at minimum:
   - **Spec conformance:** does implemented behavior match every stated requirement in the build plan for this unit? Flag any silent scope reduction or invented behavior not in the spec.
   - **Test integrity:** were tests written before implementation (check commit order/timestamps)? Do tests actually exercise claimed edge cases, or are they superficial/tautological?
   - **Code quality:** naming, architecture layering (is business logic leaking into UI/Compose layer?), error handling, no dead code, no TODOs.
   - **Security/robustness:** input validation, safe handling of corrupted save state, no hardcoded secrets.
   - **Performance:** decay/tick calculations run in bounded time, no main-thread blocking I/O.
   - **Claimed vs actual status:** cross-check any agent self-report ("Unit complete, all tests passing") against the actual CI run and diff — this is the core anti-hallucination check for agent-produced status claims[cite:52].
5. **Risk-rank findings** as Critical / High / Medium / Low, consistent with standard audit gap-report format[cite:52][cite:60].
6. **Produce the Audit Report** (committed to `/docs/phase-logs/<unit>-audit.md`) containing: scope, methodology, findings table (finding, severity, evidence, recommended fix), and an overall verdict (Pass / Conditional Pass / Fail) — mirroring the standard SDLC audit gap-report structure[cite:52].

**Session 2 exit criteria:** audit report committed; verdict determines whether the unit proceeds to Remediation (if any findings) or skips directly to Documentation (rare, only if zero findings).

## 5. Session 3 — Remediation Phase

**Objective:** Address every finding from the audit report with tracked, verifiable fixes — not just patches, but confirmation the original test suite plus any new regression tests pass.

**Protocol[cite:52][cite:60]:**

1. **Assign ownership per finding.** Each finding in the audit report gets a remediation task; a fresh agent session (or the original implementer, depending on severity/complexity) is assigned.
2. **Prioritize by severity.** Critical/High findings block merge; Medium/Low may be ticketed for a follow-up unit if truly non-blocking, but this must be explicitly justified and logged, not silently deferred[cite:52][cite:55].
3. **Fix + regression test.** For every finding, the remediating agent writes a new test that specifically reproduces the audited defect (red), then fixes the code (green) — same TDD discipline as Session 1, applied to bug-fixing[cite:51][cite:54].
4. **No silent scope changes.** If remediation reveals the original spec itself was ambiguous or wrong, that must be raised explicitly and the build plan updated (with a note in the doc), not worked around silently.
5. **Re-run full existing test suite** (not just new tests) to confirm no regressions were introduced.
6. **Produce Remediation Log** (`/docs/phase-logs/<unit>-remediation.md`): maps each finding to its fix commit hash, the new regression test name, and confirmation status.
7. **Re-audit trigger.** Critical/High findings require the Session 2 auditor (or another independent agent) to re-verify the specific fix before sign-off — closing the loop, per standard "verification and re-audit" audit practice[cite:52].

**Session 3 exit criteria:** all Critical/High findings verified fixed and re-audited; Medium/Low findings either fixed or explicitly logged as accepted technical debt with rationale.

## 6. Session 4 — Documentation Phase (Close-Out)

**Objective:** Produce the permanent record that makes the unit's final state legible to future agents and humans, and formally close the cycle.

**Protocol:**

1. **Update `AGENTS.md`/rules files** with any new architectural decisions, conventions, or gotchas discovered during this unit (context hygiene for future agent sessions)[cite:58].
2. **Update the build plan document** itself if scope, thresholds, or design decisions changed during remediation (single source of truth must stay accurate).
3. **Write/update module-level documentation**: KDoc comments on public functions/classes, a short README in the module's directory explaining its responsibility and how it fits the overall architecture.
4. **Write a Phase Closure Summary** (`/docs/phase-logs/<unit>-closure.md`) containing: final status, links to code/test/audit/remediation artifacts, test coverage achieved, any accepted technical debt, and sign-off.
5. **Merge to `develop`** only after closure doc is committed — this is the literal gate that ends the cycle.
6. **Retrospective note** (2-3 sentences): what went well, what to change in the next unit's cycle — feeds back into refining `AGENTS.md` and this methodology itself.

**Session 4 exit criteria:** PR merged, closure doc committed, `AGENTS.md` updated if applicable, unit marked complete in the roadmap tracker.

## 7. Cycle Governance & Roles

| Role | Responsibility | Agent Assignment Pattern |
|---|---|---|
| Implementer agent | Session 1 coding + tests | Fresh context per unit; uses worktree/branch |
| Auditor agent | Session 2 audit | Must be a different session/context than implementer to avoid self-grading bias[cite:52] |
| Remediator agent | Session 3 fixes | Can be implementer or fresh agent depending on severity |
| Documentation agent | Session 4 closure | Can be any agent; benefits from full context of the other three sessions' logs |
| Human lead (you) | Approves Plan Mode outputs, reviews audit verdicts on Critical findings, merges final PRs | Checkpoint gate at Session 1 plan approval and Session 2 verdict |

Use parallel Cursor cloud agents/worktrees to run independent units (e.g., U8 mini-game and U6 notifications) concurrently once their dependencies are met, following the parallel-worktree pattern common in 2026 agentic workflows, while keeping each unit's four-session cycle sequential and self-contained[cite:56][cite:58].

## 8. Cadence & Tracking

- Maintain a single roadmap tracker (`/docs/roadmap.md` or a project board) listing all 13 units, their current cycle stage (Coding/Audit/Remediation/Documentation/Closed), and links to each phase-log artifact.
- Run a **biweekly "debloat" audit pass** across the whole codebase (no new features, pure cleanup and drift-detection between `AGENTS.md` claims and actual code), independent of per-unit audits, to catch systemic issues the per-unit cycle might miss[cite:58].
- Gate the overall roadmap phases (Prototype → Offline/Notifications → Evolution/Mini-games → Polish → Beta → Launch) on all constituent units having reached "Closed" status — no roadmap phase is marked done while any of its units sit in Audit or Remediation.

## 9. Adaptation Notes for This Specific Game

- The offline-progress reconciliation logic (U3) and evolution branching (U4) are the highest-risk units — they are the "safety-critical" core of the whole product's feel, and should receive the most aggressive audit scrutiny (mandatory fuzz/property-based testing, mandatory Critical-severity re-audit even if no findings are flagged, given how much of the original Tamagotchi's appeal rested on this logic behaving correctly)[cite:6][cite:5].
- Notification logic (U6) audits must specifically check against Android's Live Update guidance (no repeat/non-actionable spam) since this was flagged as a churn risk in the original build plan.
- Analytics instrumentation (U12) audit should verify event schemas match exactly what the tuning/beta-testing plan requires — a mismatch here silently invalidates the whole beta-tuning phase.
