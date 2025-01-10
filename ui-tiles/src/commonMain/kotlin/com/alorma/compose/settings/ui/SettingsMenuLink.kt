package com.alorma.compose.settings.ui

import androidx.compose.foundation.clickable
import androidx.compose.material3.ListItemColors
import androidx.compose.material3.ListItemDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import com.alorma.compose.settings.ui.base.internal.LocalSettingsGroupEnabled
import com.alorma.compose.settings.ui.base.internal.SettingsTileDefaults
import com.alorma.compose.settings.ui.base.internal.SettingsTileScaffold

@Composable
fun SettingsMenuLink(
  title: @Composable () -> Unit,
  modifier: Modifier = Modifier,
  enabled: Boolean = LocalSettingsGroupEnabled.current,
  icon: (@Composable () -> Unit)? = null,
  subtitle: (@Composable () -> Unit)? = null,
  action: (@Composable () -> Unit)? = null,
  colors: ListItemColors = SettingsTileDefaults.colors(),
  tonalElevation: Dp = ListItemDefaults.Elevation,
  shadowElevation: Dp = ListItemDefaults.Elevation,
  onClick: () -> Unit,
) {
  SettingsTileScaffold(
    modifier = Modifier.clickable(
      enabled = enabled,
      onClick = onClick,
    ).then(modifier),
    enabled = enabled,
    title = title,
    subtitle = subtitle,
    icon = icon,
    colors = colors,
    tonalElevation = tonalElevation,
    shadowElevation = shadowElevation,
    action = action,
  )
}
