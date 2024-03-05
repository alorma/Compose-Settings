package com.alorma.compose.settings.ui

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.selection.toggleable
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxColors
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.ListItemColors
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.Dp
import com.alorma.compose.settings.ui.internal.SettingsTileScaffold

@Composable
fun SettingsCheckbox(
  state: Boolean,
  title: @Composable () -> Unit,
  modifier: Modifier = Modifier,
  enabled: Boolean = true,
  icon: @Composable (() -> Unit)? = null,
  subtitle: @Composable (() -> Unit)? = null,
  checkboxColors: CheckboxColors = CheckboxDefaults.colors(),
  colors: ListItemColors = ListItemDefaults.colors(),
  tonalElevation: Dp = ListItemDefaults.Elevation,
  shadowElevation: Dp = ListItemDefaults.Elevation,
  onCheckedChange: (Boolean) -> Unit,
) {
  val update: (Boolean) -> Unit = { boolean -> onCheckedChange(boolean) }
  Surface {
    Row(
      modifier = Modifier
        .fillMaxWidth()
        .toggleable(
          enabled = enabled,
          value = state,
          role = Role.Switch,
          onValueChange = { update(!state) },
        ).then(modifier),
      verticalAlignment = Alignment.CenterVertically,
    ) {
      SettingsTileScaffold(
        enabled = enabled,
        title = title,
        subtitle = subtitle,
        icon = icon,
        colors = colors,
        tonalElevation = tonalElevation,
        shadowElevation = shadowElevation,
      ) {
        Checkbox(
          enabled = enabled,
          checked = state,
          onCheckedChange = update,
          colors = checkboxColors,
        )
      }
    }
  }
}
