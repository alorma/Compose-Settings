package com.alorma.compose.settings.ui.expressive

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxColors
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.ListItemColors
import androidx.compose.material3.ListItemElevation
import androidx.compose.material3.ListItemShapes
import androidx.compose.material3.SegmentedListItem
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.SemanticsPropertyReceiver
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.ui.semantics.semantics
import com.alorma.compose.settings.ui.core.LocalSettingsGroupEnabled

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun SettingsCheckbox(
  state: Boolean,
  title: @Composable () -> Unit,
  modifier: Modifier = Modifier,
  enabled: Boolean = LocalSettingsGroupEnabled.current,
  icon: @Composable (() -> Unit)? = null,
  subtitle: @Composable (() -> Unit)? = null,
  colors: ListItemColors = SettingsTileDefaults.colors(),
  checkboxColors: CheckboxColors = CheckboxDefaults.colors(),
  shapes: ListItemShapes = SettingsTileDefaults.shapes(),
  elevation: ListItemElevation = SettingsTileDefaults.elevation(),
  semanticProperties: (SemanticsPropertyReceiver.() -> Unit) = {},
  onCheckedChange: (Boolean) -> Unit,
) {
  val update: (Boolean) -> Unit = { boolean -> onCheckedChange(boolean) }

  SegmentedListItem(
    modifier = Modifier
      .fillMaxWidth()
      .semantics(properties = semanticProperties)
      .then(modifier),
    checked = true,
    onCheckedChange = { update(!state) },
    shapes = shapes,
    enabled = enabled,
    content = title,
    leadingContent = icon,
    supportingContent = subtitle,
    colors = colors,
    elevation = elevation,
    trailingContent = {
      Checkbox(
        modifier = Modifier.clearAndSetSemantics { },
        enabled = enabled,
        checked = state,
        onCheckedChange = update,
        colors = checkboxColors,
      )
    },
  )
}
