package com.alorma.compose.settings.sample.shared

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.ComposeViewport
import org.jetbrains.skiko.wasm.onWasmReady
import theme.ComposeSettingsTheme

@OptIn(ExperimentalComposeUiApi::class)
fun main() {
  onWasmReady {
    ComposeViewport(
      configure = { isA11YEnabled = true },
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
}
