package com.alorma.compose.settings.sample.shared

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.padding
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
import com.alorma.compose.settings.ui.SettingsMenuLink
import com.alorma.compose.settings.ui.SettingsSwitch
import com.alorma.compose.settings.ui.SettingsTriStateCheckbox
import com.russhwolf.settings.Settings

@Composable
fun SettingsScreen(
  settings: Settings,
  darkTheme: Boolean,
  onDarkThemeChange: (Boolean) -> Unit,
) {
  Scaffold { padding ->
    Column(
      modifier = Modifier
        .consumeWindowInsets(padding)
        .padding(top = padding.calculateTopPadding()),
    ) {

      val intState = rememberIntSettingState(
        key = "clicked",
        defaultValue = 0,
        settings = settings,
      )

      val checkState = rememberBooleanSettingState(
        key = "checkbox",
        defaultValue = true,
        settings = settings,
      )
      val checkTriState = rememberTriStateSetting(
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
        subtitle = if (intState.value == 0) {
          null
        } else {
          { Text(text = "Clicked ${intState.value} times") }
        },
        onClick = { intState.value += 1 }
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
        state = checkTriState.value,
        title = { Text(text = "Online status") },
        onCheckedChange = { newState -> checkTriState.value = newState },
        subtitle = {
          when (checkTriState.value) {
            true -> Text(text = "You are connected!")
            false -> Text(text = "Probably out of the office")
            null -> Text(text = "I'm confused")
          }
        },
      )

      HorizontalDivider()

      SettingsMenuLink(
        title = { Text(text = "Reset click") },
        onClick = {
          intState.reset()
          checkTriState.reset()
        }
      )
    }
  }
}