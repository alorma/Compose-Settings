package com.alorma.compose.settings.sample.shared.internal

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable

internal fun iconSampleOrNull(iconState: Boolean): (@Composable () -> Unit)? = if (!iconState) {
  null
} else {
  {
    Icon(
      imageVector = Icons.Default.ThumbUp,
      contentDescription = null,
    )
  }
}
