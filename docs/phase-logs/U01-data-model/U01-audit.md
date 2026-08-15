# Audit report — U01

- Date: 2026-08-15
- Auditor session: independent Session 2 (not Session 1 implementer)
- PR: https://github.com/Symbo-gif/PIPKIN/pull/2 (`feature/U01-data-model` → `develop`)
- Spec: `docs/units/U01.md` + `docs/build-plan.md`
- Methodology: evidence collection, static analysis, manual checklist, claimed-vs-actual

## Verdict

**Conditional Pass**

Medium finding F1 must be fixed or explicitly accepted as debt in Session 3. No Critical/High findings; merge is not blocked on severity, but `GOVERNANCE.md` §2 still requires Remediation when the findings table is non-empty.

## Findings

| ID | Finding | Severity | Evidence | Recommended fix |
|----|---------|----------|----------|-----------------|
| F1 | `LoadResult.Success` stores `value: Any`, so mapper callers must unchecked-cast. This violates `.cursor/rules/kotlin-style.mdc` (“Avoid `Any` and unchecked casts”) and will be a ClassCastException hazard in U7/U11. | Medium | `LoadResult.kt` `Success(val value: Any)`; `PetStateMapperTest` / `HistoryLogMapperTest` cast `(loaded as LoadResult.Success).value` | Make `LoadResult` generic (`LoadResult<T>` with `Success(val value: T)`) or split pet/history result types. Add a compile-time regression that a `PetState` success is `LoadResult.Success<PetState>` (no `Any` cast). |
| F2 | `EvolutionBranch.UNASSIGNED` is not listed in `docs/build-plan.md` §3.3 (Kindred / Wild / Withered only). Needed as an egg/child sentinel, but it is a silent spec extension. | Low | `EvolutionBranch.kt`; fixtures default to `UNASSIGNED`; build plan §3.3 | In Session 3, add one sentence to `docs/build-plan.md` §3.3 (and U01/U04 unit docs if needed): egg/child store `UNASSIGNED` until the Child→Teen branch choice. |

## Coverage and CI

- Unit coverage: JaCoCo still not wired (gates start at U2 per `AGENTS.md`). U01 requires DAO + mapper tests, not 90% `:core` simulation coverage. Present and passing: 7 `PetState` tests, 3 `HistoryLog` tests, 1 `SaveSlots` test, 9 pet mapper tests, 4 history mapper tests, 8 in-memory Room DAO tests (Robolectric, minSdk 26), plus U00 smokes.
- CI URL / result: https://github.com/Symbo-gif/PIPKIN/actions/runs/31866967789 — `verify` and `instrumented` **SUCCESS** (2026-08-15T05:30:15Z / 05:30:27Z)
- ktlint/detekt: **PASS** locally (`.\gradlew.bat ktlintCheck detekt`) and on CI

### Local verification (auditor run, 2026-08-15)

```
.\gradlew.bat ktlintCheck detekt :core:test :app:testDebugUnitTest assembleDebug
→ BUILD SUCCESSFUL
```

## Manual checklist (U01 focus)

| Check | Result | Evidence |
|-------|--------|----------|
| Entity vs domain split | Pass | `PetState` / `HistoryLog` in `:core`; `*Entity` / `*Dao` / mappers in `:app` `com.pipkin.data` |
| DAO round-trips | Pass | Insert/read/update for `PetState`; insert/read for `HistoryLog`. History has no UPDATE — matches build-plan §3.5 append-only; not treated as a spec cut |
| Slot isolation | Pass | `writing_slot_one_does_not_change_slot_zero`; history `getForSlot` isolation |
| Corrupt save handling | Pass | Mapper contract is **reject** (`LoadResult.Corrupt`), not coerce. Out-of-range meters, future `lastSimulatedAtMillis`, negative timestamps, unknown enums, invalid slot ids. `SlotRecovery.clearSlot` is the crash-loop hook (pet row only; UI is U11) |
| No simulation math in DAOs | Pass | DAOs are parameterized Room queries only; no decay/tick/offline math in `:app` data |
| Tests committed before implementation | Pass | `8ebcbdd test:` → `d124c84 feat:` → `80f1ca5 docs:`. Feat commit does not modify `src/test` |
| No business logic in the wrong layer | Pass | Validation in `:core` constructors + mappers; Compose `MainActivity` unchanged (app name only). No Android imports in `:core` |
| No secrets, no TODOs | Pass | No `TODO`/`FIXME` in `*.kt`/`*.kts`; no `google-services.json` / API keys |
| Claimed status matches CI | Pass | Session 1 marked CI green; `verify` is SUCCESS. PR body still has an unchecked “CI verify green” box — stale markdown only |

### Additional checks

| Check | Result | Evidence |
|-------|--------|----------|
| Three save slots `0..2` | Pass | `SaveSlots`; domain `require`; mapper corrupt on slot 3/4 |
| `PetState` / `HistoryLog` minimum fields | Pass | All U01-listed fields present on domain + entity |
| In-memory Room | Pass | Robolectric `Room.inMemoryDatabaseBuilder` (spec allows androidTest or Robolectric) |
| Type converters vs corrupt enums | Pass | Entities persist enum **names as TEXT**; unknown names become `LoadResult.Corrupt` instead of Room `enumValueOf` crash |
| `:core` has no Android imports | Pass | `grep import android.` / `androidx.` under `core/` — no matches |
| Persistence is local Room | Pass | `PipKinDatabase.NAME = "pipkin.db"`; schema exported `app/schemas/.../1.json` |
| Detekt `LongParameterList` | Informational | `constructorThreshold`/`functionThreshold` raised to 16 so `PetState`’s spec-mandated constructor can exist. Constructor limit is justified; function limit is looser than U00’s `threshold: 8` |
| Session 1 plan artifact | Informational | No committed plan in `docs/phase-logs/U01-data-model/`. Reject-vs-coerce is documented in tests and the PR body |
| Production `Room.databaseBuilder` wiring | Out of scope | Database/DAO types exist; Application singleton/onboarding is U11 |
| CodeRabbit | Informational | Review skipped (non-default-branch policy); not used as evidence |

## Claimed vs actual

| Claim (Session 1 / PR) | Actual |
|------------------------|--------|
| TDD order: `test:` then `feat:` | Confirmed in `git log develop..HEAD` (`8ebcbdd` then `d124c84`) |
| Mapper **rejects** out-of-range meters (not coerce) | Confirmed in mapper tests and `PetStateMapper.findCorruption` |
| Future `lastSimulatedAtMillis` is corrupt | Confirmed (`mapper_rejects_entity_with_future_last_simulated_timestamp`) |
| Writing slot 1 does not change slot 0 | Confirmed |
| `SlotRecovery.clearSlot` deletes only the requested slot | Confirmed |
| Local gradle ktlint, detekt, tests, assembleDebug green | Confirmed by auditor |
| CI `verify` green | Confirmed: run 31866967789 SUCCESS. PR test-plan checkbox still unchecked |
| Roadmap U1 = Coding, ready for Session 2 | Confirmed at audit start |

## Re-audit required

No for a full Critical re-audit (U3/U4 only). After Session 3, re-audit **F1** only if it is still open or if the remediator upgrades severity; F1 is Medium so independent re-audit is not mandatory. Re-verify F1 if the remediator’s fix is non-trivial.

## Roadmap action

U01 cycle stage updated from **Coding** → **Remediation** (Conditional Pass; findings exist, so Session 3 is required per `GOVERNANCE.md` §2).
