package com.alorma.compose.settings.sample.shared

import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.CanvasBasedWindow
import org.jetbrains.compose.resources.configureWebResources

@OptIn(ExperimentalComposeUiApi::class)
fun main() {
  configureWebResources {
    resourcePathMapping { path -> "./$path" }
  }
  CanvasBasedWindow(
    title = "Compose Settings - sample",
    canvasElementId = "ComposeTarget",
  ) {
    SampleApp()
  }
}

