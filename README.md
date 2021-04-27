# Compose Settings

### Versions

![compose-settings](https://img.shields.io/badge/ComposeSettings-0.0.4-brightgreen)

![Compatible with Compose](https://img.shields.io/badge/Compose-1.0.0--beta05-brightgreen)

## Demo

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
implementation 'com.github.alorma:compose-settings:$version'
```

## Demo

|Menu|Switch|Checkbox|
|--|--|--|
|<img width="300" src="docs/art/screenshot_links.jpeg" />|<img width="300" src="docs/art/screenshot_switches.jpeg" />|<img width="300" src="docs/art/screenshot_checkboxes.jpeg" />|

## Usage

Use `SettingsList` it's composed of a `Scaffold` and receives all settings in `content`

```kotlin
    SettingsList(
    scaffoldState = scaffoldState,
    title = { Text(text = "Screen title") },
    onBack = {},
    onSearch = {},
    onHelp = {}
) { ... }
```

### Menu Link

[Android docs](https://source.android.com/devices/tech/settings/settings-guidelines#menu_link)

![](docs/art/setting_menu.png)

This can be used to open another settings screen, open link, show a dialog....

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
) {}
```

```kotlin
SettingsCheckbox(
    icon = { Icon(imageVector = Icons.Default.Wifi, contentDescription = "Wifi") },
    title = { Text(text = "Hello") },
    subtitle = { Text(text = "This is a longer text") },
    checked = true,
) {}
```

#### Customization

**Header**

By default, this library provided a `TopAppBar` composable with a slot to setup the text:

![](docs/art/screenshot_toolbar.png)

You can provide `onBack = {}`, `onHelp = {}` and `onSearch = {}` to show the corresponding actions

**Custom header**

If you need to build a custom header, you can use the alternative `SettingsList`

```kotlin
SettingsList(
    scaffoldState = scaffoldState,
    header = { ... }.
) { ... }
```

