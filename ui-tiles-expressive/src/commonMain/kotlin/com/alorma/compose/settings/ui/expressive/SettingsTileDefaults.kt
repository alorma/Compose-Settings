package com.alorma.compose.settings.ui.expressive

import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.ListItemShapes
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import com.alorma.compose.settings.ui.core.SettingsTileCoreDefaults

object SettingsTileDefaults : SettingsTileCoreDefaults() {
  override val Elevation: Dp = ListItemDefaults.Elevation

  @Composable
  fun shape(): Shape = ListItemDefaults.shape

  @OptIn(ExperimentalMaterial3ExpressiveApi::class)
  @Composable
  fun segmentedShape(): ListItemShapes = ListItemDefaults.shapes()
}
