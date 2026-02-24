package com.alorma.compose.settings.ui.expressive

import androidx.compose.foundation.selection.toggleable
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.ListItemElevation
import androidx.compose.material3.ListItemShapes
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonColors
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.SemanticsPropertyReceiver
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.ui.semantics.semantics
import com.alorma.compose.settings.ui.core.LocalSettingsGroupEnabled

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun SettingsRadioButton(
  state: Boolean,
  title: @Composable () -> Unit,
  modifier: Modifier = Modifier,
  enabled: Boolean = LocalSettingsGroupEnabled.current,
  icon: @Composable (() -> Unit)? = null,
  subtitle: @Composable (() -> Unit)? = null,
  colors: ListItemColors = SettingsTileDefaults.colors(),
  checkboxColors: RadioButtonColors =
    RadioButtonDefaults.colors(
      selectedColor = colors.actionColor(enabled),
      disabledSelectedColor = colors.actionColor(enabled),
    ),
  textStyles: SettingsTextStyles = SettingsTileDefaults.textStyles(),
  shapes: ListItemShapes = SettingsTileDefaults.shapes(),
  elevation: ListItemElevation = SettingsTileDefaults.elevation(),
  semanticProperties: (SemanticsPropertyReceiver.() -> Unit) = {},
  onClick: () -> Unit,
) {
  SettingsTileScaffold(
    modifier =
      Modifier
        .toggleable(
          enabled = enabled,
          value = state,
          role = Role.RadioButton,
          onValueChange = { onClick() },
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
    RadioButton(
      modifier = Modifier.clearAndSetSemantics { },
      enabled = enabled,
      selected = state,
      onClick = onClick,
      colors = checkboxColors,
    )
  }
}
