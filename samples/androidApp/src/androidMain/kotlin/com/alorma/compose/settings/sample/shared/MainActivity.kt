package com.alorma.compose.settings.sample.shared

import android.content.Context
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.ui.platform.LocalContext
import theme.ComposeSettingsTheme
import theme.DarkColorScheme
import theme.LightColorScheme

class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    enableEdgeToEdge()

    setContent {
      val context: Context = LocalContext.current

      ComposeSettingsTheme(
        colorScheme = { darkModeState ->
          val isS = Build.VERSION.SDK_INT >= Build.VERSION_CODES.S
          if (isS) {
            if (darkModeState) {
              dynamicDarkColorScheme(context)
            } else {
              dynamicLightColorScheme(context)
            }
          } else if (darkModeState) {
            DarkColorScheme
          } else {
            LightColorScheme
          }
        },
      ) {
        SampleApp()
      }
    }
  }
}
