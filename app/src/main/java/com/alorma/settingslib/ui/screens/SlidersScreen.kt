package com.alorma.settingslib.ui.screens

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
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import com.alorma.settings.composables.SettingsSlider
import com.alorma.settingslib.demo.AppScaffold

@Composable
fun SlidersScreen(navController: NavHostController) {

    val scaffoldState = rememberScaffoldState()

    var brightnessSliderPosition by remember { mutableStateOf(0f) }
    var volumeSliderPosition by remember { mutableStateOf(0f) }
    var customColorsSliderPosition by remember { mutableStateOf(0f) }

    AppScaffold(
        scaffoldState = scaffoldState,
        title = { Text(text = "Sliders") },
        onBack = {
            navController.popBackStack()
        }
    ) {
        SettingsSlider(
            icon = {
                when {
                    brightnessSliderPosition < 0.1 -> {
                        Icon(
                            imageVector = Icons.Default.BrightnessLow,
                            contentDescription = "Brightness Low"
                        )
                    }
                    brightnessSliderPosition in 0.1f..0.8f -> {
                        Icon(
                            imageVector = Icons.Default.BrightnessMedium,
                            contentDescription = "Brightness Medium"
                        )
                    }
                    else -> {
                        Icon(
                            imageVector = Icons.Default.BrightnessHigh,
                            contentDescription = "Brightness High"
                        )
                    }
                }
            },
            title = { Text(text = "Brightness") },
            value = brightnessSliderPosition,
            onValueChange = { brightnessSliderPosition = it }
        )
        SettingsSlider(
            icon = {
                when {
                    volumeSliderPosition < 0.1 -> {
                        Icon(
                            imageVector = Icons.Default.VolumeMute,
                            contentDescription = "Volume Mute"
                        )
                    }
                    volumeSliderPosition in 0.1f..0.8f -> {
                        Icon(
                            imageVector = Icons.Default.VolumeDown,
                            contentDescription = "Volume Down"
                        )
                    }
                    else -> {
                        Icon(imageVector = Icons.Default.VolumeUp, contentDescription = "Volume Up")
                    }
                }
            },
            title = { Text(text = "Volume") },
            value = volumeSliderPosition,
            onValueChange = { volumeSliderPosition = it },
            steps = 3,
            colors = SliderDefaults.colors(
                activeTickColor = Color.Transparent,
                disabledActiveTickColor = Color.Transparent,
                disabledInactiveTickColor = Color.Transparent,
                inactiveTickColor = Color.Transparent
            )
        )
        SettingsSlider(
            icon = {
                Icon(imageVector = Icons.Default.Colorize, contentDescription = "Custom colors")
            },
            title = { Text(text = "Custom colors") },
            value = customColorsSliderPosition,
            onValueChange = { customColorsSliderPosition = it },
            steps = 4,
            colors = SliderDefaults.colors(
                thumbColor = Color.Green,
                activeTrackColor = Color.Blue,
                inactiveTrackColor = Color.Yellow,
                activeTickColor = Color.Red
            )
        )
    }
}
