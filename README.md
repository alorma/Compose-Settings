# Compose Settings

### Versions

![compose-settings](https://img.shields.io/badge/ComposeSettings-0.7.2-brightgreen)

![Compatible with Compose](https://img.shields.io/badge/Compose-1.0.3-brightgreen)

This library provides a set of **Settings** like composable items to help android *Jetpack Compose*
developers build complex settings screens without all the boilerplate

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
implementation 'com.github.alorma:compose-settings-ui:$version'
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

Also, the library provides a **preferences** version of the state, so when setting state is changed, the value is persisted on the
preferences.

```kotlin
val state = rememberPreferenceBooleanSettingState(key = "pref_key", defaultValue = true)
val state = rememberPreferenceFloatSettingState(key = "pref_key", defaultValue = 0.1f)
```
