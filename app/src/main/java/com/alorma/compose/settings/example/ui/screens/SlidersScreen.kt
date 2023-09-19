package com.alorma.compose.settings.example.ui.screens

import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.SliderDefaults
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BrightnessHigh
import androidx.compose.material.icons.filled.BrightnessLow
import androidx.compose.material.icons.filled.BrightnessMedium
import androidx.compose.material.icons.filled.Colorize
import androidx.compose.material.icons.filled.VolumeDown
import androidx.compose.material.icons.filled.VolumeMute
import androidx.compose.material.icons.filled.VolumeUp
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import com.alorma.compose.settings.example.demo.AppScaffold
import com.alorma.compose.settings.storage.base.rememberBooleanSettingState
import com.alorma.compose.settings.storage.preferences.rememberPreferenceFloatSettingState
import com.alorma.compose.settings.storage.preferences.rememberPreferenceIntSettingState
import com.alorma.compose.settings.ui.SettingsSlider

@Composable
fun SlidersScreen(navController: NavHostController) {
    val settingBrightness = rememberPreferenceFloatSettingState(key = "brightness", defaultValue = 0f)
    val settingVolume = rememberPreferenceFloatSettingState(key = "volume", defaultValue = 0f)
    val settingColors = rememberPreferenceIntSettingState(key = "colors", defaultValue = 0)

    val enabledState = rememberBooleanSettingState(true)

    AppScaffold(
        navController = navController,
        enabledState = enabledState,
        title = { Text(text = "Sliders") },
    ) {
        SettingsSlider(
            enabled = enabledState.value,
            state = settingBrightness,
            icon = {
                when {
                    settingBrightness.value < 0.1 -> {
                        Icon(
                            imageVector = Icons.Default.BrightnessLow,
                            contentDescription = "Brightness Low",
                        )
                    }
                    settingBrightness.value in 0.1f..0.8f -> {
                        Icon(
                            imageVector = Icons.Default.BrightnessMedium,
                            contentDescription = "Brightness Medium",
                        )
                    }
                    else -> {
                        Icon(
                            imageVector = Icons.Default.BrightnessHigh,
                            contentDescription = "Brightness High",
                        )
                    }
                }
            },
            title = { Text(text = "Brightness") },
        )
        Divider()
        SettingsSlider(
            enabled = enabledState.value,
            state = settingVolume,
            icon = {
                when {
                    settingVolume.value < 0.1 -> {
                        Icon(
                            imageVector = Icons.Default.VolumeMute,
                            contentDescription = "Volume Mute",
                        )
                    }
                    settingVolume.value in 0.1f..0.8f -> {
                        Icon(
                            imageVector = Icons.Default.VolumeDown,
                            contentDescription = "Volume Down",
                        )
                    }
                    else -> {
                        Icon(imageVector = Icons.Default.VolumeUp, contentDescription = "Volume Up")
                    }
                }
            },
            title = { Text(text = "Volume") },
            steps = 3,
            colors = SliderDefaults.colors(
                activeTickColor = Color.Transparent,
                disabledActiveTickColor = Color.Transparent,
                disabledInactiveTickColor = Color.Transparent,
                inactiveTickColor = Color.Transparent,
            ),
        )
        Divider()
        SettingsSlider(
            enabled = enabledState.value,
            state = settingColors,
            icon = {
                Icon(imageVector = Icons.Default.Colorize, contentDescription = "Custom colors")
            },
            title = { Text(text = "Custom colors") },
            steps = 4,
            colors = SliderDefaults.colors(
                thumbColor = Color.Green,
                activeTrackColor = Color.Blue,
                inactiveTrackColor = Color.Yellow,
                activeTickColor = Color.Red,
            ),
        )
    }
}
