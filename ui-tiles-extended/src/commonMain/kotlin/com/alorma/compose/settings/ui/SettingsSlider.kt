package com.alorma.compose.settings.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ListItemColors
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderColors
import androidx.compose.material3.SliderDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.alorma.compose.settings.ui.base.internal.LocalSettingsGroupEnabled
import com.alorma.compose.settings.ui.base.internal.LocalSettingsTileColors
import com.alorma.compose.settings.ui.base.internal.SettingsTileColors
import com.alorma.compose.settings.ui.base.internal.SettingsTileDefaults
import com.alorma.compose.settings.ui.base.internal.SettingsTileScaffold

@Composable
@Suppress("LongParameterList")
fun SettingsSlider(
  title: @Composable () -> Unit,
  value: Float,
  onValueChange: (Float) -> Unit,
  modifier: Modifier = Modifier,
  subtitle: @Composable (() -> Unit)? = null,
  icon: @Composable (() -> Unit)? = null,
  enabled: Boolean = LocalSettingsGroupEnabled.current,
  valueRange: ClosedFloatingPointRange<Float> = 0f..1f,
  steps: Int = 0,
  onValueChangeFinished: (() -> Unit)? = null,
  sliderColors: SliderColors = SliderDefaults.colors(),
  colors: SettingsTileColors = SettingsTileDefaults.colors(),
  tonalElevation: Dp = SettingsTileDefaults.Elevation,
  shadowElevation: Dp = SettingsTileDefaults.Elevation,
) {
  SettingsTileScaffold(
    modifier = modifier,
    enabled = enabled,
    title = title,
    subtitle = {
      Column {
        subtitle?.invoke()
        Slider(
          value = value,
          onValueChange = { value -> onValueChange(value) },
          modifier = Modifier.padding(end = 16.dp),
          enabled = enabled,
          valueRange = valueRange,
          steps = steps,
          onValueChangeFinished = onValueChangeFinished,
          colors = sliderColors,
        )
      }
    },
    icon = icon,
    colors = colors,
    tonalElevation = tonalElevation,
    shadowElevation = shadowElevation,
  )
}
