package com.alorma.compose.settings.sample

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.alorma.compose.settings.storage.base.getValue
import com.alorma.compose.settings.storage.base.setValue
import com.alorma.compose.settings.storage.memory.rememberMemoryBooleanSettingState
import com.alorma.compose.settings.ui.SettingsSwitch

@Composable
fun SettingsScreen() {
  Scaffold {
    Column {
      var darkModeState by rememberMemoryBooleanSettingState(defaultValue = false)

      SettingsSwitch(
        state = darkModeState,
        title = { Text(text = "Dark mode") },
        onCheckedChange = { darkModeState = it },
      )
    }
  }
}