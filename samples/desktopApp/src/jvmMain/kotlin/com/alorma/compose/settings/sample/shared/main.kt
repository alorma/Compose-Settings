package com.alorma.compose.settings.sample.shared

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import theme.ComposeSettingsTheme

fun main() =
  application {
    Window(
      onCloseRequest = ::exitApplication,
      title = "Compose Settings - sample",
    ) {
      val darkModeState = remember { mutableStateOf(false) }
      ComposeSettingsTheme(
        darkModeState = darkModeState.value,
      ) {
        SampleApp(
          darkModeState = darkModeState.value,
          onDarkModeState = { darkModeState.value = it },
        )
      }
    }
  }
