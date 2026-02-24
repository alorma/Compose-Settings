package com.alorma.compose.settings.ui

import androidx.compose.foundation.clickable
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.semantics.SemanticsPropertyReceiver
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.Dp
import com.alorma.compose.settings.ui.core.LocalSettingsGroupEnabled
import com.alorma.compose.settings.ui.core.SettingsTileColors
import com.alorma.compose.settings.ui.core.SettingsTextStyles

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
  shape: Shape = SettingsTileDefaults.shape(),
  tonalElevation: Dp = SettingsTileDefaults.Elevation,
  shadowElevation: Dp = SettingsTileDefaults.Elevation,
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
    shape = shape,
    tonalElevation = tonalElevation,
    shadowElevation = shadowElevation,
    action = action,
  )
}
