package com.alorma.compose.settings.ui

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp

@Composable
fun SettingsTileScaffold(
  title: @Composable () -> Unit,
  modifier: Modifier = Modifier,
  subtitle: @Composable (() -> Unit)? = null,
  icon: @Composable (() -> Unit)? = null,
  colors: ListItemColors = SettingsTileDefaults.colors(),
  shape: Shape = SettingsTileDefaults.shape(),
  tonalElevation: Dp = SettingsTileDefaults.Elevation,
  shadowElevation: Dp = SettingsTileDefaults.Elevation,
  action: @Composable (() -> Unit)? = null,
) {
  ListItem(
    modifier = Modifier.fillMaxWidth().clip(shape).then(modifier),
    headlineContent = title,
    supportingContent = subtitle,
    leadingContent = icon,
    trailingContent = action,
    colors = colors,
    tonalElevation = tonalElevation,
    shadowElevation = shadowElevation,
  )
}
