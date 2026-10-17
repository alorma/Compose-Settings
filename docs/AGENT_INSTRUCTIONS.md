# Compose Settings: instructions for AI agents

Use this file when you are asked to **add Compose-Settings to a project** or build a settings screen
with it (for working on the library itself, see [AGENTS.md](../AGENTS.md)).

Compose-Settings is a Kotlin Multiplatform library of ready-made settings tiles for Compose
(Android, iOS, Desktop JVM, JS, WasmJS). Prefer its components over hand-written `ListItem` rows.

## 1. Pick ONE style

| Style | Module | Package | Needs |
|-------|--------|---------|-------|
| Standard Material 3 | `ui-tiles` (+ optional `ui-tiles-extended`) | `com.alorma.compose.settings.ui` | nothing special |
| Material 3 Expressive | `ui-tiles-expressive` | `com.alorma.compose.settings.ui.expressive` | `@OptIn(ExperimentalMaterial3ExpressiveApi::class)` |

- `ui-tiles-extended` adds `SettingsSlider` and `SettingsSegmented` (standard style only; it depends
  on `ui-tiles`).
- `ui-tiles-expressive` is self-contained and adds `SettingsButtonGroup`. It has no slider.
- Both styles export components with the **same names** (`SettingsSwitch`, ...). Do not mix them in
  one file or group, or imports will clash. Default to standard unless the user asks for Expressive.
- Group id for all modules: `com.github.alorma.compose-settings`.

## 2. Dependencies

Latest version: check the Maven Central badge in the README (it is `3.4.0` at the time of writing).

```toml
# gradle/libs.versions.toml
[versions]
compose-settings = "3.4.0"

[libraries]
composeSettings-ui = { group = "com.github.alorma.compose-settings", name = "ui-tiles", version.ref = "compose-settings" }
composeSettings-ui-extended = { group = "com.github.alorma.compose-settings", name = "ui-tiles-extended", version.ref = "compose-settings" }
composeSettings-ui-expressive = { group = "com.github.alorma.compose-settings", name = "ui-tiles-expressive", version.ref = "compose-settings" }
```

```kotlin
// shared module build.gradle.kts, KMP: put it in commonMain
kotlin.sourceSets.commonMain.dependencies {
  implementation(libs.composeSettings.ui)
}
// Android-only project: implementation(libs.composeSettings.ui) in dependencies { }
```

## 3. Components

All tiles share these parameters: `title: @Composable () -> Unit` (required), `modifier`,
`enabled` (defaults to the enclosing `SettingsGroup`), `icon`, `subtitle` (both optional
`@Composable () -> Unit`), `colors: ListItemColors`, `semanticProperties`.
The state is **hoisted**: you pass the current value and update it in the callback.

### Standard (`ui-tiles`)

| Component | State param | Callback | Extra |
|-----------|-------------|----------|-------|
| `SettingsMenuLink` | none | `onClick: () -> Unit` | `action: (@Composable () -> Unit)?` trailing content |
| `SettingsSwitch` | `state: Boolean` | `onCheckedChange: (Boolean) -> Unit` | `switchColors` |
| `SettingsCheckbox` | `state: Boolean` | `onCheckedChange: (Boolean) -> Unit` | `checkboxColors` |
| `SettingsTriStateCheckbox` | `state: Boolean?` (`null` = indeterminate) | `onCheckedChange: (Boolean) -> Unit` | `checkboxColors` |
| `SettingsRadioButton` | `state: Boolean` (is selected) | `onClick: () -> Unit` | `checkboxColors: RadioButtonColors` |
| `SettingsGroup` | n/a | n/a | see section 5 |

Standard tiles also take `shape: Shape`, `tonalElevation: Dp`, `shadowElevation: Dp`.

### Extended (`ui-tiles-extended`)

- `SettingsSlider(title, value: Float, onValueChange: (Float) -> Unit, valueRange = 0f..1f, steps = 0, onValueChangeFinished, sliderColors, ...)`
- `SettingsSegmented<T>(title, items: List<T>, selectedItem: T?, onItemSelected: (T) -> Unit, itemTitleMap: (T) -> CharSequence, ...)`

### Expressive (`ui-tiles-expressive`)

Same names and callbacks as standard, with these differences:

