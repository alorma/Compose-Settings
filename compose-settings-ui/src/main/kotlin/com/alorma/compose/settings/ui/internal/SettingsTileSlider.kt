package com.alorma.compose.settings.ui.internal

import androidx.annotation.IntRange
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Slider
import androidx.compose.material.SliderColors
import androidx.compose.material.SliderDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
internal fun <T: Number> RowScope.SettingsTileSlider(
    title: @Composable () -> Unit,
    value: T,
    onValueChange: (T) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    valueRange: ClosedFloatingPointRange<Float> = 0f..1f,
    @IntRange(from = 0) steps: Int = 0,
    onValueChangeFinished: (() -> Unit)? = null,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    colors: SliderColors = SliderDefaults.colors()
) {
    SettingsTileTexts(title = title, subtitle = {
        Slider(
            value = value.toFloat(),
            onValueChange = { value ->
                @Suppress("UNCHECKED_CAST")
                onValueChange(value as T)
            },
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
    })
}
