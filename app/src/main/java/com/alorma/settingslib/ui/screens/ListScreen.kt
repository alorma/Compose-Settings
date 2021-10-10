package com.alorma.settingslib.ui.screens

import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.alorma.settings.composables.SettingsList
import com.alorma.settings.composables.SettingsListMultiSelect
import com.alorma.settings.storage.preferences.rememberPreferenceIntSettingState
import com.alorma.settings.storage.preferences.rememberPreferenceStringSettingState
import com.alorma.settingslib.demo.AppScaffold

@Composable
fun ListScreen(
  navController: NavController = rememberNavController(),
) {
  AppScaffold(
    title = { Text(text = "List") },
    onBack = { navController.popBackStack() },
  ) {
    val singleChoiceState = rememberPreferenceIntSettingState(key = "list_pref_1")
    SettingsList(
      state = singleChoiceState,
      title = { Text(text = "Single choice") },
      useSelectedValueAsSubtitle = true,
      items = listOf("Banana", "Kiwi", "Pineapple"),
      action = {
        IconButton(onClick = { singleChoiceState.reset() }) {
          Icon(
            imageVector = Icons.Default.Clear,
            contentDescription = "Clear",
          )
        }
      },
    )
    Divider()
    val multiChoiceState = rememberPreferenceStringSettingState(key = "list_pref_2")
    SettingsListMultiSelect(
      state = multiChoiceState,
      title = { Text(text = "Multi choice") },
      items = listOf("Banana", "Kiwi", "Pineapple"),
      action = {
        IconButton(onClick = { multiChoiceState.reset() }) {
          Icon(
            imageVector = Icons.Default.Clear,
            contentDescription = "Clear",
          )
        }
      },
    )
  }
}
