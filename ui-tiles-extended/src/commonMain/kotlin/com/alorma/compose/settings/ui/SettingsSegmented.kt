package com.alorma.compose.settings.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ListItemColors
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.SegmentedButton
import androidx.compose.material3.SegmentedButtonColors
import androidx.compose.material3.SegmentedButtonDefaults
import androidx.compose.material3.SingleChoiceSegmentedButtonRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.unit.Dp
import com.alorma.compose.settings.ui.base.internal.LocalSettingsGroupEnabled
import com.alorma.compose.settings.ui.base.internal.SettingsTileScaffold

@Composable
fun <T> SettingsSegmented(
  title: @Composable () -> Unit,
  items: List<T>,
  selectedItem: T?,
  onItemSelected: (T) -> Unit,
  itemTitleMap: (T) -> CharSequence,
  modifier: Modifier = Modifier,
  buttonSpace: Dp = SegmentedButtonDefaults.BorderWidth,
  buttonShape: @Composable (Int) -> Shape = { index ->
    SegmentedButtonDefaults.itemShape(
      index = index,
      count = items.size,
    )
  },
  buttonColors: SegmentedButtonColors = SegmentedButtonDefaults.colors(),
  buttonIcon: @Composable (Boolean) -> Unit = { selected ->
    SegmentedButtonDefaults.Icon(selected)
  },
  subtitle: @Composable (() -> Unit)? = null,
  icon: @Composable (() -> Unit)? = null,
  enabled: Boolean = LocalSettingsGroupEnabled.current,
  colors: ListItemColors = ListItemDefaults.colors(),
  tonalElevation: Dp = ListItemDefaults.Elevation,
  shadowElevation: Dp = ListItemDefaults.Elevation,
) {
  SettingsTileScaffold(
    modifier = modifier,
    enabled = enabled,
    title = title,
    subtitle = {
      Column {
        subtitle?.invoke()
        SingleChoiceSegmentedButtonRow(
          modifier = Modifier.fillMaxWidth(),
          space = buttonSpace,
        ) {
          items.forEachIndexed { index, item ->
            val shape = buttonShape(index)

            SegmentedButton(
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