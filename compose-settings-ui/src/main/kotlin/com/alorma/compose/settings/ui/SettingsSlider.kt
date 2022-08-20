package com.alorma.compose.settings.ui

import androidx.annotation.IntRange
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.SliderColors
import androidx.compose.material.SliderDefaults
import androidx.compose.material.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.alorma.compose.settings.storage.base.*
import com.alorma.compose.settings.ui.internal.SettingsTileIcon
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

@Composable
fun <TMarker : SettingsSnapshotMarker> AsyncSettingsItemScope<TMarker, *, *>.SettingsSlider(
    modifier: Modifier = Modifier,
    key: SettingsSnapshot.Key<TMarker, Float>,
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
    val storageValue by getValueFlow(key).collectAsState(initial = key.defaultValue)

    // When an user changes the value of slider, it's expected that the change will be observed instantly.
    // As 'update' is async operation, storageValue won't be updated instantly.
    // visualValue fixes that issue. It mirrors storageValue and allows the change to be observed instantly.
    var visualValue by remember(storageValue) {
        mutableStateOf(storageValue)
    }

    val updateHandler by remember(key) {
        derivedStateOf { updateHandlerFor(key) }
    }

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
                visualValue,
                { value ->
                    visualValue = value
                    updateHandler.update(value)
                    onValueChange(storageValue)
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
