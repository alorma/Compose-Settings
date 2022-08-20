package com.alorma.compose.settings.storage.base

import kotlinx.coroutines.flow.Flow

interface SettingsSnapshotMarker

// TMarker is used to disallow passing a key to get() or set() with TValue that is not supported by the implementation.
interface SettingsSnapshot<TMarker: SettingsSnapshotMarker> {
    interface Key<TMarker, TValue> {
        val name: String
        val defaultValue: TValue
    }

    fun<T> contains(key: Key<TMarker, T>): Boolean
    operator fun<T> get(key: Key<TMarker, T>): T
}

interface MutableSettingsSnapshot<TMarker: SettingsSnapshotMarker> : SettingsSnapshot<TMarker> {
    operator fun<T> set(key: SettingsSnapshot.Key<TMarker, T>, value: T)
}

sealed interface SettingsSnapshotLoadState<T : SettingsSnapshot<*>> {
    class Loading<T : SettingsSnapshot<*>> : SettingsSnapshotLoadState<T>
    data class Error<T : SettingsSnapshot<*>>(val cause: Throwable): SettingsSnapshotLoadState<T>
    data class Success<T : SettingsSnapshot<*>>(val value: T): SettingsSnapshotLoadState<T>
}

interface AsyncSettingsSnapshotProvider<TMarker: SettingsSnapshotMarker, TReadonly : SettingsSnapshot<TMarker>, TMutable : MutableSettingsSnapshot<TMarker>> {
    val snapshotStateFlow: Flow<SettingsSnapshotLoadState<TReadonly>>

    fun retryLoadSnapshot()
    suspend fun updateSnapshot(block: (TMutable) -> Unit)
}