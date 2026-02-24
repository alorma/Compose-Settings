package com.alorma.compose.settings.ui

import androidx.compose.material3.ListItemColors
import androidx.compose.material3.ListItemDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import com.alorma.compose.settings.ui.core.SettingsTileCoreDefaults

object SettingsTileDefaults : SettingsTileCoreDefaults() {
  override val Elevation: Dp = ListItemDefaults.Elevation

  @Composable
  fun shape(): Shape = ListItemDefaults.shape

  @Composable
  override fun colors(): ListItemColors = ListItemDefaults.colors()
}
