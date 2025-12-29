# Publishing Guide

This document explains how to publish Compose-Settings library to Maven Central.

## Overview

The publishing process is fully automated through GitHub Actions and uses:

- **Convention Plugin**: Maven publishing configuration integrated in
  `ComposeLibraryConventionPlugin`
- **In-Memory GPG Signing**: No local keyring files needed
- **GitHub Actions**: Automated building, signing, and publishing
- **Maven Central Portal**: Modern Sonatype publishing endpoint

## Architecture

### Convention Plugin Setup

All library modules (`ui-base`, `ui-tiles`, `ui-tiles-extended`, `ui-tiles-expressive`) use the
`compose.library` convention plugin which automatically configures:

**Location**: `build-logic/convention/src/main/kotlin/ComposeLibraryConventionPlugin.kt`

**What it does**:

1. Applies `com.vanniktech.maven.publish` plugin
2. Applies `signing` plugin for GPG signatures
3. Configures Maven Central publishing endpoint
4. Sets up POM metadata (name, description, licenses, developers, SCM)
5. Reads `libGroup` and `libVersion` from `gradle.properties`

**Key Configuration**:

```kotlin
extensions.configure<MavenPublishBaseExtension> {
  publishToMavenCentral(validateDeployment = false)
  signAllPublications()
  pom { configurePom(this) }
}

group = findProperty("libGroup")?.toString() ?: "com.alorma.compose.settings"
version = findProperty("libVersion")?.toString() ?: "0.0.1"
```

### Library Modules

Each library module only needs to apply the plugin:

```kotlin
plugins {
  id("compose.library")
}
```

The convention plugin handles all publishing configuration automatically.

## GPG Key Setup

### Current Key Information

- **Key ID**: `54137F1B26EEAF35`
- **Email**: `bernatbor15@gmail.com`
- **Expires**: 2027-12-23

### Renewing the GPG Key (when it expires)

```bash
gpg --edit-key 54137F1B26EEAF35
# In GPG prompt:
expire    # Extend primary key
2y        # Set to 2 years
key 1     # Select subkey
expire    # Extend subkey
2y        # Set to 2 years
save      # Save and quit
```

### Exporting the Key for GitHub

```bash
# Export in ASCII-armored format
gpg --export-secret-keys --armor 54137F1B26EEAF35
```

Copy the entire output (including BEGIN and END lines) to the `GPG_KEY` secret in GitHub.

## GitHub Secrets Configuration

Navigate to: **GitHub.com → Repository → Settings → Secrets and variables → Actions**

### Required Secrets

| Secret Name              | Description                | How to Get It                                       |
|--------------------------|----------------------------|-----------------------------------------------------|
| `GPG_KEY`                | Your GPG private key       | `gpg --export-secret-keys --armor 54137F1B26EEAF35` |
| `GPG_PASSWORD`           | Passphrase for the GPG key | The password you use to unlock the key              |
| `MAVEN_CENTRAL_USERNAME` | Sonatype username/token    | From https://central.sonatype.com/                  |
| `MAVEN_CENTRAL_PASSWORD` | Sonatype password/token    | From https://central.sonatype.com/                  |

## GitHub Actions Workflows

### 1. Pull Request Workflow (`.github/workflows/pull_request.yml`)

**Triggers**: On pull requests to `main`

**Steps**:

- Builds all library modules
- Builds all sample apps
- Checks GPG signatures
- Publishes to Maven Local (test)
- Dry run publish to Maven Central

**Purpose**: Validates that builds and signing work before merging.

### 2. Main Branch Workflow (`.github/workflows/main.yml`)

**Triggers**: On push to `main` branch

**Steps**:

- Builds all library modules
- Builds all sample apps
- Checks GPG signatures
- Publishes to Maven Local (test)
- Dry run publish to Maven Central
- Deploys documentation to GitHub Pages

**Purpose**: Continuous validation of main branch.

### 3. Publish and Release Workflow (`.github/workflows/publish-and-release.yml`)

**Triggers**: Manual (workflow_dispatch)

**Steps**:

1. Reads current version from `gradle.properties`
2. Builds all library modules
3. Builds all sample apps
4. Checks GPG signatures
5. **In Parallel**:
    - Publishes to Maven Central
    - Creates GitHub release with tag
6. Calculates next version (increments minor, resets patch to 0)
7. Updates `gradle.properties` with next version
8. Commits and pushes version update to `main`

**Example Flow**:

- Current version: `2.18.0`
- Published version: `2.18.0`
- GitHub tag created: `v2.18.0`
- Next version set: `2.19.0`

## Publishing Process

### Version Management

Versions are stored in `gradle.properties`:

```properties
libGroup=com.alorma.compose.settings
libVersion=2.18.0
```

The convention plugin reads these properties and applies them to all library modules.

### How to Publish a New Release

1. **Ensure main branch is ready**:
    - All features merged
    - Tests passing
    - Version in `gradle.properties` is what you want to release

