# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project Overview

Compose-Settings is a Kotlin Multiplatform library providing pre-built Settings UI components for Jetpack Compose. It targets Android, iOS, Desktop (JVM), JavaScript, and WebAssembly platforms.

## Architecture

### Module Structure

The project is organized into three main library modules:

1. **ui-base** - Foundation module containing shared internal components
   - Location: `ui-base/src/commonMain/kotlin/com/alorma/compose/settings/ui/base/internal/`
   - Provides: `SettingsTileScaffold`, `SettingsTileDefaults`, `SettingsTileColors`, `CompositionLocals`
   - Contains no public API - only internal utilities used by other modules

2. **ui-tiles** - Core settings components
   - Location: `ui-tiles/src/commonMain/kotlin/com/alorma/compose/settings/ui/`
   - Depends on: `ui-base`
   - Components: `SettingsMenuLink`, `SettingsCheckbox`, `SettingsRadioButton`, `SettingsSwitch`, `SettingsTriStateCheckbox`, `SettingsGroup`
   - Published as: `com.github.alorma.compose-settings:ui-tiles`

3. **ui-tiles-extended** - Advanced/extended settings components
   - Location: `ui-tiles-extended/src/commonMain/kotlin/com/alorma/compose/settings/ui/`
   - Depends on: `ui-base`
   - Components: `SettingsSlider`, `SettingsSegmented`
   - Published as: `com.github.alorma.compose-settings:ui-tiles-extended`

### Design Patterns

All settings components follow a consistent pattern:

1. Built on top of `SettingsTileScaffold` (from `ui-base`)
2. Accept common parameters: `title`, `subtitle`, `icon`, `modifier`, `enabled`, `colors`
3. Use Material 3 components internally (ListItem, Checkbox, Switch, etc.)
4. Support `LocalSettingsGroupEnabled` for hierarchical enabled state
5. Leverage `SettingsTileColors` for consistent theming across all components

The `SettingsTileScaffold` wraps Material 3's `ListItem` and provides consistent layout and color handling.

### Platform-Specific Code

Platform-specific implementations are in separate source sets:
- `commonMain/` - Shared Compose UI code
- `androidMain/` - Android-specific integrations (AndroidManifest.xml, preference library integration)
- `desktopMain/` - JVM desktop code
- `iosMain/` - iOS-specific code
- `jsMain/` - JavaScript code
- `wasmJsMain/` - WebAssembly code

### Sample Apps

Sample applications demonstrating the library are in `samples/`:
- `samples/androidApp/` - Android demo
- `samples/desktopApp/` - Desktop (JVM) demo
- `samples/iosApp/` - iOS demo
- `samples/jsApp/` - JavaScript browser demo
- `samples/wasmApp/` - WebAssembly demo
- `samples/shared/` - Shared demo code across platforms

## Build Commands

### Core Build Tasks
```bash
# Build all modules
./gradlew build

# Assemble artifacts without running tests
./gradlew assemble

# Clean build artifacts
./gradlew clean
```

### Testing
```bash
# Run all tests across all platforms
./gradlew allTests

# Run desktop tests specifically
./gradlew desktopTest

# Run checks (includes tests + linting)
./gradlew check
```

### Code Quality
```bash
# Run ktlint for code formatting
./gradlew ktlintCheck

# Auto-format code with ktlint
./gradlew ktlintFormat

# Run detekt for static code analysis
./gradlew detekt

# Run all detekt tasks across all modules
./gradlew detektAll
```

### Documentation
```bash
# Generate Dokka HTML documentation (outputs to docs/html/)
./gradlew dokkaGenerateHtml

# Generate all Dokka publications
./gradlew dokkaGenerate
```

### Publishing
```bash
# Publish to Maven Local for testing
./gradlew publishToMavenLocal

# Publish to Maven Central (requires credentials)
./gradlew publishAndReleaseToMavenCentral
```

See [PUBLISHING.md](PUBLISHING.md) for detailed publishing instructions and configuration.

## Development Notes

### Adding New Components

When creating new settings components:
1. Choose the appropriate module (`ui-tiles` for core, `ui-tiles-extended` for advanced)
2. Place in `<module>/src/commonMain/kotlin/com/alorma/compose/settings/ui/`
3. Use `SettingsTileScaffold` as the base
4. Follow the existing component API pattern (title, subtitle, icon, enabled, colors, etc.)
5. Ensure the component respects `LocalSettingsGroupEnabled`

### Version Configuration

All version management is centralized in `gradle/libs.versions.toml`:
- SDK versions: `android-minSdk`, `android-compileSdk`, `android-targetSdk`
- Plugin versions: `kotlin`, `compose-plugin`, `agp`, `dokka`
- Library versions are cataloged under `[libraries]`

### Multiplatform Targets

All library modules are configured with:
- Android (minSdk 21)
- JVM Desktop
- iOS (iosX64, iosArm64, iosSimulatorArm64)
- JavaScript (IR)
- WebAssembly (wasmJs)

The default hierarchy template is applied via `applyDefaultHierarchyTemplate()`.

### Build Convention Plugins

The project uses Gradle convention plugins located in `build-logic/convention/`:

1. **ComposeLibraryConventionPlugin** (`compose.library`)
   - Applied to all library modules (ui-base, ui-tiles, ui-tiles-extended)
   - Configures Kotlin Multiplatform with all target platforms
   - Sets up Compose Multiplatform and Compose Compiler
   - Configures Android library settings
   - Integrates Maven publishing with automatic signing
   - Applies code quality tools (Detekt, ktlint, Dokka)
   - Location: `build-logic/convention/src/main/kotlin/ComposeLibraryConventionPlugin.kt`

2. **ComposeSampleConventionPlugin** (`compose.sample`)
   - Applied to sample application modules
   - Configures sample apps with appropriate dependencies

All library modules automatically get Maven Central publishing configured through the convention plugin.