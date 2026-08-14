# Branch protection (apply on GitHub after the remote exists)

Repository settings → Branches:

## `main`

- Require a pull request before merging
- Require status checks to pass: CI `verify`
- Require conversation resolution
- Do not allow bypassing for admins (recommended)
- No direct pushes
- No force pushes, no deletions

## `develop`

- Require a pull request before merging
- Require status checks to pass: CI `verify`
- Require at least one approval **or** an audit sign-off comment linking `docs/phase-logs/<unit>/<unit>-audit.md` with verdict Pass or Conditional Pass
- No direct pushes
- No force pushes, no deletions

Human lead merges Session 4 PRs. Agents open PRs; they do not push to `develop` or `main`.
