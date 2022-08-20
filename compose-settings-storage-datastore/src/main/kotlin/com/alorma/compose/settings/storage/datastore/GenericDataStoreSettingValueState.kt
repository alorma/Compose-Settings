package com.alorma.compose.settings.storage.datastore

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import com.alorma.compose.settings.storage.base.SettingValueState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class GenericDataStoreSettingValueState<T>(
  private val coroutineScope: CoroutineScope,
  private val dataStore: DataStore<Preferences>,
  private val dataStoreKey: Preferences.Key<T>,
  private val defaultValue: T,
) : SettingValueState<T> {

  private var _value: T by mutableStateOf(runBlocking {
    dataStore.data.map { preferences ->
      preferences[dataStoreKey] ?: defaultValue
    }.first()
  })

  override var value: T
    get() = _value
    set(value: T) {
      _value = value
      coroutineScope.launch {
        dataStore.edit { mutablePreferences ->
          mutablePreferences[dataStoreKey] = value
        }
      }
    }

  override fun reset() {
    value = defaultValue
  }
}