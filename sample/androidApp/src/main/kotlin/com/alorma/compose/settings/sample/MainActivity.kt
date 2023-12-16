package com.alorma.compose.settings.sample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.alorma.compose.settings.sample.shared.SettingsScreen
import com.alorma.compose.settings.sample.theme.ComposeSettingsTheme

class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      ComposeSettingsTheme {
        SettingsScreen()
      }
    }
  }
}
