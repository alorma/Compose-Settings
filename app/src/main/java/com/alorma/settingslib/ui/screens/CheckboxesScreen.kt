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
import com.alorma.settings.composables.SettingsCheckbox
import com.alorma.settings.storage.ValueStorage
import com.alorma.settings.storage.preferences.BooleanPreferenceStorage
import com.alorma.settings.storage.preferences.rememberPreferenceBooleanStorage
import com.alorma.settings.storage.rememberBooleanStorage
import com.alorma.settingslib.demo.AppScaffold
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun CheckboxesScreen(navController: NavHostController) {
  val coroutineScope = rememberCoroutineScope()

  val scaffoldState = rememberScaffoldState()

  AppScaffold(
    scaffoldState = scaffoldState,
    title = { Text(text = "Checkboxes") },
    onBack = { navController.popBackStack() },
  ) {
    val memoryStorage = rememberBooleanStorage(defaultValue = false)
    SettingsCheckbox(
      storage = memoryStorage,
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
          valueStorage = memoryStorage
        )
      },
    )
    val preferenceStorage: BooleanPreferenceStorage = rememberPreferenceBooleanStorage(
      key = "switch_2",
      defaultValue = false,
    )
    SettingsCheckbox(
      storage = preferenceStorage,
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
          valueStorage = preferenceStorage,
        )
      },
    )
  }
}

private fun ScaffoldState.showChange(
  coroutineScope: CoroutineScope,
  key: String,
  valueStorage: ValueStorage<Boolean>
) {
  coroutineScope.launch {
    snackbarHostState.currentSnackbarData?.dismiss()
    snackbarHostState.showSnackbar(message = "[$key]:  ${valueStorage.value}")
  }
}