- `shapes: ListItemShapes` and `elevation: ListItemElevation` replace `shape`, `tonalElevation`
  and `shadowElevation`.
- `SettingsTriStateCheckbox` uses `state: ToggleableState` and
  `onCheckedChange: (ToggleableState) -> Unit`.
- `SettingsGroup` has no `colors` parameter.
- Extra: `SettingsButtonGroup<T>(title, items, selectedItem: T?, onItemSelected, itemTitleMap, ...)`.

## 4. Minimal examples

Standard:

```kotlin
import com.alorma.compose.settings.ui.SettingsGroup
import com.alorma.compose.settings.ui.SettingsMenuLink
import com.alorma.compose.settings.ui.SettingsSwitch

@Composable
fun SettingsScreen() {
  var wifi by remember { mutableStateOf(true) }

  SettingsGroup(title = { Text("Connectivity") }) {
    SettingsSwitch(
      state = wifi,
      title = { Text("Wi-Fi") },
      subtitle = { Text("Connect to known networks") },
      icon = { Icon(Icons.Default.Wifi, contentDescription = null) },
      onCheckedChange = { wifi = it },
    )
    SettingsMenuLink(
      title = { Text("About") },
      onClick = { /* navigate */ },
    )
  }
}
```

Expressive (segmented, connected list):

```kotlin
import com.alorma.compose.settings.ui.expressive.SettingsGroup
import com.alorma.compose.settings.ui.expressive.SettingsMenuLink
import com.alorma.compose.settings.ui.expressive.SettingsSwitch

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun SettingsScreen() {
  var wifi by remember { mutableStateOf(true) }
  val colors = ListItemDefaults.segmentedColors(
    containerColor = MaterialTheme.colorScheme.surfaceContainer,
  )

  SettingsGroup(
    title = { Text("Connectivity") },
    verticalArrangement = Arrangement.spacedBy(ListItemDefaults.SegmentedGap),
  ) {
    SettingsSwitch(
      state = wifi,
      title = { Text("Wi-Fi") },
      colors = colors,
      shapes = ListItemDefaults.segmentedShapes(index = 0, count = 2),
      onCheckedChange = { wifi = it },
    )
    SettingsMenuLink(
      title = { Text("About") },
      colors = colors,
      shapes = ListItemDefaults.segmentedShapes(index = 1, count = 2),
      onClick = { },
    )
  }
}
```

Radio options (one selected at a time):

```kotlin
var theme by remember { mutableStateOf("system") }
listOf("light", "dark", "system").forEach { option ->
  SettingsRadioButton(
    state = theme == option,
    title = { Text(option) },
    onClick = { theme = option },
  )
}
```

## 5. Groups and enabled state

`SettingsGroup(modifier, enabled = true, contentPadding, verticalArrangement = spacedBy(8.dp), title, content)`
provides `LocalSettingsGroupEnabled` to its children. `enabled = false` on the group disables every
tile inside it unless a tile sets `enabled` explicitly. `SettingsGroup` is optional; tiles work on
their own in any `Column`/`LazyColumn`. For long lists use `LazyColumn` and place the tiles
directly in `item { }`.

## 6. Common mistakes

- Using `ListItem` + `Switch` by hand instead of `SettingsSwitch`.
- Importing from both `...settings.ui` and `...settings.ui.expressive`.
- Forgetting `@OptIn(ExperimentalMaterial3ExpressiveApi::class)` when using Expressive APIs
  (including `ListItemDefaults.segmentedShapes` / `segmentedColors`).
- Passing `shape` to an Expressive tile or `shapes` to a standard tile. They are different parameters.
- Treating tiles as stateful. They never store their own value; update your state in the callback.
- `SettingsRadioButton` takes `onClick`, not `onCheckedChange`.
- Not adding `ui-tiles-extended` when using `SettingsSlider` / `SettingsSegmented`.
- Inventing parameters (for example `textStyles`, which does not exist: style the `Text` inside `title`/`subtitle`). Only use those listed above or in the KDoc.

## 7. Where to look for more

- Compilable usage of every component: `samples/shared` in this repository.
- Screenshots and per-component snippets: [README](../README.md).
- Exact signatures and KDoc: the `-sources.jar` published next to each artifact, or the
  [API reference](https://alorma.github.io/Compose-Settings/dokka).
