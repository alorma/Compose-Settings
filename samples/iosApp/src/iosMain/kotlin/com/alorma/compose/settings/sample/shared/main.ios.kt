package com.alorma.compose.settings.sample.shared


import androidx.compose.ui.window.ComposeUIViewController
import platform.UIKit.UIViewController
import theme.ComposeSettingsTheme

fun MainViewController(): UIViewController {
  return ComposeUIViewController {
    ComposeSettingsTheme {
      SampleApp()
    }
  }
}
