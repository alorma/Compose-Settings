package com.alorma.compose.settings.ui.base.internal

import androidx.compose.material3.ListItemColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

object SettingsTileDefaults {
  private const val disabledAlpha: Float = 0.38f

  @Composable
  fun colors(
    containerColor: Color = LocalSettingsTileColors.current?.containerColor ?: Color.Transparent,
    headlineColor: Color = LocalSettingsTileColors.current?.headlineColor
      ?: MaterialTheme.colorScheme.onSurface,
    leadingIconColor: Color = LocalSettingsTileColors.current?.leadingIconColor ?: headlineColor,
    overlineColor: Color = LocalSettingsTileColors.current?.overlineColor
      ?: MaterialTheme.colorScheme.primary,
    supportingTextColor: Color = LocalSettingsTileColors.current?.supportingTextColor
      ?: headlineColor,
    trailingIconColor: Color = LocalSettingsTileColors.current?.trailingIconColor ?: headlineColor,
    disabledHeadlineColor: Color = LocalSettingsTileColors.current?.disabledHeadlineColor
      ?: MaterialTheme.colorScheme.onSurface.copy(alpha = disabledAlpha),
    disabledLeadingIconColor: Color = LocalSettingsTileColors.current?.disabledLeadingIconColor
      ?: MaterialTheme.colorScheme.onSurface.copy(alpha = disabledAlpha),
    disabledTrailingIconColor: Color = LocalSettingsTileColors.current?.disabledTrailingIconColor
      ?: MaterialTheme.colorScheme.onSurface.copy(alpha = disabledAlpha),
  ): ListItemColors =
    ListItemColors(
      containerColor = containerColor,
      headlineColor = headlineColor,
      leadingIconColor = leadingIconColor,
      overlineColor = overlineColor,
      supportingTextColor = supportingTextColor,
      trailingIconColor = trailingIconColor,
      disabledHeadlineColor = disabledHeadlineColor,
      disabledLeadingIconColor = disabledLeadingIconColor,
      disabledTrailingIconColor = disabledTrailingIconColor,
    )
}