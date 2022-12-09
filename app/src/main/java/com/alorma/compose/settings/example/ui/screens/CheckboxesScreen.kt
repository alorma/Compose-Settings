package com.alorma.compose.settings.example.ui.screens

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.SortByAlpha
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavHostController
import com.alorma.compose.settings.example.demo.AppScaffold
import com.alorma.compose.settings.example.serializer.SettingsSerializer
import com.alorma.compose.settings.storage.base.SettingValueState
import com.alorma.compose.settings.storage.base.rememberBooleanSettingState
import com.alorma.compose.settings.storage.datastore.proto.rememberProtoDataStoreSettingState
import com.alorma.compose.settings.storage.datastore.proto.rememberProtoDataStoreState
import com.alorma.compose.settings.storage.datastore.proto.rememberProtoDataStoreTransformSettingState
import com.alorma.compose.settings.storage.datastore.rememberDataStoreBooleanSettingState
import com.alorma.compose.settings.storage.preferences.rememberPreferenceBooleanSettingState
import com.alorma.compose.settings.ui.SettingsCheckbox
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun CheckboxesScreen(navController: NavHostController) {

  val coroutineScope = rememberCoroutineScope()
  val snackbarHostState = remember { SnackbarHostState() }

  AppScaffold(
    title = { Text(text = "Checkboxes") },
    snackbarHostState = snackbarHostState,
    onBack = { navController.popBackStack() },
  ) {
    val memoryStorage = rememberBooleanSettingState(defaultValue = false)
    SettingsCheckbox(
      state = memoryStorage,
      icon = {
        Icon(
          imageVector = Icons.Default.SortByAlpha,
          contentDescription = "Memory switch 1"
        )
      },
      title = { Text(text = "Memory") },
      onCheckedChange = {
        snackbarHostState.showChange(
          coroutineScope = coroutineScope,
          key = "Memory",
          state = memoryStorage
        )
      },
    )
    Divider()
    val preferenceStorage = rememberPreferenceBooleanSettingState(
      key = "switch_2",
      defaultValue = false,
    )
    SettingsCheckbox(
      state = preferenceStorage,
      icon = {
        Icon(
          imageVector = Icons.Default.SortByAlpha,
          contentDescription = "Preferences switch 1"
        )
      },
      title = { Text(text = "Preferences") },
      onCheckedChange = {
        snackbarHostState.showChange(
          coroutineScope = coroutineScope,
          key = "Preferences",
          state = preferenceStorage,
        )
      },
    )
    Divider()
    val preferenceDataStoreStorage = rememberDataStoreBooleanSettingState(
      key = "checkbox_dataStore_preference",
      defaultValue = false
    )
    SettingsCheckbox(
      state = preferenceDataStoreStorage,
      icon = {
        Icon(
          imageVector = Icons.Default.SortByAlpha,
          contentDescription = "Preferences switch 2"
        )
      },
      title = { Text(text = "PreferenceDataStore") },
      onCheckedChange = {
        snackbarHostState.showChange(
          coroutineScope = coroutineScope,
          key = "PreferenceDataStore",
          state = preferenceDataStoreStorage,
        )
      },
    )
    Divider()
    val dataStoreState = rememberProtoDataStoreState(
      serializer = SettingsSerializer
    )
    val protoDataStoreStorage = rememberProtoDataStoreTransformSettingState(
      protoDataStoreState = dataStoreState,
      encoder = {savedValue, newValue -> savedValue.toBuilder().setCheckboxDataStoreProto(newValue).build() },
      decoder = { it.checkboxDataStoreProto },
    )
    SettingsCheckbox(
      state = protoDataStoreStorage,
      icon = {
        Icon(
          imageVector = Icons.Default.SortByAlpha,
          contentDescription = "ProtoDataStore switch 3"
        )
      },
      title = { Text(text = "ProtoDataStore") },
      onCheckedChange = {
        snackbarHostState.showChange(
          coroutineScope = coroutineScope,
          key = "ProtoDataStore",
          state = protoDataStoreStorage,
        )
      },
    )
  }
}

private fun SnackbarHostState.showChange(
  coroutineScope: CoroutineScope,
  key: String,
  state: SettingValueState<Boolean>
) {
  coroutineScope.launch {
    currentSnackbarData?.dismiss()
    showSnackbar(message = "[$key]:  ${state.value}")
  }
}