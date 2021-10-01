package com.alorma.settings.composables

import androidx.annotation.IntRange
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.SliderColors
import androidx.compose.material.SliderDefaults
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.alorma.settings.composables.internal.SettingsTileIcon
import com.alorma.settings.composables.internal.SettingsTileSlider
import com.alorma.settings.storage.SettingValueState
import com.alorma.settings.storage.getValue
import com.alorma.settings.storage.rememberFloatSettingState
import com.alorma.settings.storage.setValue

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
      SettingsTileIcon(icon = icon)
      SettingsTileSlider(
        title,
        settingValue,
        { value ->
          settingValue = value
          onValueChange(settingValue)
        },
        sliderModifier,
        enabled,
        valueRange,
        steps,
        onValueChangeFinished,
        interactionSource,
        colors
      )
    }
  }
}
