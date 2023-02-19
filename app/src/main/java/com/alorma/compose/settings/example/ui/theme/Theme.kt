package com.alorma.compose.settings.example.ui.theme

import android.os.Build
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColorScheme
import androidx.compose.material.dynamicDarkColorScheme
import androidx.compose.material.dynamicLightColorScheme
import androidx.compose.material.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

@Composable
fun ComposeSettingsTheme(
  darkThemePreference: Boolean,
  dynamicThemePreference: Boolean,
  content: @Composable () -> Unit,
) {
  MaterialTheme(
    colorScheme = if (Build.VERSION.SDK_INT >= 31 && dynamicThemePreference) {
      if (darkThemePreference) {
        dynamicDarkColorScheme(LocalContext.current)
      } else {
        dynamicLightColorScheme(LocalContext.current)
      }
    } else {
      if (darkThemePreference) {
        darkColorScheme()
      } else {
        lightColorScheme()
      }
    },
    content = content
  )
}