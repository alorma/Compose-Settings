package com.alorma.compose.settings.ui.expressive

import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.ListItemElevation
import androidx.compose.material3.ListItemShapes
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.Dp
import com.alorma.compose.settings.ui.core.SettingsTileCoreDefaults

object SettingsTileDefaults : SettingsTileCoreDefaults() {
  override val Elevation: Dp = ListItemDefaults.Elevation

  /**
   * Returns the default shapes for expressive Material 3 segmented list items.
   */
  @OptIn(ExperimentalMaterial3ExpressiveApi::class)
  @Composable
  fun shapes(): ListItemShapes = ListItemDefaults.shapes()

  /**
   * Returns the default elevation for expressive Material 3 segmented list items.
   */
  @OptIn(ExperimentalMaterial3ExpressiveApi::class)
  @Composable
  fun elevation(): ListItemElevation = ListItemDefaults.elevation()
}
