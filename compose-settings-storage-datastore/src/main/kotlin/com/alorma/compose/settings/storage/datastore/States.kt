package com.alorma.compose.settings.storage.datastore

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.floatPreferencesKey
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringSetPreferencesKey
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

@Composable
fun rememberDataStoreFloatSettingState(
  coroutineScope: CoroutineScope = rememberCoroutineScope(),
  dataStore: DataStore<Preferences> = LocalContext.current.dataStore,
  key: String,
  defaultValue: Float,
): GenericDataStoreSettingValueState<Float> {
  return remember {
    GenericDataStoreSettingValueState(
      coroutineScope = coroutineScope,
      dataStore = dataStore,
      dataStoreKey = floatPreferencesKey(key),
      defaultValue = defaultValue,
    )
  }
}

@Composable
fun rememberDataStoreIntSettingState(
  coroutineScope: CoroutineScope = rememberCoroutineScope(),
  dataStore: DataStore<Preferences> = LocalContext.current.dataStore,
  key: String,
  defaultValue: Int,
): GenericDataStoreSettingValueState<Int> {
  return remember {
    GenericDataStoreSettingValueState(
      coroutineScope = coroutineScope,
      dataStore = dataStore,
      dataStoreKey = intPreferencesKey(key),
      defaultValue = defaultValue,
    )
  }
}