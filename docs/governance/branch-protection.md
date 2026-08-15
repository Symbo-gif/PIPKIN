# Branch protection (agents apply on GitHub after the remote exists)

Agents apply these rules with `gh`; do not wait for a human to click through repository settings.

Repository settings → Branches (or GitHub Rulesets):

## `main`

- Require a pull request before merging
- Require status checks to pass: CI `verify`
- Require conversation resolution
- Enforce for admins
- No required human approving review (phase Closed + all units in the roadmap phase Closed is the process gate)
- No direct pushes
- No force pushes, no deletions

## `develop`

- Require a pull request before merging
- Require status checks to pass: CI `verify`
- Require conversation resolution
- Enforce for admins
- No required human approving review. Process gate: audit sign-off in `docs/phase-logs/<unit>/<unit>-audit.md` with verdict Pass or Conditional Pass, then Session 4 closure, then the documentation agent merges
- No direct pushes
- No force pushes, no deletions

Documentation agents merge Session 4 PRs with `gh pr merge` after closure is committed and CI is green. Do not direct-push to `develop` or `main`.
