package com.alorma.settingslib.ui.screens

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.SortByAlpha
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.alorma.settings.composables.SettingsList
import com.alorma.settings.storage.preferences.rememberPreferenceIntSettingState
import com.alorma.settingslib.demo.AppScaffold

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ListScreen(
  navController: NavController = rememberNavController(),
) {
  AppScaffold(
    title = { Text(text = "List") },
    onBack = { navController.popBackStack() },
  ) {
    val state = rememberPreferenceIntSettingState(key = "list_pref_1")
    SettingsList(
      state = state,
      title = { Text(text = "Menu 1") },
      items = listOf("Banana", "Kiwi", "Pineapple"),
      subtitle = { Text(text = "Subtitle of menu 1") },
      icon = {
        Icon(
          imageVector = Icons.Default.SortByAlpha,
          contentDescription = "Menu 1"
        )
      }
    )
  }
}
