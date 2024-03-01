package com.alorma.compose.settings.sample.shared

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.alorma.compose.settings.storage.base.getValue
import com.alorma.compose.settings.storage.base.setValue
import com.alorma.compose.settings.storage.disk.rememberIntSettingState
import com.alorma.compose.settings.ui.SettingsMenuLink
import com.alorma.compose.settings.ui.SettingsSwitch
import com.russhwolf.settings.Settings

@OptIn(ExperimentalLayoutApi::class)
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
    }
  }
}