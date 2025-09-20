package com.alorma.compose.settings.sample.shared

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.ComposeViewport
import kotlinx.browser.document
import org.jetbrains.compose.resources.configureWebResources
import theme.ComposeSettingsTheme

@OptIn(ExperimentalComposeUiApi::class)
fun main() {
  configureWebResources {
    resourcePathMapping { path -> "./$path" }
  }
  ComposeViewport(
    viewportContainer = document.body!!,
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
