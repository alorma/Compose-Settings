package com.alorma.compose.settings.storage.datastore

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import kotlinx.coroutines.CoroutineScope

@Composable
fun <T> rememberSettingsDataStoreState(
  coroutineScope: CoroutineScope = rememberCoroutineScope(), key: String, defaultValue: T,
  dataStore: DataStore<Preferences> = LocalContext.current.composeSettingsDataStore,
): PreferenceDataStoreValueState<T> {
  return remember {
    PreferenceDataStoreValueState(
      coroutineScope = coroutineScope, dataStore = dataStore,
      dataStoreKey = makePreferenceKey(defaultValue, key), defaultValue = defaultValue
    )
  }
}

@Suppress("unchecked_cast")
private fun <T> makePreferenceKey(valueType: T, key: String): Preferences.Key<T> {
  return (when (valueType) {
    is Int -> intPreferencesKey(key)
    is Float -> floatPreferencesKey(key)
    is String -> stringPreferencesKey(key)
    is Boolean -> booleanPreferencesKey(key)
    else -> throw IllegalArgumentException("Type ${valueType!!::class.java.simpleName} is not supported")
  }) as Preferences.Key<T>
}