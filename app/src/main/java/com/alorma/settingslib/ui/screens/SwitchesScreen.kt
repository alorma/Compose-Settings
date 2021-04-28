package com.alorma.settingslib.ui.screens

import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.SortByAlpha
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.navigation.NavHostController
import com.alorma.settings.composables.SettingsSwitch
import com.alorma.settings.composables.demo.AppScaffold
import com.alorma.settingslib.extensions.showSnackbar
import kotlinx.coroutines.launch
import kotlin.random.Random

@Composable
fun SwitchesScreen(navController: NavHostController) {
    val coroutineScope = rememberCoroutineScope()

    val scaffoldState = rememberScaffoldState()

    var switch1 by remember { mutableStateOf(Random.nextBoolean()) }
    var switch2 by remember { mutableStateOf(Random.nextBoolean()) }
    var switch3 by remember { mutableStateOf(Random.nextBoolean()) }
    var switch4 by remember { mutableStateOf(Random.nextBoolean()) }

    AppScaffold(
        scaffoldState = scaffoldState,
        title = { Text(text = "Switches") },
        onBack = {
            navController.popBackStack()
        },
    ) {
        SettingsSwitch(
            title = { Text(text = "Menu 1") },
            subtitle = { Text(text = "Subtitle of menu 1") },
            icon = {
                Icon(imageVector = Icons.Default.SortByAlpha,
                    contentDescription = "Menu 1")
            },
            checked = switch1,
        ) { changed ->
            coroutineScope.launch {
                switch1 = changed
                scaffoldState.showSnackbar(message = "Switch changed to:  $changed")
            }
        }
        Divider()
        SettingsSwitch(
            title = { Text(text = "Menu 2") },
            icon = {
                Icon(imageVector = Icons.Default.SortByAlpha,
                    contentDescription = "Menu 2")
            },
            checked = switch2,
        ) { changed ->
            coroutineScope.launch {
                switch2 = changed
                scaffoldState.showSnackbar(message = "Switch changed to:  $changed")
            }
        }
        Divider()
        SettingsSwitch(
            title = { Text(text = "Menu 3") },
            icon = {
                Icon(imageVector = Icons.Default.SortByAlpha,
                    contentDescription = "Menu 1")
            },
            checked = switch3,
        ) { changed ->
            coroutineScope.launch {
                switch3 = changed
                scaffoldState.showSnackbar(message = "Switch changed to:  $changed")
            }
        }
        SettingsSwitch(
            title = { Text(text = "Menu 4") },
            checked = switch4,
        ) { changed ->
            coroutineScope.launch {
                switch4 = changed
                scaffoldState.showSnackbar(message = "Switch changed to:  $changed")
            }
        }
    }
}