package com.alorma.compose.settings.sample.shared

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.window.ComposeUIViewController
import platform.UIKit.UIViewController
import theme.ComposeSettingsTheme

fun MainViewController(): UIViewController =
  ComposeUIViewController {
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
