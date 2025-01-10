package com.alorma.compose.settings.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SegmentedButton
import androidx.compose.material3.SegmentedButtonColors
import androidx.compose.material3.SegmentedButtonDefaults
import androidx.compose.material3.SingleChoiceSegmentedButtonRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.compositeOver
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.alorma.compose.settings.ui.base.internal.LocalSettingsGroupEnabled
import com.alorma.compose.settings.ui.base.internal.SettingsTileColors
import com.alorma.compose.settings.ui.base.internal.SettingsTileDefaults
import com.alorma.compose.settings.ui.base.internal.SettingsTileScaffold

@Composable
@Suppress("LongParameterList")
fun <T> SettingsSegmented(
  title: @Composable () -> Unit,
  items: List<T>,
  selectedItem: T?,
  onItemSelected: (T) -> Unit,
  itemTitleMap: (T) -> CharSequence,
  modifier: Modifier = Modifier,
  enabled: Boolean = LocalSettingsGroupEnabled.current,
  colors: SettingsTileColors = SettingsTileDefaults.colors(),
  buttonSpace: Dp = SegmentedButtonDefaults.BorderWidth,
  buttonShape: @Composable (Int) -> Shape = { index ->
    SegmentedButtonDefaults.itemShape(
      index = index,
      count = items.size,
    )
  },
  buttonColors: SegmentedButtonColors =
    SegmentedButtonDefaults.colors(
      activeContainerColor =
        colors.actionColor(enabled).copy(alpha = 0.4f)
          .compositeOver(MaterialTheme.colorScheme.surfaceContainerLowest),
      inactiveContainerColor = colors.containerColor,
      activeContentColor = colors.subtitleColor(enabled),
      inactiveContentColor = colors.subtitleColor(enabled),
      activeBorderColor = colors.subtitleColor,
      inactiveBorderColor = colors.subtitleColor,
      disabledActiveContainerColor =
        colors.actionColor(enabled).copy(alpha = 0.12f)
          .compositeOver(MaterialTheme.colorScheme.surfaceContainerLowest),
      disabledInactiveContainerColor =
        MaterialTheme.colorScheme.surface
          .copy(alpha = SettingsTileDefaults.DisabledAlpha)
          .compositeOver(colors.containerColor),
      disabledActiveContentColor = colors.subtitleColor(enabled),
      disabledInactiveContentColor = colors.subtitleColor(enabled),
      disabledActiveBorderColor = colors.subtitleColor(enabled),
      disabledInactiveBorderColor = colors.subtitleColor(enabled),
    ),
  buttonIcon: @Composable (Boolean) -> Unit = { selected -> SegmentedButtonDefaults.Icon(selected) },
  subtitle: @Composable (() -> Unit)? = null,
  icon: @Composable (() -> Unit)? = null,
  tonalElevation: Dp = SettingsTileDefaults.Elevation,
  shadowElevation: Dp = SettingsTileDefaults.Elevation,
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
        SingleChoiceSegmentedButtonRow(
          modifier = Modifier.fillMaxWidth(),
          space = buttonSpace,
        ) {
          items.forEachIndexed { index, item ->
            val shape = buttonShape(index)

            SegmentedButton(
              enabled = enabled,
              shape = shape,
              label = {
                when (val text = itemTitleMap(item)) {
                  is String -> Text(text = text)
                  is AnnotatedString -> Text(text = text)
                  else -> Text(text = text.toString())
                }
              },
              icon = { buttonIcon(item == selectedItem) },
              selected = item == selectedItem,
              onClick = { onItemSelected(item) },
              colors = buttonColors,
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
