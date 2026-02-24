# Architecture Documentation

This document describes the architecture, design patterns, and technical decisions behind Compose-Settings.

## Table of Contents

- [Overview](#overview)
- [Module Structure](#module-structure)
- [Design Patterns](#design-patterns)
- [Core Components](#core-components)
- [Extension Points](#extension-points)
- [Platform Strategy](#platform-strategy)
- [Build System](#build-system)
- [Design Decisions](#design-decisions)

## Overview

Compose-Settings is a Kotlin Multiplatform library that provides pre-built settings UI components for Compose. The architecture emphasizes:

- **Consistency**: All components share a common visual and API pattern
- **Flexibility**: Customizable colors, shapes, and text styles
- **Composability**: Components work together seamlessly
- **Multiplatform**: Single codebase supporting Android, iOS, Desktop, and Web

## Module Structure

### Dependency Graph

```
ui-tiles ──────────┐
                   │
ui-tiles-extended ─┼──> ui-core (foundation)
                   │
ui-tiles-expressive┘
```

### Module Responsibilities

#### ui-core

**Purpose**: Foundation module providing shared infrastructure

**Location**: `ui-core/src/commonMain/kotlin/com/alorma/compose/settings/ui/core/`

**Key Components**:
- `SettingsTileScaffold` - Base composable for all settings components
- `SettingsTileCoreDefaults` - Shared default values for colors and text styles
- `SettingsTileDefaults` - Module-specific defaults (e.g., shapes)
- `SettingsTileColors` - Color system for settings components
- `SettingsTextStyles` - Typography system
- `LocalSettingsGroupEnabled` - Composition local for hierarchical enabled state
- `SettingsTileConstants` - Shared constants

**Visibility**: Most APIs are `internal` - not exposed to library consumers

**Why separate module?**
- Prevents duplication between `ui-tiles` and `ui-tiles-extended`
- Both modules depend on it, avoiding circular dependencies
- Centralizes theming and styling logic

#### ui-tiles

**Purpose**: Core settings components that most apps need

**Location**: `ui-tiles/src/commonMain/kotlin/com/alorma/compose/settings/ui/`

**Components**:
- `SettingsMenuLink` - Navigation/link items
- `SettingsCheckbox` - Boolean settings with checkbox
- `SettingsTriStateCheckbox` - Three-state checkbox
- `SettingsRadioButton` - Single choice from a group
- `SettingsSwitch` - Boolean toggle with switch
- `SettingsGroup` - Container for grouping settings

**Artifact**: `com.github.alorma.compose-settings:ui-tiles`

#### ui-tiles-extended

**Purpose**: Advanced components that may require additional dependencies

**Location**: `ui-tiles-extended/src/commonMain/kotlin/com/alorma/compose/settings/ui/`

**Components**:
- `SettingsSlider` - Numeric value selection with slider

**Artifact**: `com.github.alorma.compose-settings:ui-tiles-extended`

**Why separate from ui-tiles?**
- Keeps the core module lightweight
- Advanced components may have different Material 3 requirements
- Users can opt-in to extended features as needed

## Design Patterns

### Scaffold Pattern

All settings components are built on `SettingsTileScaffold`, which provides:

1. **Consistent Layout**: Wraps Material 3's `ListItem`
2. **Standard Parameters**: title, subtitle, icon, enabled, colors
3. **Color Management**: Handles enabled/disabled states automatically
4. **Text Styling**: Applies typography consistently

**Example Structure**:

```kotlin
@Composable
fun SettingsSwitch(
    state: Boolean,
    title: @Composable () -> Unit,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
    // ... other common parameters
) {
    // Respect group enabled state
    val groupEnabled = LocalSettingsGroupEnabled.current
    val actualEnabled = enabled && groupEnabled

    SettingsTileScaffold(
        title = title,
        subtitle = subtitle,
        icon = icon,
        enabled = actualEnabled,
        colors = colors,
        modifier = modifier.clickable { onCheckedChange(!state) },
        action = {
            Switch(
                checked = state,
                onCheckedChange = null, // Handled by parent clickable
                enabled = actualEnabled,
            )
        }
    )
}
```

### Composition Locals

#### LocalSettingsGroupEnabled

**Purpose**: Enables hierarchical enabled/disabled state

**Usage**:
```kotlin
SettingsGroup(enabled = false) {
    // All children automatically disabled
    SettingsSwitch(...)
    SettingsCheckbox(...)
}
```

**Implementation**: Each component checks `LocalSettingsGroupEnabled.current` and combines it with its own `enabled` parameter.

### Color System

`SettingsTileColors` provides state-aware colors:

```kotlin
data class SettingsTileColors(
    val titleColor: Color,
    val disabledTitleColor: Color,
    val subtitleColor: Color,
    val disabledSubtitleColor: Color,
    // ... etc
) {
    fun titleColor(enabled: Boolean) = if (enabled) titleColor else disabledTitleColor
    // ... similar for other colors
}
```

This allows:
- Automatic color transitions based on enabled state
- Consistent disabled appearance across all components
- Easy customization via `SettingsTileDefaults.colors()`

### Composable Parameters

All components follow Material 3 patterns:

1. **State first**: The most important parameter comes first (e.g., `state: Boolean`)
2. **Required parameters**: State and callbacks
3. **Optional with defaults**: `modifier`, `enabled`, `colors`, `icon`, `subtitle`
4. **Composable content**: `title`, `subtitle`, `icon` are composable lambdas
5. **Trailing lambda**: The content/action parameter comes last

## Core Components

### SettingsTileScaffold

The foundation of all settings components.

**Responsibilities**:
- Wraps Material 3 `ListItem`
- Applies colors based on enabled state
- Provides content color and text style
- Handles shape and elevation

**Key Implementation Details**:

```kotlin
@Composable
fun SettingsTileScaffold(
    title: @Composable () -> Unit,
    // ... parameters
) {
    // Wrap title/subtitle/icon with appropriate colors and text styles
    val decoratedTitle: @Composable () -> Unit = {
        ProvideContentColorAndTextStyle(
            contentColor = colors.titleColor(enabled),
            textStyle = textStyles.titleStyle,
        ) {
            title()
        }
    }

    // Use Material 3 ListItem
    ListItem(
        headlineContent = decoratedTitle,
        supportingContent = decoratedSubtitle,
        leadingContent = decoratedIcon,
        trailingContent = decoratedAction,
        // ...
    )
}
```

### SettingsGroup

Container component that affects all children.

**Features**:
- Sets `LocalSettingsGroupEnabled` for all children
- Provides optional title
- Controls spacing with `verticalArrangement`
- Manages padding with `contentPadding`

**Use Case**: Disable/enable multiple related settings at once

## Extension Points

### Custom Components

To create a custom settings component:

1. **Use the scaffold**:
```kotlin
@Composable
fun CustomSettingsComponent(
    title: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    colors: SettingsTileColors = SettingsTileDefaults.colors(),
) {
    val groupEnabled = LocalSettingsGroupEnabled.current
    val actualEnabled = enabled && groupEnabled

    SettingsTileScaffold(
        title = title,
        enabled = actualEnabled,
        colors = colors,
        modifier = modifier,
        action = {
            // Your custom action/control
        }
    )
}
```

2. **Follow naming convention**: `Settings*` prefix
3. **Match API pattern**: Same parameter order and types as other components
4. **Respect group enabled**: Always check `LocalSettingsGroupEnabled.current`

### Theming

Customize appearance through defaults:

```kotlin
@Composable
fun MySettingsScreen() {
    // Custom colors for all components
    val customColors = SettingsTileDefaults.colors(
        titleColor = MaterialTheme.colorScheme.primary,
        containerColor = MaterialTheme.colorScheme.surface,
    )

    SettingsSwitch(
        state = switchState,
        title = { Text("My setting") },
        colors = customColors,
        onCheckedChange = { switchState = it }
    )
}
```

## Platform Strategy

### Multiplatform Targets

Configured in convention plugin with:
- Android (minSdk 21)
- iOS (arm64, x64, simulator arm64)
- JVM (Desktop)
- JavaScript (IR backend)
- WebAssembly (wasmJs)

### Source Set Hierarchy

```
commonMain
├── androidMain
├── iosMain
├── desktopMain (jvmMain)
├── jsMain
└── wasmJsMain
```

### Platform-Specific Code

The library minimizes platform-specific code by:
- Using Compose Multiplatform's common APIs
- Leveraging Material 3 components (available on all platforms)
- Keeping business logic in `commonMain`

Platform-specific implementations are limited to:
- Android: Preference library integration (optional)
- iOS: Native configuration if needed
- Desktop/Web: Platform-specific sample app setup

## Build System

### Convention Plugins

Located in `build-logic/convention/`:

#### ComposeLibraryConventionPlugin

Applied via: `plugins { id("compose.library") }`

**Configures**:
1. Kotlin Multiplatform with all target platforms
2. Compose Multiplatform compiler and runtime
3. Android library settings
4. Maven publishing (group, version from gradle.properties)
5. GPG signing for releases
6. Detekt (code quality)
7. Dokka (documentation)

**Benefits**:
- Single source of truth for all library modules
- Consistent configuration across modules
- Simplified build.gradle.kts files
- Easy to add new library modules

### Version Catalog

All dependencies in `gradle/libs.versions.toml`:
- SDK versions
- Plugin versions
- Library dependencies

## Design Decisions

### Why ui-core Foundation Module?

**Problem**: All component modules (`ui-tiles`, `ui-tiles-extended`, `ui-tiles-expressive`) need shared components

**Options considered**:
1. Duplicate code in all modules ❌
2. Make modules depend on each other ❌ (circular dependency potential)
3. Create shared foundation module ✅

**Decision**: `ui-core` foundation module
- Prevents duplication
- All component modules depend on it
- Contains scaffolds, colors, styles, and shared utilities
- Published as a separate artifact for flexibility

### Why Composable Content Parameters?

**Decision**: Use `title: @Composable () -> Unit` instead of `title: String`

**Rationale**:
- Flexibility: Supports Text, annotated strings, or custom composables
- Consistent with Material 3 APIs
- Better for accessibility (can include semantics)
- Allows internationalization at call site

### Why LocalSettingsGroupEnabled?

**Problem**: Disabling a group of settings requires updating each child

**Decision**: Use CompositionLocal to propagate enabled state

**Benefits**:
- Declarative: Wrap in `SettingsGroup(enabled = false)`
- Automatic: All children become disabled
- Composable: Each child can still override if needed

### Why Separate Extended Module?

**Decision**: Split advanced components into `ui-tiles-extended`

**Rationale**:
- Keeps core module small and focused
- Optional features don't bloat basic installations
- Can evolve independently
- Clear separation of concerns

### Why Build on Material 3?

**Decision**: Use Material 3 `ListItem` as foundation

**Benefits**:
- Consistent with platform guidelines
- Accessibility built-in
- Adapts to theme automatically
- Well-tested and maintained
- Reduces code we need to write and maintain

## Future Considerations

### Extensibility

The architecture supports:
- Additional modules (e.g., `ui-tiles-premium`)
- Custom scaffold implementations
- Theme system extensions
- Platform-specific optimizations

### Backward Compatibility

Changes should maintain:
- API stability (semantic versioning)
- Binary compatibility where possible
- Clear migration guides for breaking changes

### Performance

Current approach prioritizes:
- Minimal recomposition (state hoisting)
- Efficient layout (Material 3 ListItem)
- Tree structure (Composition Locals)

---

For implementation guidelines, see [CONTRIBUTING.md](CONTRIBUTING.md).
For project overview, see [CLAUDE.md](CLAUDE.md).
