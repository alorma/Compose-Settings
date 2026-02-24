package com.alorma.compose.settings.ui.expressive

import androidx.compose.foundation.selection.triStateToggleable
import androidx.compose.material3.CheckboxColors
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.ListItemElevation
import androidx.compose.material3.ListItemShapes
import androidx.compose.material3.TriStateCheckbox
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.SemanticsPropertyReceiver
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.state.ToggleableState
import com.alorma.compose.settings.ui.core.LocalSettingsGroupEnabled
import com.alorma.compose.settings.ui.core.SettingsTileColors
import com.alorma.compose.settings.ui.core.SettingsTextStyles

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun SettingsTriStateCheckbox(
  state: Boolean?,
  title: @Composable () -> Unit,
  modifier: Modifier = Modifier,
  enabled: Boolean = LocalSettingsGroupEnabled.current,
  icon: @Composable (() -> Unit)? = null,
  subtitle: @Composable (() -> Unit)? = null,
  colors: SettingsTileColors = SettingsTileDefaults.colors(),
  checkboxColors: CheckboxColors =
    CheckboxDefaults.colors(
      checkedColor = colors.actionColor(enabled),
      checkmarkColor = contentColorFor(colors.actionColor(enabled)),
      disabledCheckedColor = colors.actionColor(enabled),
    ),
  textStyles: SettingsTextStyles = SettingsTileDefaults.textStyles(),
  shapes: ListItemShapes = SettingsTileDefaults.shapes(),
  elevation: ListItemElevation = SettingsTileDefaults.elevation(),
  semanticProperties: (SemanticsPropertyReceiver.() -> Unit) = {},
  onCheckedChange: (Boolean) -> Unit = {},
) {
  val update: () -> Unit = { onCheckedChange(state?.not() ?: true) }
  SettingsTileScaffold(
    modifier =
      Modifier
        .triStateToggleable(
          state = mapNullableBooleanToToggleableState(state),
          enabled = enabled,
          role = Role.Checkbox,
          onClick = update,
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
  ) {
    TriStateCheckbox(
      modifier = Modifier.clearAndSetSemantics { },
      enabled = enabled,
      state = mapNullableBooleanToToggleableState(state),
      onClick = update,
      colors = checkboxColors,
    )
  }
}

private fun mapNullableBooleanToToggleableState(state: Boolean?) =
  when (state) {
    true -> ToggleableState.On
    false -> ToggleableState.Off
    null -> ToggleableState.Indeterminate
  }
