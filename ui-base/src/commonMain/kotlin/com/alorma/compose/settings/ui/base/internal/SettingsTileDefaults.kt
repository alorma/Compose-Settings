package com.alorma.compose.settings.ui.base.internal

import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp

object SettingsTileDefaults {
  val Elevation: Dp = ListItemDefaults.Elevation
  const val DisabledAlpha: Float = 0.38f

  @Composable
  fun colors(
    containerColor: Color =
      LocalSettingsTileColors.current?.containerColor
        ?: MaterialTheme.colorScheme.surface,
    titleColor: Color =
      LocalSettingsTileColors.current?.titleColor
        ?: MaterialTheme.colorScheme.primary,
    iconColor: Color = LocalSettingsTileColors.current?.iconColor ?: MaterialTheme.colorScheme.onSurface,
    subtitleColor: Color = LocalSettingsTileColors.current?.subtitleColor ?: MaterialTheme.colorScheme.onSurface,
    actionColor: Color =
      LocalSettingsTileColors.current?.actionColor
        ?: MaterialTheme.colorScheme.primary,
    disabledTitleColor: Color = titleColor.copy(alpha = DisabledAlpha),
    disabledIconColor: Color = iconColor.copy(alpha = DisabledAlpha),
    disabledSubtitleColor: Color = subtitleColor.copy(alpha = DisabledAlpha),
    disabledActionColor: Color = actionColor.copy(alpha = DisabledAlpha),
  ): SettingsTileColors =
    SettingsTileColors(
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
