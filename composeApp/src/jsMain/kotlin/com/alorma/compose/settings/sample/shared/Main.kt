package com.alorma.compose.settings.sample.shared

import App
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.CanvasBasedWindow

@OptIn(ExperimentalComposeUiApi::class)
fun main() {
  CanvasBasedWindow("Compose Settings sample") {
    App()
  }
}