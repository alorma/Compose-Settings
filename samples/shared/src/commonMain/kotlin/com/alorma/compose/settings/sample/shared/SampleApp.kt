package com.alorma.compose.settings.sample.shared

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import com.alorma.compose.settings.ui.base.internal.LocalSettingsTileColors
import com.alorma.compose.settings.ui.base.internal.SettingsTileDefaults

@Composable
fun SampleApp(
  darkModeState: Boolean,
  onDarkModeState: (Boolean) -> Unit,
  modifier: Modifier = Modifier,
) {
  CompositionLocalProvider(
    LocalSettingsTileColors provides SettingsTileDefaults.colors(
      containerColor = MaterialTheme.colorScheme.surfaceContainerLowest,
    )
  ) {
    SettingsScreen(
      modifier = modifier,
      darkModeState = darkModeState,
      onDarkModeState = onDarkModeState,
    )
  }
}
