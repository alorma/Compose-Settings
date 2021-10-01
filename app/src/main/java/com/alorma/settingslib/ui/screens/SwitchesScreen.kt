package com.alorma.settingslib.ui.screens

import androidx.compose.material.Icon
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.SortByAlpha
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavHostController
import com.alorma.settings.composables.SettingsSwitch
import com.alorma.settings.storage.SettingValueState
import com.alorma.settings.storage.preferences.BooleanPreferenceSettingValueState
import com.alorma.settings.storage.preferences.rememberPreferenceBooleanState
import com.alorma.settings.storage.rememberBooleanSettingState
import com.alorma.settingslib.demo.AppScaffold
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun SwitchesScreen(navController: NavHostController) {
  val coroutineScope = rememberCoroutineScope()

  val scaffoldState = rememberScaffoldState()

  AppScaffold(
    scaffoldState = scaffoldState,
    title = { Text(text = "Switches") },
    onBack = { navController.popBackStack() },
  ) {
    val memoryStorage = rememberBooleanSettingState(defaultValue = false)
    SettingsSwitch(
      state = memoryStorage,
      icon = {
        Icon(
          imageVector = Icons.Default.SortByAlpha,
          contentDescription = "Memory switch 1"
        )
      },
      title = { Text(text = "Memory") },
      onCheckedChange = {
        scaffoldState.showChange(
          coroutineScope = coroutineScope,
          key = "Memory",
          state = memoryStorage
        )
      },
    )
    val preferenceStorage: BooleanPreferencestate = rememberPreferenceBooleanState(
      key = "switch_2",
      defaultValue = false,
    )
    SettingsSwitch(
      state = preferenceStorage,
      icon = {
        Icon(
          imageVector = Icons.Default.SortByAlpha,
          contentDescription = "Preferences switch 1"
        )
      },
      title = { Text(text = "Preferences") },
      onCheckedChange = {
        scaffoldState.showChange(
          coroutineScope = coroutineScope,
          key = "Preferences",
          state = preferenceStorage,
        )
      },
    )
  }
}

private fun ScaffoldState.showChange(
  coroutineScope: CoroutineScope,
  key: String,
  state: SettingValueState<Boolean>
) {
  coroutineScope.launch {
    snackbarHostState.currentSnackbarData?.dismiss()
    snackbarHostState.showSnackbar(message = "[$key]:  ${settingValueState.value}")
  }
}