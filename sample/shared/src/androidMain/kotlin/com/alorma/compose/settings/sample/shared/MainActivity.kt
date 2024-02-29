package com.alorma.compose.settings.sample.shared

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.alorma.compose.settings.sample.theme.ComposeSettingsTheme
import com.alorma.compose.settings.storage.base.getValue
import com.alorma.compose.settings.storage.base.setValue
import com.alorma.compose.settings.storage.memory.rememberMemoryBooleanSettingState

class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      var darkModeState by rememberMemoryBooleanSettingState(defaultValue = false)
      ComposeSettingsTheme(
        darkTheme = darkModeState,
      ) {
        SettingsScreen(
          darkTheme = darkModeState,
          onDarkThemeChange = { darkModeState = it }
        )
      }
    }
  }
}
