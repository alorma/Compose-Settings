package com.alorma.compose.settings.sample.shared

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import theme.ComposeSettingsTheme

fun main() =
  application {
    Window(
      onCloseRequest = ::exitApplication,
      title = "Compose Settings - sample",
    ) {
      ComposeSettingsTheme {
        SampleApp()
      }
    }
  }
