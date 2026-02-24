package com.alorma.compose.settings.ui.core

import androidx.compose.material3.ListItemColors
import androidx.compose.material3.ListItemDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.Dp

/**
 * Shared defaults for creating SettingsTileColors and SettingsTextStyles.
 * These are framework-agnostic and work with both standard and expressive Material 3.
 */
abstract class SettingsTileCoreDefaults {

  abstract val Elevation: Dp
  val DisabledAlpha: Float = 0.38f

  @Composable
  abstract fun colors(): ListItemColors
}
