package com.alorma.compose.settings.ui.base.internal

import androidx.compose.material3.ListItemColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

object SettingsTileDefaults {
  val disabledAlpha: Float = 0.38f

  @Composable
  fun colors(
    containerColor: Color = Color.Transparent,
    headlineColor: Color = MaterialTheme.colorScheme.onSurface,
    leadingIconColor: Color = headlineColor,
    overlineColor: Color = MaterialTheme.colorScheme.primary,
    supportingColor: Color = headlineColor,
    trailingIconColor: Color = headlineColor,
    disabledHeadlineColor: Color = MaterialTheme.colorScheme.onSurface.copy(alpha = disabledAlpha),
    disabledLeadingIconColor: Color = MaterialTheme.colorScheme.onSurface.copy(alpha = disabledAlpha),
    disabledTrailingIconColor: Color = MaterialTheme.colorScheme.onSurface.copy(alpha = disabledAlpha),
  ): ListItemColors =
    ListItemColors(
      containerColor = containerColor,
      headlineColor = headlineColor,
      leadingIconColor = leadingIconColor,
      overlineColor = overlineColor,
      supportingTextColor = supportingColor,
      trailingIconColor = trailingIconColor,
      disabledHeadlineColor = disabledHeadlineColor,
      disabledLeadingIconColor = disabledLeadingIconColor,
      disabledTrailingIconColor = disabledTrailingIconColor,
    )
}