# Phase logs

Permanent record of each unit's Audit, Remediation, and Closure. Session 1 does not write a log unless it checkpoints a saturated context.

| Path | When |
|------|------|
| `<unit>/<unit>-audit.md` | End of Session 2 |
| `<unit>/<unit>-remediation.md` | End of Session 3 (omit only if audit had zero findings) |
| `<unit>/<unit>-closure.md` | End of Session 4 — merge is blocked without this |
| `<unit>/checkpoint.md` | Optional: context-hygiene handoff mid-session |
| `debloat/` | Biweekly whole-repo cleanup |

Use templates in `docs/templates/`. Do not rewrite history of an audit report; add a re-audit section or a follow-up file `<unit>-audit-r2.md`.
