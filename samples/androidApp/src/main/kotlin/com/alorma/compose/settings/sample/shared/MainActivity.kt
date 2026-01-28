package com.alorma.compose.settings.sample.shared

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import theme.ComposeSettingsTheme

class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    enableEdgeToEdge()

    setContent {
      // Automatically follows system dark mode and uses Material You dynamic colors on Android 12+
      ComposeSettingsTheme {
        SampleApp()
      }
    }
  }
}
