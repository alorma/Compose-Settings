package com.alorma.compose.settings.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ListItemColors
import androidx.compose.material3.ListItemDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.alorma.compose.settings.ui.base.internal.LocalSettingsGroupEnabled
import com.alorma.compose.settings.ui.base.internal.SettingsTileScaffold

@Composable
fun SettingsTheme(
  stateColors: List<Color>,
  title: @Composable () -> Unit,
  modifier: Modifier = Modifier,
  enabled: Boolean = LocalSettingsGroupEnabled.current,
  icon: @Composable (() -> Unit)? = null,
  subtitle: @Composable (() -> Unit)? = null,
  colors: ListItemColors = ListItemDefaults.colors(),
  tonalElevation: Dp = ListItemDefaults.Elevation,
  shadowElevation: Dp = ListItemDefaults.Elevation,
  action: @Composable (() -> Unit)? = null,
  onClick: (() -> Unit)? = null,
) {
  SettingsTileScaffold(
    modifier = Modifier.clickable(
      enabled = enabled,
      onClick = { onClick?.invoke() },
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
  Row(
    horizontalArrangement = Arrangement.SpaceEvenly,
  ) {
    stateColors.forEach { color ->
      Card(
        modifier = Modifier
          .height(80.dp)
          .weight(1f)
          .padding(8.dp),
        colors = CardDefaults.cardColors(containerColor = color)
      ) {

      }
    }
  }
}