# PipKin Roadmap

Status vocabulary (only): `Not started` | `Coding` | `Audit` | `Remediation` | `Documentation` | `Closed`

A roadmap phase is done only when every listed unit is `Closed`.

## Phases

| Phase | Units | Phase status |
|-------|-------|--------------|
| 0 Environment | U00 | Closed |
| Prototype | U1, U2, U7 | Not started |
| Offline / Notifications | U3, U5, U6 | Not started |
| Evolution / Mini-games | U4, U8, U9, U10 | Not started |
| Polish | U11 | Not started |
| Beta | U12, U13 | Not started |
| Launch | U13 + store (human) | Not started |

## Units

| ID | Name | Depends | Cycle stage | Session briefs | Phase logs |
|----|------|---------|-------------|----------------|------------|
| U00 | Repository & environment | — | Closed | [sessions](sessions/U00-environment-setup/) | [logs](phase-logs/U00-environment-setup/) |
| U1 | Data model & Room | — | Coding | [sessions](sessions/U01-data-model/) | [logs](phase-logs/U01-data-model/) |
| U2 | Decay/tick simulation | U1 | Not started | [sessions](sessions/U02-simulation-engine/) | [logs](phase-logs/U02-simulation-engine/) |
| U3 | Offline reconciliation | U2 | Not started | [sessions](sessions/U03-offline-reconciliation/) | [logs](phase-logs/U03-offline-reconciliation/) |
| U4 | Evolution & care mistakes | U2 | Not started | [sessions](sessions/U04-evolution-care/) | [logs](phase-logs/U04-evolution-care/) |
| U5 | WorkManager & AlarmManager | U3 | Not started | [sessions](sessions/U05-background-work/) | [logs](phase-logs/U05-background-work/) |
| U6 | Notifications + Live Update | U5 | Not started | [sessions](sessions/U06-notifications/) | [logs](phase-logs/U06-notifications/) |
| U7 | Compose pet screen | U1, U2 | Not started | [sessions](sessions/U07-compose-pet-ui/) | [logs](phase-logs/U07-compose-pet-ui/) |
| U8 | Mini-game: Catch the Food | U7 | Not started | [sessions](sessions/U08-minigame-catch-food/) | [logs](phase-logs/U08-minigame-catch-food/) |
| U9 | Mini-game: reaction/timing | U7 | Not started | [sessions](sessions/U09-minigame-reaction/) | [logs](phase-logs/U09-minigame-reaction/) |
| U10 | Death/rebirth + history | U4, U7 | Not started | [sessions](sessions/U10-death-rebirth-history/) | [logs](phase-logs/U10-death-rebirth-history/) |
| U11 | Onboarding + save slots | U1, U7 | Not started | [sessions](sessions/U11-onboarding-saves/) | [logs](phase-logs/U11-onboarding-saves/) |
| U12 | Firebase analytics | U2, U4, U10 | Not started | [sessions](sessions/U12-analytics/) | [logs](phase-logs/U12-analytics/) |
| U13 | Integration + beta build | all | Not started | [sessions](sessions/U13-integration-beta/) | [logs](phase-logs/U13-integration-beta/) |

## Parallelism

Once dependencies are Closed, independent units may run in parallel on separate worktrees/branches (example: U8 and U6). Each unit still runs its four sessions sequentially.

## Debloat

Next repo-wide debloat pass: schedule after U7 Closed (or two weeks after first `develop` merge, whichever is later). Logs: `docs/phase-logs/debloat/`.
