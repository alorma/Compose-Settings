package com.alorma.compose.settings.sample.shared


import androidx.compose.ui.window.ComposeUIViewController
import platform.UIKit.UIViewController

fun MainViewController(): UIViewController =
  ComposeUIViewController {
    SampleApp()
  }