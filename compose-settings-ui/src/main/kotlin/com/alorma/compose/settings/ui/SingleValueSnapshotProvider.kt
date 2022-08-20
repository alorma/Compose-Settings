package com.alorma.compose.settings.ui

import com.alorma.compose.settings.storage.base.*
import kotlinx.coroutines.flow.flowOf

internal class SingleValueSnapshotMarker private constructor() : SettingsSnapshotMarker

@Suppress("UNCHECKED_CAST")
internal open class SingleValueStateSnapshot<TValue>(private val state: SettingValueState<TValue>) :
    SettingsSnapshot<SingleValueSnapshotMarker> {
    class Key<T>(override val defaultValue: T) : SettingsSnapshot.Key<SingleValueSnapshotMarker, T> {
        override val name: String
            get() = ""
    }

    override fun <T> contains(key: SettingsSnapshot.Key<SingleValueSnapshotMarker, T>) = true

    override fun <T> get(key: SettingsSnapshot.Key<SingleValueSnapshotMarker, T>): T {
        return state.value as T
    }
}

internal class MutableSingleValueStateSnapshot<TValue>(
    private val state: SettingValueState<TValue>
) : SingleValueStateSnapshot<TValue>(state), MutableSettingsSnapshot<SingleValueSnapshotMarker> {
    @Suppress("UNCHECKED_CAST")
    override fun <T> set(key: SettingsSnapshot.Key<SingleValueSnapshotMarker, T>, value: T) {
        state.value = value as TValue
    }
}

internal class SingleValueSnapshotProvider<T>(
    private val state: SettingValueState<T>
) : AsyncSettingsSnapshotProvider<SingleValueSnapshotMarker, SingleValueStateSnapshot<T>, MutableSingleValueStateSnapshot<T>> {
    override val snapshotStateFlow = flowOf(SettingsSnapshotLoadState.Success(SingleValueStateSnapshot(state)))

    override fun retryLoadSnapshot() {
    }

    override suspend fun updateSnapshot(block: (MutableSingleValueStateSnapshot<T>) -> Unit) {
        block(MutableSingleValueStateSnapshot(state))
    }

}