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
  // we need to check `javaObjectType` because valueType!!::class.java
  // will not be java.lang.Float but float i.e. a primitive type, & similar for other primitives.
  val checkableClass = valueType!!::class.java
  return (when (checkableClass) {
    Float::class.javaObjectType -> floatPreferencesKey(key)
    Integer::class.javaObjectType -> intPreferencesKey(key)
    String::class.javaObjectType -> stringPreferencesKey(key)
    Boolean::class.javaObjectType -> booleanPreferencesKey(key)
    else -> throw IllegalArgumentException("Type ${checkableClass.simpleName} is not supported")
  }) as Preferences.Key<T>
}