2. **Trigger the workflow**:
    - Go to GitHub Actions tab
    - Select "Publish and Release" workflow
    - Click "Run workflow"
    - Select `main` branch
    - Click "Run workflow"

3. **Monitor the workflow**:
    - Watch the GitHub Actions run
    - Check for any failures
    - Verify Maven Central publication
    - Verify GitHub release creation

4. **Automatic version bump**:
    - The workflow automatically bumps to next minor version
    - Commits the change back to `main`
    - You don't need to manually update the version

### Testing Locally

**Test signing** (requires GPG password):

```bash
./gradlew :ui-tiles:signKotlinMultiplatformPublication
```

**Test local publish**:

```bash
./gradlew publishToMavenLocal
```

**Note**: Local testing without environment variables will fail at signing step. This is expected
and safe.

**Test with credentials** (for advanced testing):

```bash
export ORG_GRADLE_PROJECT_signingInMemoryKey="$(gpg --export-secret-keys --armor 54137F1B26EEAF35)"
export ORG_GRADLE_PROJECT_signingInMemoryKeyPassword="your-password"
export ORG_GRADLE_PROJECT_mavenCentralUsername="your-username"
export ORG_GRADLE_PROJECT_mavenCentralPassword="your-password"
./gradlew publishToMavenLocal
```

## How In-Memory Signing Works

The `com.vanniktech.maven.publish` plugin automatically detects and uses in-memory GPG keys when
these environment variables are set:

```yaml
env:
  ORG_GRADLE_PROJECT_signingInMemoryKey: ${{ secrets.GPG_KEY }}
  ORG_GRADLE_PROJECT_signingInMemoryKeyPassword: ${{ secrets.GPG_PASSWORD }}
```

**Benefits**:

- No keyring files to manage
- Works seamlessly in CI/CD
- More secure (keys never touch disk)
- Cross-platform compatible

**How it works**:

1. GitHub Actions sets environment variables from secrets
2. Gradle picks them up as project properties
3. vanniktech plugin passes them to Gradle's signing plugin
4. Signing plugin uses in-memory key for all signatures

## Published Artifacts

Each library module publishes multiple artifacts for each platform:

### ui-base

- `com.alorma.compose.settings:ui-base:$version`
- Platform variants: `-android`, `-desktop`, `-iosarm64`, `-iossimulatorarm64`, `-iosx64`, `-js`,
  `-wasmjs`

### ui-tiles

- `com.alorma.compose.settings:ui-tiles:$version`
- Platform variants: `-android`, `-desktop`, `-iosarm64`, `-iossimulatorarm64`, `-iosx64`, `-js`,
  `-wasmjs`

### ui-tiles-extended

- `com.alorma.compose.settings:ui-tiles-extended:$version`
- Platform variants: `-android`, `-desktop`, `-iosarm64`, `-iossimulatorarm64`, `-iosx64`, `-js`,
  `-wasmjs`

### ui-tiles-expressive

- `com.alorma.compose.settings:ui-tiles-expressive:$version`
- Platform variants: `-android`, `-desktop`, `-iosarm64`, `-iossimulatorarm64`, `-iosx64`, `-js`,
  `-wasmjs`

## Troubleshooting

### Issue: "Unable to retrieve secret key from key ring"

**Cause**: Missing or incorrect GPG configuration.

**Solution**:

- Verify `GPG_KEY` secret contains the full ASCII-armored key
- Verify `GPG_PASSWORD` secret is correct
- Check GitHub Actions logs for environment variable presence

### Issue: "401 Unauthorized" from Maven Central

**Cause**: Invalid Maven Central credentials.

**Solution**:

- Verify `MAVEN_CENTRAL_USERNAME` secret
- Verify `MAVEN_CENTRAL_PASSWORD` secret
- Check if token has expired at https://central.sonatype.com/

### Issue: Version already exists on Maven Central

**Cause**: Trying to publish a version that's already published.

**Solution**:

- Update `libVersion` in `gradle.properties`
- Commit and push the change
- Trigger the publish workflow again

### Issue: GPG key expired

**Cause**: GPG key passed its expiration date.

**Solution**:

1. Renew the key (see "Renewing the GPG Key" section)
2. Export the renewed key
3. Update the `GPG_KEY` secret in GitHub
4. Retry the workflow

## Maven Central Portal

After publishing, artifacts appear at:

- **Portal**: https://central.sonatype.com/
- **Repository**: https://repo1.maven.org/maven2/com/alorma/compose/settings/

**Timing**:

- Artifacts appear in portal immediately after successful publish
- Full propagation to Maven Central can take 15-30 minutes
- CDNs and mirrors may take up to 2 hours

## Additional Resources

- [vanniktech/gradle-maven-publish-plugin](https://github.com/vanniktech/gradle-maven-publish-plugin)
- [Maven Central Portal Documentation](https://central.sonatype.org/publish/)
- [GPG Documentation](https://www.gnupg.org/documentation/)
