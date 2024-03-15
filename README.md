# Compose Settings - Multiplatform

[![Build](https://github.com/alorma/Compose-Settings/actions/workflows/main.yml/badge.svg)](https://github.com/alorma/Compose-Settings/actions/workflows/main.yml)
[![Maven Central](https://img.shields.io/maven-central/v/com.github.alorma.compose-settings/ui-tiles.svg)](https://search.maven.org/search?q=g:%22com.github.alorma.compse-settings%22%20AND%20a:%22ui-tiles%22)

### Preview

This library provides a set of **Settings** like composable items to help android *Jetpack Compose*
developers build complex settings screens without all the boilerplate.

**Ui tiles**

| Component                | Screenshot                                                                       |
|--------------------------|----------------------------------------------------------------------------------|
| SettingsMenuLink         | <img width="200" alt="menu.png" src="docs/art/menu.png" />                       |
| SettingsCheckbox         | <img width="200" alt="checkbox.png" src="docs/art/checkbox.png" />               |
| SettingsTriStateCheckbox | <img width="200" alt="triState-checkbox" src="docs/art/triState-checkbox.png" /> |
| SettingsRadioButton      | <img width="200" alt="radiobutton.png" src="docs/art/radiobutton.png" />         |
| SettingsSwitch           | <img width="200" alt="switch.png" src="docs/art/switch.png" />                   |


**Ui tiles expanded**

| Component                | Screenshot                                                                       |
|--------------------------|----------------------------------------------------------------------------------|
| SettingsSlider           | <img width="200" alt="switch.png" src="docs/art/slider.png" />                   |

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
compose-settings = "2.1.0"

[libraries]
composeSettings-ui = { group = "com.github.alorma.compose-settings", name = "ui-tiles", version.ref = "compose-settings" }
composeSettings-ui-extended = { group = "com.github.alorma.compose-settings", name = "ui-tiles-extended", version.ref = "compose-settings" }
```

## Usage

**SettingsMenuLink:**

```kotlin
SettingsMenuLink(
  title = { Text(text = "Setting title") },
  subtitle = { Text(text = "Setting subtitle") },
  modifier = Modifier,
  enabled = false/true,
  icon = { Icon(...) },
  action = { IconButton() },
  onClick = { ... },
)
```

<img width="300" alt="menu.png" src="docs/art/menu.png" />

## Install - Storage

```
##// groovy
implementation 'com.github.alorma.compose-settings:storage-memory:$version'
implementation 'com.github.alorma.compose-settings:storage-disk:$version'

[...]

// kotlin DSL

implementation("com.github.alorma.compose-settings:storage-memory:$version")
implementation("com.github.alorma.compose-settings:storage-disk:$version")

[...]

// Catalog versions:

[versions]
compose-settings = "2.1.0"
[libraries]
composeSettings-storage-memory = { group = "com.github.alorma.compose-settings", name = "storage-memory", version.ref = "compose-settings" }
composeSettings-storage-disk = { group = "com.github.alorma.compose-settings", name = "storage-disk", version.ref = "compose-settings" }
```
