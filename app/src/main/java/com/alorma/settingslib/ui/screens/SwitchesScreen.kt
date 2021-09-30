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
import com.alorma.settings.storage.preferences.rememberPreferenceBooleanStorage
import com.alorma.settings.storage.rememberBooleanStorage
import com.alorma.settingslib.demo.AppScaffold
import com.alorma.settingslib.extensions.showSnackbar
import kotlinx.coroutines.launch
import kotlin.random.Random

@Composable
fun SwitchesScreen(navController: NavHostController) {
  val coroutineScope = rememberCoroutineScope()

  val scaffoldState = rememberScaffoldState()

  AppScaffold(
    scaffoldState = scaffoldState,
    title = { Text(text = "Switches") },
    onBack = { navController.popBackStack() },
  ) {
    val memoryStorage = rememberBooleanStorage(defaultValue = false)
    SettingsSwitch(
      storage = memoryStorage,
      icon = {
        Icon(
          imageVector = Icons.Default.SortByAlpha,
          contentDescription = "Memory switch 1"
        )
      },
      title = { Text(text = "Memory") },
    )
    val preferenceStorage = rememberPreferenceBooleanStorage(
      key = "switch_2",
      defaultValue = false,
    )
    SettingsSwitch(
      storage = preferenceStorage,
      icon = {
        Icon(
          imageVector = Icons.Default.SortByAlpha,
          contentDescription = "Preferences switch 1"
        )
      },
      title = { Text(text = "Preferences") },
    )
    Divider()
    var switch1 by remember { mutableStateOf(Random.nextBoolean()) }
    SettingsSwitch(
      title = { Text(text = "Hoisting state") },
      icon = {
        Icon(
          imageVector = Icons.Default.SortByAlpha,
          contentDescription = "Menu 1"
        )
      },
      checked = switch1,
    ) { changed ->
      coroutineScope.launch {
        switch1 = changed
        scaffoldState.showSnackbar(message = "Switch changed to:  $changed")
      }
    }
  }
}