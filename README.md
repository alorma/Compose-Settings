# Compose Settings

### Versions

![compose-settings](https://img.shields.io/badge/ComposeSettings-0.6.0-brightgreen)

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

In order to use storage:

```groovy
implementation 'com.github.alorma:compose-settings-storage:$version'
implementation 'com.github.alorma:compose-settings-storage-preferences:$version'
```

## Demo

|Menu link|Switch|Checkbox|
|--|--|--|
|<img width="300" src="docs/art/screenshot_links.jpeg" />|<img width="300" src="docs/art/screenshot_switches.jpeg" />|<img width="300" src="docs/art/screenshot_checkboxes.jpeg" />|

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
SettingsSwitch(
    icon = { Icon(imageVector = Icons.Default.Wifi, contentDescription = "Wifi") },
    title = { Text(text = "Hello") },
    subtitle = { Text(text = "This is a longer text") },
    checked = true,
    onCheckedChange = {},
)
```

```kotlin
SettingsCheckbox(
    icon = { Icon(imageVector = Icons.Default.Wifi, contentDescription = "Wifi") },
    title = { Text(text = "Hello") },
    subtitle = { Text(text = "This is a longer text") },
    checked = true,
    onCheckedChange = {},
)
```

