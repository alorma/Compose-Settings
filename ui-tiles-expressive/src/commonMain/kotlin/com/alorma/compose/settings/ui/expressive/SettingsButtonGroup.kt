package com.alorma.compose.settings.ui.expressive

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ButtonGroup
import androidx.compose.material3.ButtonGroupMenuState
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.SegmentedButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.alorma.compose.settings.ui.base.internal.LocalSettingsGroupEnabled
import com.alorma.compose.settings.ui.base.internal.SettingsTileColors
import com.alorma.compose.settings.ui.base.internal.SettingsTileDefaults
import com.alorma.compose.settings.ui.base.internal.SettingsTileScaffold

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun <T> SettingsButtonGroup(
  title: @Composable () -> Unit,
  items: List<T>,
  selectedItem: T?,
  onItemSelected: (T) -> Unit,
  itemTitleMap: (T) -> CharSequence,
  modifier: Modifier = Modifier,
  enabled: Boolean = LocalSettingsGroupEnabled.current,
  colors: SettingsTileColors = SettingsTileDefaults.colors(),
  buttonIcon: @Composable (Boolean) -> Unit = { selected -> SegmentedButtonDefaults.Icon(selected) },
  subtitle: @Composable (() -> Unit)? = null,
  icon: @Composable (() -> Unit)? = null,
  tonalElevation: Dp = SettingsTileDefaults.Elevation,
  shadowElevation: Dp = SettingsTileDefaults.Elevation,
  overflowIndicator: @Composable (ButtonGroupMenuState) -> Unit= {},
) {
  SettingsTileScaffold(
    modifier = modifier,
    enabled = enabled,
    title = title,
    subtitle = {
      Column(
        verticalArrangement = Arrangement.spacedBy(4.dp),
      ) {
        subtitle?.invoke()

        ButtonGroup(
          modifier = Modifier.fillMaxWidth(),
          overflowIndicator = overflowIndicator,
        ) {
          items.forEachIndexed { _, item ->
            toggleableItem(
              checked = item == selectedItem,
              label = itemTitleMap(item).toString(),
              icon = { buttonIcon(item == selectedItem) },
              onCheckedChange = { onItemSelected(item) },
              enabled = enabled,
              weight = 1f,
            )
          }
        }
      }
    },
    icon = icon,
    colors = colors,
    tonalElevation = tonalElevation,
    shadowElevation = shadowElevation,
  )
}
