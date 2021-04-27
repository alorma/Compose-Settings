package com.alorma.settingslib.ui.screens

import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.SortByAlpha
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavHostController
import com.alorma.settings.SettingsList
import com.alorma.settings.composables.SettingsCheckbox
import com.alorma.settingslib.extensions.showSnackbar
import kotlinx.coroutines.launch
import kotlin.random.Random
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.alorma.settingslib.ui.theme.SettingsLibTheme

@Composable
fun CheckboxesSettings(navController: NavHostController) {
    val coroutineScope = rememberCoroutineScope()

    val scaffoldState = rememberScaffoldState()

    var checked1 by remember { mutableStateOf(Random.nextBoolean()) }
    var checked2 by remember { mutableStateOf(Random.nextBoolean()) }
    var checked3 by remember { mutableStateOf(Random.nextBoolean()) }
    var checked4 by remember { mutableStateOf(Random.nextBoolean()) }

    SettingsList(
        scaffoldState = scaffoldState,
        title = { Text(text = "Checkboxes") },
        onBack = {
            navController.popBackStack()
        },
    ) {
        SettingsCheckbox(
            title = { Text(text = "Menu 1") },
            subtitle = { Text(text = "Subtitle of menu 1") },
            icon = {
                Icon(imageVector = Icons.Default.SortByAlpha,
                    contentDescription = "Menu 1")
            },
            checked = checked1,
        ) { changed ->
            coroutineScope.launch {
                checked1 = changed
                scaffoldState.showSnackbar(message = "Checkbox changed to:  $changed")
            }
        }
        Divider()
        SettingsCheckbox(
            title = { Text(text = "Menu 2") },
            subtitle = { Text(text = "Without icon") },
            checked = checked2,
        ) { changed ->
            coroutineScope.launch {
                checked2 = changed
                scaffoldState.showSnackbar(message = "Checkbox changed to:  $changed")
            }
        }
        Divider()
        SettingsCheckbox(
            title = { Text(text = "Menu 3") },
            icon = {
                Icon(imageVector = Icons.Default.SortByAlpha,
                    contentDescription = "Menu 1")
            },
            checked = checked3,
        ) { changed ->
            coroutineScope.launch {
                checked3 = changed
                scaffoldState.showSnackbar(message = "Checkbox changed to:  $changed")
            }
        }
        SettingsCheckbox(
            title = { Text(text = "Menu 4") },
            checked = checked4,
        ) { changed ->
            coroutineScope.launch {
                checked4 = changed
                scaffoldState.showSnackbar(message = "Checkbox changed to:  $changed")
            }
        }
    }
}