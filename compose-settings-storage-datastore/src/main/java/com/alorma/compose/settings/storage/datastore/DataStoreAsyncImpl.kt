package com.alorma.compose.settings.storage.datastore

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import com.alorma.compose.settings.storage.base.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.*

class DataStoreSnapshotMarker : SettingsSnapshotMarker

open class DataStoreSnapshot internal constructor(
    private val prefs: Preferences
) : SettingsSnapshot<DataStoreSnapshotMarker> {
    class Key<T>(
        val preferencesKey: Preferences.Key<T>,
        override val defaultValue: T
    ) : SettingsSnapshot.Key<DataStoreSnapshotMarker, T> {
        override val name: String
            get() = preferencesKey.name
    }

    override fun <T> contains(key: SettingsSnapshot.Key<DataStoreSnapshotMarker, T>): Boolean {
        return prefs.contains(key.getPreferencesKey())
    }

    override fun <T> get(key: SettingsSnapshot.Key<DataStoreSnapshotMarker, T>): T {
        return prefs[key.getPreferencesKey()] ?: key.defaultValue
    }
}

class MutableDataStoreSnapshot(
    private val prefs: MutablePreferences
) : DataStoreSnapshot(prefs), MutableSettingsSnapshot<DataStoreSnapshotMarker> {
    override fun <T> set(key: SettingsSnapshot.Key<DataStoreSnapshotMarker, T>, value: T) {
        prefs[key.getPreferencesKey()] = value
    }
}

internal fun <T> SettingsSnapshot.Key<DataStoreSnapshotMarker, T>.getPreferencesKey(): Preferences.Key<T> {
    return (this as DataStoreSnapshot.Key<T>).preferencesKey
}

class AsyncDataStoreSnapshotProvider(
    private val dataStore: DataStore<Preferences>,
    stateScope: CoroutineScope,
) : AsyncSettingsSnapshotProvider<DataStoreSnapshotMarker, DataStoreSnapshot, MutableDataStoreSnapshot> {
    private val retryTriggerFlow = MutableStateFlow(Any())

    override val snapshotStateFlow = retryTriggerFlow.flatMapLatest {
        dataStore.data.map<Preferences, SettingsSnapshotLoadState<DataStoreSnapshot>> {
            SettingsSnapshotLoadState.Success(DataStoreSnapshot(it))
        }.onStart {
            emit(SettingsSnapshotLoadState.Loading())
        }.catch { cause ->
            emit(SettingsSnapshotLoadState.Error(cause))
        }
    }.stateIn(stateScope, SharingStarted.Lazily, SettingsSnapshotLoadState.Loading())

    override fun retryLoadSnapshot() {
        retryTriggerFlow.value = Any()
    }

    override suspend fun updateSnapshot(block: (MutableDataStoreSnapshot) -> Unit) {
        dataStore.edit {
            block(MutableDataStoreSnapshot(it))
        }
    }
}

fun <T> Preferences.Key<T>.asSettingsSnapshotKey(defaultValue: T): DataStoreSnapshot.Key<T> {
    return DataStoreSnapshot.Key(this, defaultValue)
}

fun dataStoreIntSettingsSnapshotKey(name: String, defaultValue: Int = -1): DataStoreSnapshot.Key<Int> {
    return intPreferencesKey(name).asSettingsSnapshotKey(defaultValue)
}

fun dataStoreLongSettingsSnapshotKey(name: String, defaultValue: Long = -1L): DataStoreSnapshot.Key<Long> {
    return longPreferencesKey(name).asSettingsSnapshotKey(defaultValue)
}

fun dataStoreFloatSettingsSnapshotKey(name: String, defaultValue: Float = 0f): DataStoreSnapshot.Key<Float> {
    return floatPreferencesKey(name).asSettingsSnapshotKey(defaultValue)
}

fun dataStoreDoubleSettingsSnapshotKey(name: String, defaultValue: Double = 0.0): DataStoreSnapshot.Key<Double> {
    return doublePreferencesKey(name).asSettingsSnapshotKey(defaultValue)
}

fun dataStoreBooleanSettingsSnapshotKey(name: String, defaultValue: Boolean): DataStoreSnapshot.Key<Boolean> {
    return booleanPreferencesKey(name).asSettingsSnapshotKey(defaultValue)
}

fun dataStoreStringSettingsSnapshotKey(name: String, defaultValue: String): DataStoreSnapshot.Key<String> {
    return stringPreferencesKey(name).asSettingsSnapshotKey(defaultValue)
}

fun dataStoreStringSetSettingsSnapshotKey(name: String, defaultValue: Set<String>): DataStoreSnapshot.Key<Set<String>> {
    return stringSetPreferencesKey(name).asSettingsSnapshotKey(defaultValue)
}

class DataStoreSnapshotKeySelector(private val name: String) {
    class Result<T> internal constructor(val preferencesKey: Preferences.Key<T>, val defaultValue: T)

    fun int(defaultValue: Int = -1) = result(defaultValue)
    fun long(defaultValue: Long = -1L) = result(defaultValue)
    fun boolean(defaultValue: Boolean) = result(defaultValue)
    fun float(defaultValue: Float = 0f) = result(defaultValue)
    fun double(defaultValue: Double = 0.0) = result(defaultValue)
    fun string(defaultValue: String) = result(defaultValue)
    fun stringSet(defaultValue: Set<String>) = result(defaultValue)

    @Suppress("UNCHECKED_CAST")
    private fun <T> result(defaultValue: T): Result<T> {
        return Result(intPreferencesKey(name) as Preferences.Key<T>, defaultValue)
    }
}

inline fun <T> dataStoreSnapshotKey(
    name: String,
    resolver: DataStoreSnapshotKeySelector.() -> DataStoreSnapshotKeySelector.Result<T>
): DataStoreSnapshot.Key<T> {
    val result = resolver(DataStoreSnapshotKeySelector(name))

    return DataStoreSnapshot.Key(result.preferencesKey, result.defaultValue)
}

@Composable
fun <T> rememberDataStoreSnapshotKey(
    name: String,
    resolver: DataStoreSnapshotKeySelector.() -> DataStoreSnapshotKeySelector.Result<T>
): DataStoreSnapshot.Key<T> {
    return remember(name, resolver) {
        dataStoreSnapshotKey(name, resolver)
    }
}