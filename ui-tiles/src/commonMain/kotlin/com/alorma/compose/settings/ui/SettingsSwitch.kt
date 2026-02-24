package com.alorma.compose.settings.ui

import androidx.compose.foundation.selection.toggleable
import androidx.compose.material3.ListItemColors
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchColors
import androidx.compose.material3.SwitchDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.SemanticsPropertyReceiver
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.Dp
import com.alorma.compose.settings.ui.core.LocalSettingsGroupEnabled

@Composable
fun SettingsSwitch(
  state: Boolean,
  title: @Composable () -> Unit,
  modifier: Modifier = Modifier,
  enabled: Boolean = LocalSettingsGroupEnabled.current,
  icon: @Composable (() -> Unit)? = null,
  subtitle: @Composable (() -> Unit)? = null,
  colors: ListItemColors = SettingsTileDefaults.colors(),
  switchColors: SwitchColors = SwitchDefaults.colors(),
  shape: Shape = SettingsTileDefaults.shape(),
  tonalElevation: Dp = SettingsTileDefaults.Elevation,
  shadowElevation: Dp = SettingsTileDefaults.Elevation,
  semanticProperties: (SemanticsPropertyReceiver.() -> Unit) = {},
  onCheckedChange: (Boolean) -> Unit,
) {
  val update: (Boolean) -> Unit = { boolean -> onCheckedChange(boolean) }

  SettingsTileScaffold(
    modifier =
      Modifier
        .toggleable(
          enabled = enabled,
          value = state,
          role = Role.Switch,
          onValueChange = { update(!state) },
        ).semantics(properties = semanticProperties)
        .then(modifier),
    title = title,
    subtitle = subtitle,
    icon = icon,
    colors = colors,
    shape = shape,
    tonalElevation = tonalElevation,
    shadowElevation = shadowElevation,
  ) {
    Switch(
      modifier = Modifier.clearAndSetSemantics { },
      enabled = enabled,
      checked = state,
      onCheckedChange = update,
      colors = switchColors,
    )
  }
}
