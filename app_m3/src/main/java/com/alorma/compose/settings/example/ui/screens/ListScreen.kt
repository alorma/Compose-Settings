package com.alorma.compose.settings.example.ui.screens

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.alorma.compose.settings.example.demo.AppScaffold
import com.alorma.compose.settings.storage.base.rememberBooleanSettingState
import com.alorma.compose.settings.storage.datastore.rememberPreferenceDataStoreIntSettingState
import com.alorma.compose.settings.storage.preferences.rememberPreferenceIntSetSettingState
import com.alorma.compose.settings.storage.preferences.rememberPreferenceIntSettingState
import com.alorma.compose.settings.ui.SettingsList
import com.alorma.compose.settings.ui.SettingsListDropdown
import com.alorma.compose.settings.ui.SettingsListMultiSelect

@Composable
fun ListScreen(
  navController: NavController = rememberNavController(),
) {

  val enabledState = rememberBooleanSettingState(true)

  AppScaffold(
    enabledState = enabledState,
    navController = navController,
    title = { Text(text = "List") },
  ) {
    val singleChoiceState = rememberPreferenceDataStoreIntSettingState(key = "list_pref_1")
    SettingsList(
      enabled = enabledState.value,
      state = singleChoiceState,
      title = { Text(text = "Single choice") },
      subtitle = { Text(text = "Select a fruit") },
      items = listOf("Banana", "Kiwi", "Pineapple"),
      action = { enabled ->
        IconButton(
          enabled = enabled,
          onClick = { singleChoiceState.reset() }
        ) {
          Icon(
            imageVector = Icons.Default.Clear,
            contentDescription = "Clear",
          )
        }
      },
    )
    Divider()
    val multiChoiceState =
      rememberPreferenceIntSetSettingState(key = "list_pref_2", defaultValue = setOf(1, 2))
    SettingsListMultiSelect(
      enabled = enabledState.value,
      state = multiChoiceState,
      title = { Text(text = "Multi choice") },
      subtitle = { Text(text = "Select multiple fruits") },
      items = listOf("Banana", "Kiwi", "Pineapple"),
      action = { enabled ->
        IconButton(
          enabled = enabled,
          onClick = { multiChoiceState.reset() },

          ) {
          Icon(
            imageVector = Icons.Default.Clear,
            contentDescription = "Clear",
          )
        }
      },
      confirmButton = "Select"
    )
    Divider()
    val dropdownChoiceState =
      rememberPreferenceIntSettingState(key = "dropdown_list_pref_1", defaultValue = 0)
    SettingsListDropdown(
      enabled = enabledState.value,
      state = dropdownChoiceState,
      title = { Text(text = "Dropdown choice") },
      subtitle = { Text(text = "Select a single fruit") },
      items = listOf("Banana", "Kiwi", "Pineapple"),
    )
  }
}
