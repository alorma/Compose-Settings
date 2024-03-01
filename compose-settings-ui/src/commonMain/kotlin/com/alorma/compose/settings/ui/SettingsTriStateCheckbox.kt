package com.alorma.compose.settings.ui

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.selection.triStateToggleable
import androidx.compose.material3.CheckboxColors
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.TriStateCheckbox
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.state.ToggleableState
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
  onCheckedChange: (Boolean?) -> Unit = {},
) {
  val update: () -> Unit = { onCheckedChange(state?.not() ?: true) }
  Surface {
    Row(
      modifier = modifier
        .fillMaxWidth()
        .triStateToggleable(
          state = mapNullableBooleanToToggleableState(state),
          onClick = update,
          enabled = enabled,
          role = Role.Checkbox,
        ),
      verticalAlignment = Alignment.CenterVertically,
    ) {
      SettingsTileScaffold(
        enabled = enabled,
        title = title,
        subtitle = subtitle,
        icon = icon,
        action = {
          TriStateCheckbox(
            enabled = enabled,
            state = mapNullableBooleanToToggleableState(state),
            onClick = update,
            colors = checkboxColors,
          )
        },
      )
    }
  }
}

private fun mapNullableBooleanToToggleableState(state: Boolean?) = when (state) {
  true -> ToggleableState.On
  false -> ToggleableState.Off
  null -> ToggleableState.Indeterminate
}
