package com.alorma.compose.settings.example.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

@Composable
fun ComposeSettingsTheme(
  isSystemDark: Boolean = isSystemInDarkTheme(),
  isDynamic: Boolean = Build.VERSION.SDK_INT > Build.VERSION_CODES.S,
  content: @Composable () -> Unit,
) {
  MaterialTheme(
    colorScheme = if (isDynamic) {
      if (isSystemDark) {
        dynamicDarkColorScheme(LocalContext.current)
      } else {
        dynamicLightColorScheme(LocalContext.current)
      }
    } else {
      if (isSystemDark) {
        darkColorScheme()
      } else {
        lightColorScheme()
      }
    },
    content = content
  )
}