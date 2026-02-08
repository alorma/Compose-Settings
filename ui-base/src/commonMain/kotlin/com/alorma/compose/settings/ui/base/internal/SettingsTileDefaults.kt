package com.alorma.compose.settings.ui.base.internal

import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp

object SettingsTileDefaults {
  val Elevation: Dp = ListItemDefaults.Elevation
  const val DisabledAlpha: Float = 0.38f

  @Composable
  fun shape(): Shape = ListItemDefaults.shape

  @Composable
  fun textStyles(
    groupTitleStyle: TextStyle =
      LocalSettingsTextStyles.current?.groupTitleStyle
        ?: MaterialTheme.typography.titleMedium,
    titleStyle: TextStyle =
      LocalSettingsTextStyles.current?.titleStyle
        ?: MaterialTheme.typography.bodyLarge,
    subtitleStyle: TextStyle =
      LocalSettingsTextStyles.current?.subtitleStyle
        ?: MaterialTheme.typography.bodyMedium,
  ): SettingsTextStyles =
    SettingsTextStyles(
      groupTitleStyle = groupTitleStyle,
      titleStyle = titleStyle,
      subtitleStyle = subtitleStyle,
    )

  @Composable
  fun colors(
    containerColor: Color =
      LocalSettingsTileColors.current?.containerColor
        ?: MaterialTheme.colorScheme.surface,
    titleColor: Color =
      LocalSettingsTileColors.current?.titleColor
        ?: MaterialTheme.colorScheme.primary,
    groupTitleColor: Color =
      LocalSettingsTileColors.current?.groupTitleColor
        ?: MaterialTheme.colorScheme.onBackground,
    iconColor: Color = LocalSettingsTileColors.current?.iconColor ?: MaterialTheme.colorScheme.onSurface,
    subtitleColor: Color = LocalSettingsTileColors.current?.subtitleColor ?: MaterialTheme.colorScheme.onSurface,
    actionColor: Color =
      LocalSettingsTileColors.current?.actionColor
        ?: MaterialTheme.colorScheme.primary,
    disabledTitleColor: Color = titleColor.copy(alpha = DisabledAlpha),
    disabledGroupTitleColor: Color = groupTitleColor.copy(alpha = DisabledAlpha),
    disabledIconColor: Color = iconColor.copy(alpha = DisabledAlpha),
    disabledSubtitleColor: Color = subtitleColor.copy(alpha = DisabledAlpha),
    disabledActionColor: Color = actionColor.copy(alpha = DisabledAlpha),
  ): SettingsTileColors =
    SettingsTileColors(
      containerColor = containerColor,
      titleColor = titleColor,
      groupTitleColor = groupTitleColor,
      iconColor = iconColor,
      subtitleColor = subtitleColor,
      actionColor = actionColor,
      disabledTitleColor = disabledTitleColor,
      disabledGroupTitleColor = disabledGroupTitleColor,
      disabledIconColor = disabledIconColor,
      disabledSubtitleColor = disabledSubtitleColor,
      disabledActionColor = disabledActionColor,
    )
}
