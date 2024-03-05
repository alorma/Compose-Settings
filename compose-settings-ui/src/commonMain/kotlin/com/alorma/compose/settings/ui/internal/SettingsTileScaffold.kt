package com.alorma.compose.settings.ui.internal

import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemColors
import androidx.compose.material3.ListItemDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.Dp

@Composable
internal fun SettingsTileScaffold(
  enabled: Boolean = true,
  title: @Composable () -> Unit,
  subtitle: @Composable (() -> Unit)? = null,
  icon: @Composable (() -> Unit)? = null,
  colors: ListItemColors = ListItemDefaults.colors(),
  tonalElevation: Dp = ListItemDefaults.Elevation,
  shadowElevation: Dp = ListItemDefaults.Elevation,
  action: @Composable ((Boolean) -> Unit)? = null,
) {
  ListItem(
    headlineContent = { title() },
    supportingContent = if (subtitle == null) {
      null
    } else {
      { subtitle() }
    },
    leadingContent = if (icon == null) {
      null
    } else {
      { icon() }
    },
    trailingContent = if (action == null) {
      null
    } else {
      { action(enabled) }
    },
    colors = colors,
    tonalElevation = tonalElevation,
    shadowElevation = shadowElevation,
  )
}