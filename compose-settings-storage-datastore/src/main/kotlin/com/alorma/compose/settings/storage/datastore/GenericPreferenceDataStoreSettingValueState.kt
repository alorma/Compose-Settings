package com.alorma.compose.settings.storage.datastore

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import com.alorma.compose.settings.storage.base.SettingValueState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class GenericPreferenceDataStoreSettingValueState<T>(
  private val coroutineScope: CoroutineScope,
  private val dataStore: DataStore<Preferences>,
  private val dataStoreKey: Preferences.Key<T>,
  private val defaultValue: T,
) : SettingValueState<T> {
  private var job: Job? = null
  private val valueFlow = dataStore.data.map { it[dataStoreKey] ?: defaultValue }

  private var _value: T by mutableStateOf(runBlocking { valueFlow.first() })

  init {
    coroutineScope.launch {
      valueFlow.collect { _value = it }
    }
  }

  override var value: T
    get() = _value
    set(value) {
      _value = value
      job?.cancel()
      job = coroutineScope.launch {
        dataStore.edit {
          it[dataStoreKey] = value
        }
      }
    }

  override fun reset() {
    value = defaultValue
  }
}