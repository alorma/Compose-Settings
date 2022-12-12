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
import kotlinx.coroutines.CoroutineScope

@Deprecated("Unclear Method Name", ReplaceWith("rememberPreferenceDataStoreBooleanSettingState(coroutineScope, dataStore, key, defaultValue)"))
@Composable
fun rememberDataStoreBooleanSettingState(
  coroutineScope: CoroutineScope = rememberCoroutineScope(),
  dataStore: DataStore<Preferences> = LocalContext.current.composeSettingsDataStore,
  key: String,
  defaultValue: Boolean = false,
) = rememberPreferenceDataStoreBooleanSettingState(
  coroutineScope = coroutineScope,
  dataStore = dataStore,
  key = key,
  defaultValue = defaultValue
)

@Composable
fun rememberPreferenceDataStoreBooleanSettingState(
  coroutineScope: CoroutineScope = rememberCoroutineScope(),
  dataStore: DataStore<Preferences> = LocalContext.current.composeSettingsDataStore,
  key: String,
  defaultValue: Boolean = false,
): GenericPreferenceDataStoreSettingValueState<Boolean> {
  return remember {
    GenericPreferenceDataStoreSettingValueState(
      coroutineScope = coroutineScope,
      dataStore = dataStore,
      dataStoreKey = booleanPreferencesKey(key),
      defaultValue = defaultValue,
    )
  }
}


@Deprecated("Unclear Method Name", ReplaceWith("rememberPreferenceDataStoreFloatSettingState(coroutineScope, dataStore, key, defaultValue)"))
@Composable
fun rememberDataStoreFloatSettingState(
  coroutineScope: CoroutineScope = rememberCoroutineScope(),
  dataStore: DataStore<Preferences> = LocalContext.current.composeSettingsDataStore,
  key: String,
  defaultValue: Float = 0f,
) = rememberPreferenceDataStoreFloatSettingState(
  coroutineScope = coroutineScope,
  dataStore = dataStore,
  key = key,
  defaultValue = defaultValue
)

@Composable
fun rememberPreferenceDataStoreFloatSettingState(
  coroutineScope: CoroutineScope = rememberCoroutineScope(),
  dataStore: DataStore<Preferences> = LocalContext.current.composeSettingsDataStore,
  key: String,
  defaultValue: Float = 0F,
): GenericPreferenceDataStoreSettingValueState<Float> {
  return remember {
    GenericPreferenceDataStoreSettingValueState(
      coroutineScope = coroutineScope,
      dataStore = dataStore,
      dataStoreKey = floatPreferencesKey(key),
      defaultValue = defaultValue,
    )
  }
}


@Deprecated("Unclear Method Name", ReplaceWith("rememberPreferenceDataStoreIntSettingState(coroutineScope, dataStore, key, defaultValue)"))
@Composable
fun rememberDataStoreIntSettingState(
  coroutineScope: CoroutineScope = rememberCoroutineScope(),
  dataStore: DataStore<Preferences> = LocalContext.current.composeSettingsDataStore,
  key: String,
  defaultValue: Int = -1,
) = rememberPreferenceDataStoreIntSettingState(
  coroutineScope = coroutineScope,
  dataStore = dataStore,
  key = key,
  defaultValue = defaultValue
)

@Composable
fun rememberPreferenceDataStoreIntSettingState(
  coroutineScope: CoroutineScope = rememberCoroutineScope(),
  dataStore: DataStore<Preferences> = LocalContext.current.composeSettingsDataStore,
  key: String,
  defaultValue: Int = -1,
): GenericPreferenceDataStoreSettingValueState<Int> {
  return remember {
    GenericPreferenceDataStoreSettingValueState(
      coroutineScope = coroutineScope,
      dataStore = dataStore,
      dataStoreKey = intPreferencesKey(key),
      defaultValue = defaultValue,
    )
  }
}