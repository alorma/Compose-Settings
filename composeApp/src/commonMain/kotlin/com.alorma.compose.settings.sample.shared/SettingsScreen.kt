package com.alorma.compose.settings.sample.shared

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.alorma.compose.settings.storage.disk.rememberBooleanSettingState
import com.alorma.compose.settings.storage.disk.rememberIntSettingState
import com.alorma.compose.settings.storage.disk.rememberTriStateSetting
import com.alorma.compose.settings.storage.memory.rememberMemoryBooleanSettingState
import com.alorma.compose.settings.storage.memory.rememberMemoryTriStateSettingState
import com.alorma.compose.settings.ui.SettingsCheckbox
import com.alorma.compose.settings.ui.SettingsGroup
import com.alorma.compose.settings.ui.SettingsMenuLink
import com.alorma.compose.settings.ui.SettingsSwitch
import com.alorma.compose.settings.ui.SettingsTriStateCheckbox
import com.russhwolf.settings.Settings

@Composable
fun SettingsScreen(
  settings: Settings,
  darkTheme: Boolean,
  onDarkThemeChange: (Boolean) -> Unit,
  onDarkModeReset: () -> Unit,
) {
  Scaffold { padding ->
    val scrollState = rememberScrollState()
    Column(
      modifier = Modifier
        .consumeWindowInsets(padding)
        .verticalScroll(scrollState)
        .padding(top = padding.calculateTopPadding()),
    ) {

      val clicksCounterState = rememberIntSettingState(
        key = "clicked",
        defaultValue = 0,
        settings = settings,
      )

      val checkState = rememberBooleanSettingState(
        key = "checkbox",
        defaultValue = true,
        settings = settings,
      )

      val onlineStatusState = rememberTriStateSetting(
        key = "triStateCheck",
        defaultValue = null,
        settings = settings,
      )

      SettingsGroup(
        title = { Text(text = "SettingsSwitch Tile") }
      ) {
        SettingsSwitch(
          state = darkTheme,
          title = { Text(text = "Dark mode") },
          onCheckedChange = onDarkThemeChange,
        )
        val switchMemoryState = rememberMemoryBooleanSettingState()
        SettingsSwitch(
          state = switchMemoryState.value,
          title = { Text(text = "Switch") },
          subtitle = { Text(text = "Memory state") },
          onCheckedChange = { switchMemoryState.value = it },
        )
        val switchDiskState = rememberBooleanSettingState(
          key = "switchDiskState",
          settings = settings,
        )
        SettingsSwitch(
          state = switchDiskState.value,
          title = { Text(text = "Switch") },
          subtitle = { Text(text = "Disk state") },
          onCheckedChange = { switchDiskState.value = it },
        )
      }

      SettingsGroup(
        title = { Text(text = "SettingsCheckbox Tile") }
      ) {
        SettingsCheckbox(
          state = checkState.value,
          title = { Text(text = "Logger enabled") },
          onCheckedChange = { newState -> checkState.value = newState },
          subtitle = {
            if (checkState.value) {
              Text(text = "All your data belongs to us!")
            } else {
              Text(text = "Don't worry, we won't track you")
            }
          },
        )
        val checkboxMemoryState = rememberMemoryBooleanSettingState()
        SettingsCheckbox(
          state = checkboxMemoryState.value,
          title = { Text(text = "Checkbox") },
          subtitle = { Text(text = "Memory state") },
          onCheckedChange = { checkboxMemoryState.value = it },
        )
        val checkboxDiskState = rememberBooleanSettingState(
          key = "checkboxDiskState",
          settings = settings,
        )
        SettingsCheckbox(
          state = checkboxDiskState.value,
          title = { Text(text = "Checkbox") },
          subtitle = { Text(text = "Disk state") },
          onCheckedChange = { checkboxDiskState.value = it },
        )
      }

      SettingsGroup(
        title = { Text(text = "SettingsTriStateCheckbox Tile") },
      ) {
        val triStateCheckboxMemoryState = rememberMemoryTriStateSettingState()
        SettingsTriStateCheckbox(
          state = triStateCheckboxMemoryState.value,
          title = { Text(text = "TriStateCheckbox") },
          subtitle = { Text(text = "Memory") },
          onCheckedChange = { newState -> triStateCheckboxMemoryState.value = newState },
        )
        val triStateCheckboxDiskState = rememberTriStateSetting(
          key = "triStateCheckbox",
          settings = settings,
        )
        SettingsTriStateCheckbox(
          state = triStateCheckboxDiskState.value,
          title = { Text(text = "TriStateCheckbox") },
          subtitle = { Text(text = "Disk") },
          onCheckedChange = { newState -> triStateCheckboxDiskState.value = newState },
        )
      }

      SettingsMenuLink(
        title = { Text(text = "Click me") },
        subtitle = if (clicksCounterState.value == 0) {
          null
        } else {
          { Text(text = "Clicked ${clicksCounterState.value} times") }
        },
        onClick = { clicksCounterState.value += 1 }
      )
    }
  }
}