package com.alorma.compose.settings.sample.shared

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.alorma.compose.settings.storage.base.getValue
import com.alorma.compose.settings.storage.base.setValue
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
      SettingsSwitch(
        state = darkTheme,
        title = { Text(text = "Dark mode") },
        onCheckedChange = onDarkThemeChange,
      )

      HorizontalDivider()

      var intState by rememberIntSettingState(
        key = "clicked",
        defaultValue = 0,
        settings = settings,
      )
      SettingsMenuLink(
        title = {
          if (intState == 0) {
            Text(text = "Click me")
          } else {
            Text(text = "Clicked $intState times")
          }
        },
        onClick = { intState += 1 }
      )

      HorizontalDivider()

      var checkState by rememberBooleanSettingState(
        key = "checkbox",
        defaultValue = true,
        settings = settings,
      )
      SettingsCheckbox(
        state = checkState,
        title = { Text(text = "Logger enabled") },
        onCheckedChange = { newState -> checkState = newState },
        subtitle = {
          if (checkState) {
            Text(text = "All your data belongs to us!")
          } else {
            Text(text = "Don't worry, we won't track you")
          }
        },
      )

      HorizontalDivider()

      var checkTriState by rememberTriStateSetting(
        key = "checkboxTriState",
        defaultValue = true,
        settings = settings,
      )
      SettingsTriStateCheckbox(
        state = checkTriState,
        title = { Text(text = "Online status") },
        onCheckedChange = { newState -> checkTriState = newState },
        subtitle = {
          when (checkTriState) {
            true -> Text(text = "You are connected!")
            false -> Text(text = "Probably out of the office")
            null -> Text(text = "I'm confused")
          }
        },
      )
    }
  }
}