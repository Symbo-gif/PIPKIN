# PipKin Build Plan

Single source of truth for product and technical behavior. Audits measure implementations against this document, not against chat. Process lives in `agentic-dev-methodology.md` and `GOVERNANCE.md`.

Game name: **PipKin**. Platform: Android. Genre: virtual pet (Tamagotchi-class: persistent care, decay while away, evolution from how you care, death and rebirth).

## 1. Product intent

PipKin is a small companion you keep alive by feeding, cleaning, playing, and sleeping it. Care quality changes how it grows. Neglect has consequences. The loop must feel honest when the app is closed: offline time is simulated, capped, and never silently rewritten.

## 2. Locked mechanics

These values are methodology-locked. Changing them requires an explicit spec update and a phase-log note.

| Rule | Value |
|------|--------|
| Hunger decay | 2 points per hour |
| Meter range | integers 0–100 inclusive |
| Offline simulation cap | 72 hours of simulated time, even if the real gap is 96 hours (or any longer gap) |
| Care mistake | triggers at exactly 20 minutes of continuous hunger == 0 |
| Persistence | local only for v1 (Room). No account server. |
| Simulation | pure Kotlin in `:core`, no Android types |

## 3. Proposed mechanics (Phase 0 defaults)

The methodology named hunger, care mistakes, evolution, death/rebirth, two mini-games, notifications, save slots, and analytics. The following fills remaining Tamagotchi-class gaps so units can be specified. Treat as approved defaults unless `docs/build-plan.md` is revised before the owning unit's Session 1.

### 3.1 Meters

| Meter | Idle decay | Primary recovery action | Zero-state consequence |
|-------|------------|-------------------------|------------------------|
| Hunger | 2 / hour | Feed (mini-game or quick feed) | After 20 minutes at 0: care mistake; continued 0 contributes to death clock |
| Happiness | 1 / hour | Play (mini-games, pet) | Low happiness biases evolution toward neglected branch |
| Energy | 1 / hour while awake | Sleep (user starts rest; recovers while resting) | At 0 the pet is forced-resting; actions locked |
| Cleanliness | 1 / 2 hours | Clean | Low cleanliness increases illness chance (health drain) |
| Health | no idle decay | Recovers slowly when other meters are healthy | At 0: death |

Death clock (proposed): if hunger stays at 0 for 24 simulated hours after the first care mistake in that episode, or health hits 0, the pet dies.

### 3.2 Tick model

- Canonical tick length: 1 minute of simulated time.
- Online: apply ticks from `lastSimulatedAt` to now on a background-safe clock, then persist.
- Offline: same function, with simulated duration `min(elapsed, 72 hours)`.
- The catch-up function must be deterministic: same `(PetState, elapsedMillis, cap)` → same `PetState`.

### 3.3 Evolution

Stages: Egg → Child → Teen → Adult.

- Stage advances by simulated age (Egg 24h, Child 3d, Teen 4d, Adult thereafter) unless dead.
- At Child→Teen and Teen→Adult, branch is chosen from care-mistake count and average meter health since last branch.
- Branches (v1): **Kindred** (well cared), **Wild** (play-heavy, messy), **Withered** (neglect). Visual + idle animation differ; meters stay the same system.

### 3.4 Care mistakes

- Counter on `PetState.careMistakeCount`.
- Increment once when hunger has been 0 for 20 consecutive simulated minutes.
- Do not increment again until hunger has been > 0 and then returned to 0 for another 20 minutes.
- Logged as a `HistoryLog` event.

### 3.5 Death, rebirth, history

- Death produces a `HistoryLog` entry (name, stage, branch, age, mistake count, cause, timestamps).
- Rebirth starts a new egg in the same save slot. History is append-only.
- UI shows a death screen before rebirth is offered.

### 3.6 Mini-games

1. **Catch the Food** — timed tap/drag to catch falling food. Success raises hunger (and a little happiness). Failure is a small hunger gain or none; never punitive death.
2. **Reaction / timing** — wait for the tell, tap in the window. Success raises happiness (and a little energy cost).

Both are launched from the main pet screen. They write results back through `:core` (no meter math in the game UI).

### 3.7 Save slots

- Three local slots.
- Onboarding: name the pet, pick slot, hatch.
- Switching slots persists the current slot first.

### 3.8 Notifications

- Periodic WorkManager check (suggested 15–30 minutes, OS-constrained) runs catch-up and may notify.
- AlarmManager only for imminent death / 20-minute hunger-zero care-mistake window when the app is not in foreground.
- Live Update (where the OS supports it): single ongoing pet status, updated in place.
- Forbidden: repeating the same non-actionable notification; notifying more than once per distinct condition until that condition clears.

### 3.9 Analytics (Firebase)

Schema must match this list. Adding or renaming events is a spec change.

| Event | Params |
|-------|--------|
| `pet_hatched` | slot_id, pet_id |
| `care_action` | action (`feed`,`clean`,`sleep`,`play`,`pet`), meters_after |
| `care_mistake` | hunger_zero_minutes, total_mistakes |
| `evolution_branch` | from_stage, to_stage, branch |
| `pet_died` | cause, age_hours, mistakes, branch |
| `pet_reborn` | previous_pet_id, slot_id |
| `minigame_completed` | game_id, success, score |
| `offline_catchup` | elapsed_hours, simulated_hours, capped |

No names, free-text, or precise location. `pet_id` is a local UUID.

## 4. Technical architecture

```
:core  (JVM)     model, simulation, offline, evolution
:app   (Android) Room, WorkManager, AlarmManager, notifications, Compose, Firebase
```

- `PetState` (domain) in `:core`. `PetStateEntity` in `:app` with mapper.
- `HistoryLog` same split.
- Single catch-up entry point: `OfflineReconciler.reconcile(state, nowMillis): PetState`.
- UI: Jetpack Compose, Material 3, one main pet screen with meters + actions.

minSdk 26, target/compileSdk 36, Kotlin 2.x, Gradle version catalog.

## 5. Milestones vs units

| Roadmap phase | Units that must be Closed |
|---------------|---------------------------|
| Prototype | U1, U2, U7 |
| Offline / Notifications | U3, U5, U6 |
| Evolution / Mini-games | U4, U8, U9, U10 |
| Polish | U11 |
| Beta | U12, U13 |
| Launch | U13 Closed + store listing (human) |

U13 is the integration/beta build gate for Beta and the technical gate for Launch.

## 6. Quality bars

- `:core` simulation/offline/evolution line coverage ≥ 90%.
- U3: property-based tests on gap, cap, floor/ceiling.
- Instrumented: feed updates hunger meter; death screen appears; a notification can be posted for a critical condition (may use fakes in CI).
- ktlint + detekt clean on CI. No secrets in git.

## 7. Out of scope for v1

Online multiplayer, cloud saves, IAP, wearable companion, iOS, generative AI pets, social sharing.

## 8. Document history

- 2026-08-14 — Phase 0: initial plan derived from `agentic-dev-methodology.md` with named defaults in §3.
- 2026-08-15 — U00: compile/target SDK 36 requires AGP 8.9.1 and Gradle 8.11.1. Agents own git/GitHub, branch protection, and Session 4 merges.
