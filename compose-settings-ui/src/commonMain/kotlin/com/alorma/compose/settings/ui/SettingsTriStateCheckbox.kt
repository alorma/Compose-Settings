package com.alorma.compose.settings.ui

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.selection.triStateToggleable
import androidx.compose.material3.CheckboxColors
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.ListItemColors
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.TriStateCheckbox
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.state.ToggleableState
import androidx.compose.ui.unit.Dp
import com.alorma.compose.settings.ui.internal.SettingsTileScaffold

@Composable
fun SettingsTriStateCheckbox(
  state: Boolean?,
  title: @Composable () -> Unit,
  modifier: Modifier = Modifier,
  enabled: Boolean = true,
  icon: @Composable (() -> Unit)? = null,
  subtitle: @Composable (() -> Unit)? = null,
  checkboxColors: CheckboxColors = CheckboxDefaults.colors(),
  colors: ListItemColors = ListItemDefaults.colors(),
  tonalElevation: Dp = ListItemDefaults.Elevation,
  shadowElevation: Dp = ListItemDefaults.Elevation,
  onCheckedChange: (Boolean) -> Unit = {},
) {
  val update: () -> Unit = { onCheckedChange(state?.not() ?: true) }
  Surface {
    Row(
      modifier = Modifier
        .fillMaxWidth()
        .triStateToggleable(
          state = mapNullableBooleanToToggleableState(state),
          onClick = update,
          enabled = enabled,
          role = Role.Checkbox,
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
        TriStateCheckbox(
          enabled = enabled,
          state = mapNullableBooleanToToggleableState(state),
          onClick = update,
          colors = checkboxColors,
        )
      }
    }
  }
}

private fun mapNullableBooleanToToggleableState(state: Boolean?) = when (state) {
  true -> ToggleableState.On
  false -> ToggleableState.Off
  null -> ToggleableState.Indeterminate
}
