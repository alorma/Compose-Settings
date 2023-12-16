package com.alorma.compose.settings.sample.shared

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.alorma.compose.settings.storage.base.getValue
import com.alorma.compose.settings.storage.base.setValue
import com.alorma.compose.settings.storage.memory.rememberMemoryBooleanSettingState
import com.alorma.compose.settings.ui.SettingsSwitch

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun SettingsScreen() {
  Scaffold { padding ->
    Column(
      modifier = Modifier
        .consumeWindowInsets(padding)
        .padding(top = padding.calculateTopPadding()),
    ) {
      var darkModeState by rememberMemoryBooleanSettingState(defaultValue = false)

      SettingsSwitch(
        state = darkModeState,
        title = { Text(text = "Dark mode") },
        onCheckedChange = { darkModeState = it },
      )
    }
  }
}