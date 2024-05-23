package com.alorma.compose.settings.ui

import androidx.compose.foundation.selection.toggleable
import androidx.compose.material3.ListItemColors
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonColors
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.Dp
import com.alorma.compose.settings.ui.base.internal.LocalSettingsGroupEnabled
import com.alorma.compose.settings.ui.base.internal.SettingsTileScaffold

@Composable
fun SettingsRadioButton(
  state: Boolean,
  title: @Composable () -> Unit,
  modifier: Modifier = Modifier,
  enabled: Boolean = LocalSettingsGroupEnabled.current,
  icon: @Composable (() -> Unit)? = null,
  subtitle: @Composable (() -> Unit)? = null,
  checkboxColors: RadioButtonColors = RadioButtonDefaults.colors(),
  colors: ListItemColors = ListItemDefaults.colors(),
  tonalElevation: Dp = ListItemDefaults.Elevation,
  shadowElevation: Dp = ListItemDefaults.Elevation,
  onClick: () -> Unit,
) {
  SettingsTileScaffold(
    modifier = Modifier.toggleable(
      enabled = enabled,
      value = state,
      role = Role.RadioButton,
      onValueChange = { onClick() },
    ).then(modifier),
    title = title,
    subtitle = subtitle,
    icon = icon,
    colors = colors,
    tonalElevation = tonalElevation,
    shadowElevation = shadowElevation,
  ) {
    RadioButton(
      enabled = enabled,
      selected = state,
      onClick = onClick,
      colors = checkboxColors,
    )
  }
}
