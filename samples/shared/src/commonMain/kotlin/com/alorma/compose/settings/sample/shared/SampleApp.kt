package com.alorma.compose.settings.sample.shared

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun SampleApp(
  darkModeState: Boolean,
  onDarkModeState: (Boolean) -> Unit,
  modifier: Modifier = Modifier,
) {
  SettingsScreen(
    modifier = modifier,
    darkModeState = darkModeState,
    onDarkModeState = onDarkModeState,
  )
}
