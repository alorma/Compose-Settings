package com.alorma.compose.settings.storage.datastore.proto

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.datastore.core.DataStore
import com.alorma.compose.settings.storage.base.SettingValueState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class GenericProtoDataStoreSettingValueState<T, R>(
    private val coroutineScope: CoroutineScope,
    private val dataStore: DataStore<R>,
    private val defaultValue: T,
    private val getter: suspend (savedValue: R) -> T,
    private val setter: suspend (savedValue: R, newValue: T) -> R
) : SettingValueState<T> {
    private var job: Job? = null
    private val valueFlow = dataStore.data.map(getter)

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
                dataStore.updateData {
                    setter(it, value)
                }
            }
        }

    override fun reset() {
        value = defaultValue
    }
}
