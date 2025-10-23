package com.alorma.compose.settings.sample.shared

import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.ComposeViewport
import theme.ComposeSettingsTheme

@OptIn(ExperimentalComposeUiApi::class)
fun main() {
  ComposeViewport(
    configure = { isA11YEnabled = true },
  ) {
    ComposeSettingsTheme {
      SampleApp()
    }
  }
}
