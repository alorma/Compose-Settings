package com.alorma.compose.settings.sample.shared

import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.CanvasBasedWindow
import org.jetbrains.skiko.wasm.onWasmReady
import theme.ComposeSettingsTheme

@OptIn(ExperimentalComposeUiApi::class)
fun main() {
  onWasmReady {
    CanvasBasedWindow(
      title = "Compose Settings - sample",
      canvasElementId = "ComposeTarget",
    ) {
      ComposeSettingsTheme {
        SampleApp()
      }
    }
  }
}

