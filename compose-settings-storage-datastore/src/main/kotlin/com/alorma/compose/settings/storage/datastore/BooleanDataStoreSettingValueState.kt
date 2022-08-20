package com.alorma.compose.settings.storage.datastore

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import com.alorma.compose.settings.storage.base.SettingValueState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

@Composable
fun rememberDataStoreBooleanSettingState(
  coroutineScope: CoroutineScope = rememberCoroutineScope(),
  dataStore: DataStore<Preferences> = LocalContext.current.dataStore,
  key: Preferences.Key<Boolean>,
  defaultValue: Boolean,
): BooleanDataStoreSettingValueState {

  return remember {
    BooleanDataStoreSettingValueState(
      coroutineScope = coroutineScope,
      dataStore = dataStore,
      key = key,
      defaultValue = defaultValue,
    )
  }
}

class BooleanDataStoreSettingValueState(
  private val coroutineScope: CoroutineScope,
  private val dataStore: DataStore<Preferences>,
  private val key: Preferences.Key<Boolean>,
  private val defaultValue: Boolean,
) : SettingValueState<Boolean> {

  private var _value by mutableStateOf(runBlocking {
    dataStore.data.map { preferences ->
      preferences[key] ?: defaultValue
    }.first()
  })

  override var value: Boolean
    get() = _value
    set(value) {
      _value = value
      coroutineScope.launch {
        dataStore.edit { mutablePreferences ->
          mutablePreferences[key] = value
        }
      }
    }

  override fun reset() {
  }
}