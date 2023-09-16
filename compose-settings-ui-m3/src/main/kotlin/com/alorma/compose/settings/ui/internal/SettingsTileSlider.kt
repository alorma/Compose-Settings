package com.alorma.compose.settings.ui.internal

import androidx.annotation.IntRange
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderColors
import androidx.compose.material3.SliderDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
internal fun SettingsTileSlider(
  title: @Composable () -> Unit,
  value: Float,
  onValueChange: (Float) -> Unit,
  modifier: Modifier = Modifier,
  icon: (@Composable () -> Unit)? = null,
  enabled: Boolean = true,
  valueRange: ClosedFloatingPointRange<Float> = 0f..1f,
  @IntRange(from = 0) steps: Int = 0,
  onValueChangeFinished: (() -> Unit)? = null,
  interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
  colors: SliderColors = SliderDefaults.colors()
) {

  SettingsTileScaffold(
    enabled = enabled,
    title = title,
    subtitle = {
      Column(
        modifier = Modifier
          .padding(top = 8.dp, end = 16.dp)
          .then(modifier)
      ) {
        Slider(
          value = value,
          onValueChange = onValueChange,
          modifier = Modifier
            .padding(end = 16.dp)
            .then(modifier),
          enabled = enabled,
          valueRange = valueRange,
          steps = steps,
          onValueChangeFinished = onValueChangeFinished,
          interactionSource = interactionSource,
          colors = colors
        )
      }
    },
    icon = icon,
  )
}
