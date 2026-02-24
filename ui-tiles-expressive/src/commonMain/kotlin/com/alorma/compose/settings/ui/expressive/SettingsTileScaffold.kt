package com.alorma.compose.settings.ui.expressive

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.ListItemColors
import androidx.compose.material3.ListItemElevation
import androidx.compose.material3.ListItemShapes
import androidx.compose.material3.SegmentedListItem
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun SettingsTileScaffold(
  title: @Composable () -> Unit,
  modifier: Modifier = Modifier,
  enabled: Boolean = true,
  subtitle: @Composable (() -> Unit)? = null,
  icon: @Composable (() -> Unit)? = null,
  colors: ListItemColors = SettingsTileDefaults.colors(),
  shapes: ListItemShapes = SettingsTileDefaults.shapes(),
  elevation: ListItemElevation = SettingsTileDefaults.elevation(),
  action: @Composable (() -> Unit)? = null,
) {
  val decoratedSubtitle: @Composable (() -> Unit)? = subtitle?.let { { subtitle() } }
  val decoratedIcon: @Composable (() -> Unit)? = icon?.let { { icon() } }
  val decoratedAction: @Composable (() -> Unit)? = action?.let { { action() } }

  SegmentedListItem(
    selected = false,
    onClick = {},
    shapes = shapes,
    modifier = Modifier.fillMaxWidth().then(modifier),
    enabled = enabled,
    leadingContent = decoratedIcon,
    trailingContent = decoratedAction,
    supportingContent = decoratedSubtitle,
    colors = colors,
    elevation = elevation,
  ) {
    title()
  }
}
