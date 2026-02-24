# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this
repository.

## Project Overview

Compose-Settings is a Kotlin Multiplatform library providing pre-built Settings UI components for
Jetpack Compose. It targets Android, iOS, Desktop (JVM), JavaScript, and WebAssembly platforms.

## Architecture

### Module Structure

The project is organized into four main library modules:

1. **ui-core** - Foundation module with shared logic and Material 3 scaffold
    - Location: `ui-core/src/commonMain/kotlin/com/alorma/compose/settings/ui/core/`
    - Provides: `SettingsTileColors`, `SettingsTextStyles`, `SettingsTileCoreDefaults`,
      `CompositionLocals` (`LocalSettingsGroupEnabled`, `LocalSettingsTileColors`,
      `LocalSettingsTextStyles`), `SettingsTileConstants`, `SettingsTileScaffold` (wraps Material
      3's `ListItem`)
    - Contains internal utilities and data models used by all other modules
    - Depends on: standard Material 3
    - Published as: `com.github.alorma.compose-settings:ui-core`

2. **ui-tiles** - Core settings components (standard Material 3)
    - Location: `ui-tiles/src/commonMain/kotlin/com/alorma/compose/settings/ui/`
    - Depends on: `ui-core`
    - Components: `SettingsMenuLink`, `SettingsCheckbox`, `SettingsRadioButton`, `SettingsSwitch`,
      `SettingsTriStateCheckbox`, `SettingsGroup`
    - Published as: `com.github.alorma.compose-settings:ui-tiles`

3. **ui-tiles-extended** - Advanced/extended settings components (standard Material 3)
    - Location: `ui-tiles-extended/src/commonMain/kotlin/com/alorma/compose/settings/ui/`
    - Depends on: `ui-core`
    - Components: `SettingsSlider`, `SettingsSegmented`
    - Published as: `com.github.alorma.compose-settings:ui-tiles-extended`

4. **ui-tiles-expressive** - Expressive Material 3 components
    - Location: `ui-tiles-expressive/src/commonMain/kotlin/com/alorma/compose/settings/ui/expressive/`
    - Depends on: `ui-core`, Material 3 Expressive
    - Components: `SettingsButtonGroup`
    - Published as: `com.github.alorma.compose-settings:ui-tiles-expressive`

### Design Patterns

All settings components follow a consistent pattern:

1. Built on top of `SettingsTileScaffold` (from `ui-core`)
2. Accept common parameters: `title`, `subtitle`, `icon`, `modifier`, `enabled`, `colors`
3. Use Material 3 components internally (ListItem, Checkbox, Switch, etc.)
4. Support `LocalSettingsGroupEnabled` for hierarchical enabled state
5. Leverage `SettingsTileColors` and `SettingsTextStyles` (from `ui-core`) for consistent theming
   across all components

The `SettingsTileScaffold` wraps Material 3's `ListItem` and provides consistent layout and color
handling.

### Architecture Layers

The library uses a layered architecture:

```
ui-core (foundation - colors, styles, scaffold, composition locals)
   ↓
ui-tiles, ui-tiles-extended, ui-tiles-expressive (components)
```

This separation allows:
- **Code reuse**: Shared foundation (colors, styles, scaffold) used by all component modules
- **Flexibility**: Easy to add new component modules
- **Type safety**: Strong separation between foundation layer and component layer

### Platform-Specific Code

Platform-specific implementations are in separate source sets:

- `commonMain/` - Shared Compose UI code
- `androidMain/` - Android-specific integrations (AndroidManifest.xml, preference library
  integration)
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
- iOS (iosArm64, iosSimulatorArm64)
- JavaScript (IR)
- WebAssembly (wasmJs)

The default hierarchy template is applied via `applyDefaultHierarchyTemplate()`.

### Build Convention Plugins

The project uses Gradle convention plugins located in `build-logic/convention/`:

1. **ComposeLibraryConventionPlugin** (`compose.library`)
    - Applied to all library modules (ui-core, ui-tiles, ui-tiles-extended, ui-tiles-expressive)
    - Configures Kotlin Multiplatform with all target platforms
    - Sets up Compose Multiplatform and Compose Compiler
    - Configures Android library settings
    - Integrates Maven publishing with automatic signing
    - Applies code quality tools (Detekt, Dokka)
    - Location: `build-logic/convention/src/main/kotlin/ComposeLibraryConventionPlugin.kt`

2. **ComposeSampleConventionPlugin** (`compose.sample`)
    - Applied to sample application modules
    - Configures sample apps with appropriate dependencies

All library modules automatically get Maven Central publishing configured through the convention
plugin.