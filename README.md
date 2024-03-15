# Compose Settings - Multiplatform
[![Build](https://github.com/alorma/Compose-Settings/actions/workflows/main.yml/badge.svg)](https://github.com/alorma/Compose-Settings/actions/workflows/main.yml)
[![Maven Central](https://img.shields.io/maven-central/v/com.github.alorma.compose-settings/ui-tiles.svg)](https://search.maven.org/search?q=g:%22com.github.alorma.compse-settings%22%20AND%20a:%22ui-tiles%22)

### Versions

This library provides a set of **Settings** like composable items to help android *Jetpack Compose*
developers build complex settings screens without all the boilerplate.

## Install

```
##// groovy
implementation 'com.github.alorma.compose-settings:storage-memory:$version'
implementation 'com.github.alorma.compose-settings:storage-disk:$version'

implementation 'com.github.alorma.compose-settings:ui-tiles:$version'
implementation 'com.github.alorma.compose-settings:ui-tiles-extended:$version'

[...]

// kotlin DSL

implementation("com.github.alorma.compose-settings:storage-memory:$version")
implementation("com.github.alorma.compose-settings:storage-disk:$version")

implementation("com.github.alorma.compose-settings:ui-tiles:$version")
implementation("com.github.alorma.compose-settings:ui-tiles-extended:$version")

[...]

// Catalog versions:

[versions]
compose-settings = "2.1.0"
[libraries]
composeSettings-storage-memory = { group = "com.github.alorma.compose-settings", name = "storage-memory", version.ref = "compose-settings" }
composeSettings-storage-disk = { group = "com.github.alorma.compose-settings", name = "storage-disk", version.ref = "compose-settings" }

composeSettings-ui = { group = "com.github.alorma.compose-settings", name = "ui-tiles", version.ref = "compose-settings" }
composeSettings-ui-extended = { group = "com.github.alorma.compose-settings", name = "ui-tiles-extended", version.ref = "compose-settings" }
```


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
