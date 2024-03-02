package com.alorma.compose.settings.sample.shared

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.alorma.compose.settings.storage.disk.rememberBooleanSettingState
import com.alorma.compose.settings.storage.disk.rememberIntSettingState
import com.alorma.compose.settings.storage.disk.rememberTriStateSetting
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

      SettingsMenuLink(
        title = { Text(text = "Generic settings") },
        action = {
          IconButton(
            onClick = {},
          ) {
            Icon(
              imageVector = Icons.Default.Settings,
              contentDescription = null,
            )
          }
        },
        onClick = {},
      )

      HorizontalDivider()

      SettingsSwitch(
        state = darkTheme,
        title = { Text(text = "Dark mode") },
        onCheckedChange = onDarkThemeChange,
      )

      HorizontalDivider()

      SettingsMenuLink(
        title = { Text(text = "Click me") },
        subtitle = if (clicksCounterState.value == 0) {
          null
        } else {
          { Text(text = "Clicked ${clicksCounterState.value} times") }
        },
        onClick = { clicksCounterState.value += 1 }
      )

      HorizontalDivider()

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

      HorizontalDivider()

      SettingsTriStateCheckbox(
        state = onlineStatusState.value,
        title = { Text(text = "Online status") },
        onCheckedChange = { newState -> onlineStatusState.value = newState },
        subtitle = {
          when (onlineStatusState.value) {
            true -> Text(text = "You are connected!")
            false -> Text(text = "Probably out of the office")
            null -> Text(text = "I'm confused")
          }
        },
      )

      HorizontalDivider()

      SettingsGroup(
        title = { Text(text = "Reset") },
      ) {
        SettingsMenuLink(
          title = { Text(text = "Reset clicks") },
          onClick = { clicksCounterState.reset() }
        )
        SettingsMenuLink(
          title = { Text(text = "Reset theme") },
          onClick = { onDarkModeReset() }
        )
        SettingsMenuLink(
          title = { Text(text = "Reset online status") },
          onClick = { onlineStatusState.reset() }
        )
      }
    }
  }
}