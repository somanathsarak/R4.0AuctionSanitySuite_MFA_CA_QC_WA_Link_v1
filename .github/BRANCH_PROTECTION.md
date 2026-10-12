# Branch Protection and Dual Approval Policy

This repository enforces a **dual approval policy** for all commits and pull requests.

## Requirements

Every pull request must receive approval from **2 reviewers** before it can be merged:

1. **First approval**: GitHub Copilot
2. **Second approval**: vishal.giram@eqanim.com

## Implementation

The dual approval policy is enforced through:

1. **CODEOWNERS file** (`.github/CODEOWNERS`):
   - Automatically requests reviews from required approvers
   - Ensures the right people are notified for every PR

2. **GitHub Actions Workflow** (`.github/workflows/dual-approval-policy.yml`):
   - Automatically checks that PRs have the required number of approvals
   - Runs on PR events and review submissions
   - Provides clear feedback on approval status

## Recommended GitHub Settings

To fully enforce this policy, configure the following branch protection rules in GitHub:

### For the `main` (or default) branch:

1. Go to **Settings** → **Branches** → **Branch protection rules**
2. Add rule for `main` branch with these settings:
   - ✅ **Require a pull request before merging**
     - ✅ **Require approvals**: Set to `2`
     - ✅ **Dismiss stale pull request approvals when new commits are pushed**
     - ✅ **Require review from Code Owners**
   - ✅ **Require status checks to pass before merging**
     - ✅ **Require branches to be up to date before merging**
     - Add status check: `Verify Dual Approval Policy`
   - ✅ **Require conversation resolution before merging**
   - ✅ **Do not allow bypassing the above settings** (unless you need admin override)

### For all branches:

Consider applying similar rules to other important branches (e.g., `develop`, `release/*`).

## Workflow Behavior

When a pull request is opened or updated:

1. The CODEOWNERS file automatically requests reviews from:
   - GitHub Copilot
   - vishal.giram@eqanim.com

2. The dual approval workflow checks:
   - Number of unique approvals received
   - Whether the minimum requirement (2 approvals) is met

3. Status check results:
   - ✅ **Pass**: PR has 2 or more approvals from unique reviewers
   - ❌ **Fail**: PR has fewer than 2 approvals

## Notes

- **Direct commits to protected branches are blocked** (when branch protection is enabled)
- **All changes must go through pull requests**
- **Approvals from the PR author do not count** toward the requirement
- **New commits reset approvals** (when dismissal is enabled in branch protection)

## Compliance

This dual approval policy helps ensure:
- Code quality through peer review
- Security through multiple reviewers
- Accountability for all changes
- Compliance with development standards
