package com.alorma.compose.settings.ui.internal

import androidx.compose.material3.LocalContentColor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider

@Composable
internal fun WrapContentColor(
  enabled: Boolean,
  content: @Composable () -> Unit,
) {
  val alpha = if (enabled) {
    1.0f
  } else {
    0.6f
  }
  val contentColor = LocalContentColor.current.copy(alpha = alpha)
  CompositionLocalProvider(LocalContentColor provides contentColor) {
    content()
  }
}
