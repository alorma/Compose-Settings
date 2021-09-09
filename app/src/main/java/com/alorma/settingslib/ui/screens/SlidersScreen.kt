package com.alorma.settingslib.ui.screens

import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.VolumeDown
import androidx.compose.material.icons.filled.VolumeMute
import androidx.compose.material.icons.filled.VolumeUp
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.NavHostController
import com.alorma.settings.composables.SettingsSlider
import com.alorma.settingslib.demo.AppScaffold

@Composable
fun SlidersScreen(navController: NavHostController) {

    val scaffoldState = rememberScaffoldState()

    var sliderPosition by remember { mutableStateOf(0f) }

    AppScaffold(
        scaffoldState = scaffoldState,
        title = { Text(text = "Sliders") },
        onBack = {
            navController.popBackStack()
        },
    ) {
        SettingsSlider(
            icon = {
                when {
                    sliderPosition < 0.1 -> {
                        Icon(
                            imageVector = Icons.Default.VolumeMute,
                            contentDescription = "Volume Mute"
                        )
                    }
                    sliderPosition in 0.1f..0.8f -> {
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
            value = sliderPosition,
            onValueChange = { sliderPosition = it }
        )
    }
}
