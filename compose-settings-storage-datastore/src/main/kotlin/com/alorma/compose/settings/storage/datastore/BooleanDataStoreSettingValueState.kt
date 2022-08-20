package com.alorma.compose.settings.storage.datastore

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import kotlinx.coroutines.CoroutineScope

@Composable
fun rememberDataStoreBooleanSettingState(
  coroutineScope: CoroutineScope = rememberCoroutineScope(),
  dataStore: DataStore<Preferences> = LocalContext.current.dataStore,
  key: String,
  defaultValue: Boolean,
): GenericDataStoreSettingValueState<Boolean> {
  return remember {
    GenericDataStoreSettingValueState(
      coroutineScope = coroutineScope,
      dataStore = dataStore,
      dataStoreKey = booleanPreferencesKey(key),
      defaultValue = defaultValue,
    )
  }
}