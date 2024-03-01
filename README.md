# Compose Settings - Multiplatform 

### Versions
[![Build](https://github.com/alorma/Compose-Settings/actions/workflows/main.yml/badge.svg)](https://github.com/alorma/Compose-Settings/actions/workflows/main.yml)

[![Maven Central](https://img.shields.io/maven-central/v/com.github.alorma/compose-settings-ui.svg?label=Maven%20Central)](https://search.maven.org/search?q=g:%22com.github.alorma%22%20AND%20a:%22compose-settings-ui%22)

This library provides a set of **Settings** like composable items to help android *Jetpack Compose*
developers build complex settings screens without all the boilerplate

## Current status

This library is under development.

Moving to Kotlin MulotiPlatform... Some content of this readme may not be accurate. Please, wait until v2 (soon...) for a full README.md and instructions on how to use it

## Install

```groovy
allprojects {
  repositories {
    //...
    mavenCentral()
  }
}
```

Add dependencies:

```groovy
Material 2 version
implementation 'com.github.alorma:compose-settings-ui:$version'

Material 3 version
implementation 'com.github.alorma:compose-settings-ui-m3:$version'
```

## Demo

|Menu link|Switch|Checkbox|
|--|--|--|
|<img width="300" src="docs/art/screenshot_links.jpeg" /> |<img width="300" src="docs/art/screenshot_switches.jpeg" />|<img width="300" src="docs/art/screenshot_checkboxes.jpeg" />|

## Usage

This library provide some `@Composable` items that can be used to build your settings screen.

`Scaffold`, `Column`... is not provided by the library, you can place items wherever you want.

### Menu Link

[Android docs](https://source.android.com/devices/tech/settings/settings-guidelines#menu_link)

![](docs/art/setting_menu.png)

This can be used to open another settings screen, open link, show a dialog...

```kotlin
SettingsMenuLink(
  icon = { Icon(imageVector = Icons.Default.Wifi, contentDescription = "Wifi") },
  title = { Text(text = "Hello") },
  subtitle = { Text(text = "This is a longer text") },
  onClick = {},
)
```

`action` can be provided:

```kotlin
SettingsMenuLink(
  title = { Text(text = "Menu 4") },
  action = { ... },
)
```

![](docs/art/setting_menu_action.png)

### Switch && Checkboxes

[Android docs - Switch](https://source.android.com/devices/tech/settings/settings-guidelines#switch)

![](docs/art/setting_switch.png)

[Android docs - Checkbox](https://source.android.com/devices/tech/settings/settings-guidelines#checkbox)

![](docs/art/setting_checkbox.png)

This can be used to enable or disable a feature

```kotlin
SettingsCheckbox(
  icon = { Icon(imageVector = Icons.Default.Wifi, contentDescription = "Wifi") },
  title = { Text(text = "Hello") },
  subtitle = { Text(text = "This is a longer text") },
  onCheckedChange = { newValue -> },
)
```

```kotlin
SettingsCheckbox(
  icon = { Icon(imageVector = Icons.Default.Wifi, contentDescription = "Wifi") },
  title = { Text(text = "Hello") },
  subtitle = { Text(text = "This is a longer text") },
  onCheckedChange = { newValue -> },
)
```

### State

In order to provide a default value for the setting and recompose when state changes, you must use `state` parameter.

#### Installation

```groovy
implementation 'com.github.alorma:compose-settings-storage-preferences:$version'
```

```kotlin
val state = rememberBooleanSettingState()
SettingsCheckbox(state = state)

val state = rememberFloatSettingState()
SettingSlider(state = state)
```

And, you can react to state change, for example:

```kotlin
val state = rememberBooleanSettingState()

if (state.value) {
  Text("Enabled")
} else {
  Text("Disabled")
}

val state = rememberBooleanSettingState()

if (state.value > 0.5f) {
  Icon(imageVector = Icons.Default.BrightnessLow)
} else {
  Icon(imageVector = Icons.Default.BrightnessHigh)
}
```

This library provide default value for state as `false`, however if you need to pass a different value (`true`), you can use:

```kotlin
val state = rememberBooleanSettingState(defaultValue = true)
val state = rememberFloatSettingState(defaultValue = 0.1f)
```

The library provides a **preferences** version of the state, so when setting state is changed, the value is persisted on the
preferences.

```kotlin
val state = rememberPreferenceBooleanSettingState(key = "pref_key", defaultValue = true)
val state = rememberPreferenceFloatSettingState(key = "pref_key", defaultValue = 0.1f)
val state = rememberPreferenceIntSettingState(key = "pref_key", defaultValue = 1)
```

Also, the library provides a **DataStore (Both Preference and Proto)** version of the state, so when setting state is changed, the value is persisted on the
DataStore preferences.

Add dependencies:

```groovy
PreferenceDataStore version
implementation 'com.github.alorma:compose-settings-storage-datastore:$version'

ProtoDatastore version
implementation 'com.github.alorma:compose-settings-storage-datastore-proto:$version'

And you have to add ProtoBuf dependencies in addition.
```


```kotlin
// PreferenceDataStore
val state = rememberPreferenceDataStoreBooleanSettingState(key = "pref_key", defaultValue = true)
val state = rememberPreferenceDataStoreFloatSettingState(key = "pref_key", defaultValue = 0.1f)
val state = rememberPreferenceDataStoreIntSettingState(key = "pref_key", defaultValue = 1)


// ProtoDataStore
val datastoreState = rememberProtoDataStoreState(
  // "filename" is optional, but it is recommended to set the protobuf file name so that it wouldn't conflict with other datastore definition.
  filename = "compose_settings_datastore_proto.pb",
  serializer = SettingsSerializer,
)

val state = rememberProtoDataStoreTransformSettingState(
  protoDataStoreState = dataStoreState,
  encoder = {savedValue, newValue -> savedValue.toBuilder().setProtoValue(newValue).build() },
  decoder = { it.protoValue }
)
val state = rememberProtoDataStoreSettingState(protoDataStoreState = dataStoreState)
```
