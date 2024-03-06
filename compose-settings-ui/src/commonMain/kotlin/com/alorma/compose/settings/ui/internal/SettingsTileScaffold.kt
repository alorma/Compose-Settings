package com.alorma.compose.settings.ui.internal

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemColors
import androidx.compose.material3.ListItemDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp

@Composable
internal fun SettingsTileScaffold(
  title: @Composable () -> Unit,
  modifier: Modifier = Modifier,
  subtitle: @Composable (() -> Unit)? = null,
  icon: @Composable (() -> Unit)? = null,
  colors: ListItemColors = ListItemDefaults.colors(),
  tonalElevation: Dp = ListItemDefaults.Elevation,
  shadowElevation: Dp = ListItemDefaults.Elevation,
  action: @Composable (() -> Unit)? = null,
) {
  ListItem(
    modifier = Modifier.fillMaxWidth().then(modifier),
    headlineContent = { title() },
    supportingContent = subtitle,
    leadingContent = icon,
    trailingContent = action,
    colors = colors,
    tonalElevation = tonalElevation,
    shadowElevation = shadowElevation,
  )
}