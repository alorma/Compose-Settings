package com.alorma.compose.settings.sample.shared.previews

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.alorma.compose.settings.storage.memory.rememberMemoryBooleanSettingState
import com.alorma.compose.settings.ui.SettingsMenuLink
import com.alorma.compose.settings.ui.SettingsSegmented
import theme.ComposeSettingsTheme

@SuperPreviews
@Composable
internal fun SettingsSegmentedPreview() {
  val darkModeState = rememberMemoryBooleanSettingState(
    defaultValue = isSystemInDarkTheme(),
  )

  ComposeSettingsTheme(
    darkModeState = darkModeState,
  ) {
    Surface {
      val darkModes = listOf(true, false)

      SettingsSegmented(
        title = { Text("Theme mode") },
        items = darkModes,
        selectedItem = darkModeState.value,
        onItemSelected = { darkMode -> darkModeState.value = darkMode },
        itemTitleMap = { item ->
          when (item) {
            true -> "Dark"
            false -> "Light"
          }
        },
      )
    }
  }
}
