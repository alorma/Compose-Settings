# Compose Settings - Multiplatform

<div>
 <img src="https://img.shields.io/badge/Platform-Android-brightgreen.svg?logo=android" alt="Badge Android" />

<img src="https://img.shields.io/badge/Platform-iOS%20%2F%20macOS-lightgrey.svg?logo=apple" alt="Badge iOS" />

<img src="https://img.shields.io/badge/Platform-JVM-8A2BE2.svg?logo=openjdk" alt="Badge JVM" />

<img src="https://img.shields.io/badge/Platform-WASM%20%2F%20JS-yellow.svg?logo=javascript" alt="Badge wasm/JS" />

</div>

[![Build](https://github.com/alorma/Compose-Settings/actions/workflows/main.yml/badge.svg)](https://github.com/alorma/Compose-Settings/actions/workflows/main.yml)
[![Maven Central](https://img.shields.io/maven-central/v/com.github.alorma.compose-settings/ui-tiles.svg)](https://central.sonatype.com/namespace/com.github.alorma.compose-settings)

[![Dokka](https://img.shields.io/badge/Documentation-blue)](https://alorma.github.io/Compose-Settings/dokka)

<a href="https://www.buymeacoffee.com/alorma" target="_blank"><img src="https://cdn.buymeacoffee.com/buttons/default-orange.png" alt="Buy Me A Coffee" height="41" width="174"></a>


### Preview

This library provides a set of **Settings** like composable items to help android *Jetpack Compose*
developers build complex settings screens without all the boilerplate.

**Ui tiles**

| Component                                             | Screenshot                                                                       |
|-------------------------------------------------------|----------------------------------------------------------------------------------|
| [SettingsMenuLink](#SettingsMenuLink)                 | <img width="200" alt="menu.png" src="docs/art/menu-link.png" />                  |
| [SettingsCheckbox](#SettingsCheckbox)                 | <img width="200" alt="checkbox.png" src="docs/art/checkbox.png" />               |
| [SettingsTriStateCheckbox](#SettingsTriStateCheckbox) | <img width="200" alt="triState-checkbox" src="docs/art/triState-checkbox.png" /> |
| [SettingsRadioButton](#SettingsRadioButton)           | <img width="200" alt="radiobutton.png" src="docs/art/radiobutton.png" />         |
| [SettingsSwitch](#SettingsSwitch)                     | <img width="200" alt="switch.png" src="docs/art/switch.png" />                   |
| [SettingsGroup](#SettingsGroup)                      | <img width="200" alt="group.png" src="docs/art/group.png" />                     |

**Ui tiles expanded**

| Component                         | Screenshot                                                     |
|-----------------------------------|----------------------------------------------------------------|
| [SettingsSlider](#SettingsSlider) | <img width="200" alt="slider.png" src="docs/art/slider.png" /> |

## Install

```
##// groovy
implementation 'com.github.alorma.compose-settings:ui-tiles:$version'
implementation 'com.github.alorma.compose-settings:ui-tiles-extended:$version'

[...]

// kotlin DSL

implementation("com.github.alorma.compose-settings:ui-tiles:$version")
implementation("com.github.alorma.compose-settings:ui-tiles-extended:$version")

[...]

// Catalog versions:

[versions]
compose-settings = "{{libVersion}}"

[libraries]
composeSettings-ui = { group = "com.github.alorma.compose-settings", name = "ui-tiles", version.ref = "compose-settings" }
composeSettings-ui-extended = { group = "com.github.alorma.compose-settings", name = "ui-tiles-extended", version.ref = "compose-settings" }
```

## Usage

##### SettingsMenuLink:

```kotlin
SettingsMenuLink(
  title = { Text(text = "Setting title") },
  subtitle = { Text(text = "Setting subtitle") },
  modifier = Modifier,
  enabled = false / true,
  icon = { Icon(...) },
  action = { IconButton() },
  onClick = { ... },
)
```

<img width="300" alt="menu.png" src="docs/art/menu-link.png" />

##### SettingsCheckbox:

```kotlin
SettingsCheckbox(
  state = false / true,
  title = { Text(text = "Setting title") },
  subtitle = { Text(text = "Setting subtitle") },
  modifier = Modifier,
  enabled = false / true,
  icon = { Icon(...) },
  onCheckedChange = { newState: Boolean -> },
)
```

<img width="300" alt="checkbox.png" src="docs/art/checkbox.png" />

##### SettingsTriStateCheckbox:

```kotlin
SettingsTriStateCheckbox(
  state = false / true / null,
  title = { Text(text = "Setting title") },
  subtitle = { Text(text = "Setting subtitle") },
  modifier = Modifier,
  enabled = false / true,
  icon = { Icon(...) },
  onCheckedChange = { newState: Boolean -> },
)
```

<img width="300" alt="triState-checkbox.png" src="docs/art/triState-checkbox.png" />

##### SettingsRadioButton:

```kotlin
SettingsRadioButton(
  state = false / true,
  title = { Text(text = "Setting title") },
  subtitle = { Text(text = "Setting subtitle") },
  modifier = Modifier,
  enabled = false / true,
  icon = { Icon(...) },
  onClick = { },
)
```

<img width="300" alt="radiobutton.png" src="docs/art/radiobutton.png" />

##### SettingsSwitch:

```kotlin
SettingsSwitch(
  state = false / true,
  title = { Text(text = "Setting title") },
  subtitle = { Text(text = "Setting subtitle") },
  modifier = Modifier,
  enabled = false / true,
  icon = { Icon(...) },
  onCheckedChange = { newState: Boolean -> },
)
```

<img width="300" alt="switch.png" src="docs/art/switch.png" />

##### SettingsSlider:

```kotlin
SettingsSlider(
  value = x.xf,
  valueRange = X.f..Y.f,
  steps = X,
  title = { Text(text = "Setting title") },
  subtitle = { Text(text = "Setting subtitle") },
  modifier = Modifier,
  enabled = false / true,
  icon = { Icon(...) },
  onValueChange = { newValue: Float -> },
)
```

<img width="300" alt="slider.png" src="docs/art/slider.png" />

##### SettingsGroup

> Updates on `enabled` will be reflected on it's internal components unless you change their `enabled` state manually.

```kotlin
SettingsGroup(
  modifier = Modifier,
  enabled = false / true,
  title = { Text(text = "SettingsGroup") },
  contentPadding = PaddingValues(16.dp),
) {
    SettingsMenuLink(...)
    SettingsCheckbox(...)
    SettingsSwitch(...)
    ...
}
```

<img width="300" alt="group.png" src="docs/art/group.png" />

## Development

### Hot Reload

All sample applications include the Compose Hot Reload plugin for faster development iterations. When running the sample apps in development mode, code changes to Composable functions will be instantly reflected without restarting the application.

**How to use:**
- **Android**: Run the app in debug mode from Android Studio or IntelliJ IDEA
- **Desktop**: Run the app with `./gradlew :samples:desktopApp:run`
- **Web/WASM**: Run with `./gradlew :samples:webApp:jsBrowserDevelopmentRun` or `wasmJsBrowserDevelopmentRun`
- **iOS**: Build and run the iOS app from Xcode

The hot reload functionality is automatically enabled in development builds and requires no additional configuration.
