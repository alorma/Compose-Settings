package com.alorma.compose.settings.ui.base.internal

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

object SettingsTileDefaults {
  private const val disabledAlpha: Float = 0.38f

  @Composable
  fun colors(
    containerColor: Color = LocalSettingsTileColors.current?.containerColor ?: Color.Transparent,
    titleColor: Color = LocalSettingsTileColors.current?.titleColor
      ?: MaterialTheme.colorScheme.onSurface,
    iconColor: Color = LocalSettingsTileColors.current?.iconColor ?: titleColor,
    subtitleColor: Color = LocalSettingsTileColors.current?.subtitleColor ?: titleColor,
    actionColor: Color = LocalSettingsTileColors.current?.actionColor
      ?: MaterialTheme.colorScheme.primary,
    disabledTitleColor: Color = titleColor.copy(alpha = disabledAlpha),
    disabledIconColor: Color = iconColor.copy(alpha = disabledAlpha),
    disabledSubtitleColor: Color = subtitleColor.copy(alpha = disabledAlpha),
    disabledActionColor: Color = actionColor.copy(alpha = disabledAlpha),
  ): SettingsTileColors = SettingsTileColors(
    containerColor = containerColor,
    titleColor = titleColor,
    iconColor = iconColor,
    subtitleColor = subtitleColor,
    actionColor = actionColor,
    disabledTitleColor = disabledTitleColor,
    disabledIconColor = disabledIconColor,
    disabledSubtitleColor = disabledSubtitleColor,
    disabledActionColor = disabledActionColor,
  )
}