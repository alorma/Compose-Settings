package com.alorma.compose.settings.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ListItemColors
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import com.alorma.compose.settings.ui.internal.SettingsTileScaffold

@Composable
fun SettingsMenuLink(
  modifier: Modifier = Modifier,
  enabled: Boolean = true,
  icon: (@Composable () -> Unit)? = null,
  title: @Composable () -> Unit,
  subtitle: (@Composable () -> Unit)? = null,
  action: (@Composable (Boolean) -> Unit)? = null,
  colors: ListItemColors = ListItemDefaults.colors(),
  tonalElevation: Dp = ListItemDefaults.Elevation,
  shadowElevation: Dp = ListItemDefaults.Elevation,
  onClick: () -> Unit,
) {
  Surface {
    Row(
      modifier = Modifier.fillMaxWidth()
        .clickable(
          enabled = enabled,
          onClick = onClick,
        ).then(modifier),
      verticalAlignment = Alignment.CenterVertically,
    ) {
      SettingsTileScaffold(
        enabled = enabled,
        title = title,
        subtitle = subtitle,
        icon = icon,
        action = action,
        colors = colors,
        tonalElevation = tonalElevation,
        shadowElevation = shadowElevation,
      )
    }
  }
}
