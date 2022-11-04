package com.alorma.compose.settings.example.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable

@Composable
fun ComposeSettingsTheme(
  isSystemDark: Boolean = isSystemInDarkTheme(),
  content: @Composable () -> Unit,
) {
  MaterialTheme(
    colors = if (isSystemDark) {
      darkColors()
    } else {
      lightColors()
    },
    typography = Typography,
    shapes = Shapes,
    content = content
  )
}