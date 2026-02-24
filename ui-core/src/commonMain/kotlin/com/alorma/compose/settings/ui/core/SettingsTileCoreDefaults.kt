package com.alorma.compose.settings.ui.core

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp

/**
 * Shared defaults for creating SettingsTileColors and SettingsTextStyles.
 * These are framework-agnostic and work with both standard and expressive Material 3.
 */
abstract class SettingsTileCoreDefaults {

  abstract val Elevation: Dp
  val DisabledAlpha: Float = 0.38f

  /**
   * Creates a [SettingsTextStyles] with default values from MaterialTheme typography
   * and respects [LocalSettingsTextStyles] overrides.
   */
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

  /**
   * Creates a [SettingsTileColors] with default values from MaterialTheme color scheme
   * and respects [LocalSettingsTileColors] overrides.
   */
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
    disabledTitleColor: Color = titleColor.copy(alpha = SettingsTileConstants.DisabledAlpha),
    disabledGroupTitleColor: Color = groupTitleColor.copy(alpha = SettingsTileConstants.DisabledAlpha),
    disabledIconColor: Color = iconColor.copy(alpha = SettingsTileConstants.DisabledAlpha),
    disabledSubtitleColor: Color = subtitleColor.copy(alpha = SettingsTileConstants.DisabledAlpha),
    disabledActionColor: Color = actionColor.copy(alpha = SettingsTileConstants.DisabledAlpha),
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
