package com.alorma.compose.settings.ui.expressive

import androidx.compose.foundation.clickable
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.ListItemElevation
import androidx.compose.material3.ListItemShapes
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.SemanticsPropertyReceiver
import androidx.compose.ui.semantics.semantics
import com.alorma.compose.settings.ui.core.LocalSettingsGroupEnabled

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun SettingsMenuLink(
  title: @Composable () -> Unit,
  modifier: Modifier = Modifier,
  enabled: Boolean = LocalSettingsGroupEnabled.current,
  icon: (@Composable () -> Unit)? = null,
  subtitle: (@Composable () -> Unit)? = null,
  action: (@Composable () -> Unit)? = null,
  colors: SettingsTileColors = SettingsTileDefaults.colors(),
  textStyles: SettingsTextStyles = SettingsTileDefaults.textStyles(),
  shapes: ListItemShapes = SettingsTileDefaults.shapes(),
  elevation: ListItemElevation = SettingsTileDefaults.elevation(),
  semanticProperties: (SemanticsPropertyReceiver.() -> Unit) = {},
  onClick: () -> Unit,
) {
  SettingsTileScaffold(
    modifier =
      Modifier
        .clickable(
          enabled = enabled,
          onClick = onClick,
        ).semantics(properties = semanticProperties)
        .then(modifier),
    enabled = enabled,
    title = title,
    subtitle = subtitle,
    icon = icon,
    colors = colors,
    textStyles = textStyles,
    shapes = shapes,
    elevation = elevation,
    action = action,
  )
}
