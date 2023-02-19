package com.alorma.compose.settings.ui

import androidx.annotation.IntRange
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.SliderColors
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.alorma.compose.settings.storage.base.SettingValueState
import com.alorma.compose.settings.storage.base.getValue
import com.alorma.compose.settings.storage.base.rememberFloatSettingState
import com.alorma.compose.settings.storage.base.setValue
import com.alorma.compose.settings.ui.internal.SettingsTileSlider

@Composable
fun SettingsSlider(
  modifier: Modifier = Modifier,
  state: SettingValueState<Float> = rememberFloatSettingState(),
  icon: @Composable (() -> Unit)? = null,
  title: @Composable () -> Unit,
  onValueChange: (Float) -> Unit = {},
  sliderModifier: Modifier = Modifier,
  enabled: Boolean = true,
  valueRange: ClosedFloatingPointRange<Float> = 0f..1f,
  @IntRange(from = 0) steps: Int = 0,
  onValueChangeFinished: (() -> Unit)? = null,
  interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
  colors: SliderColors = SliderDefaults.colors()
) {
  var settingValue by state
  Surface {
    Row(
      modifier = modifier
        .height(72.dp)
        .fillMaxWidth(),
      verticalAlignment = Alignment.CenterVertically,
    ) {
      SettingsTileSlider(
        title = title,
        value = settingValue,
        onValueChange = { value ->
          settingValue = value
          onValueChange(settingValue)
        },
        modifier = sliderModifier,
        icon = icon,
        enabled = enabled,
        valueRange = valueRange,
        steps = steps,
        onValueChangeFinished = onValueChangeFinished,
        interactionSource = interactionSource,
        colors = colors
      )
    }
  }
}
