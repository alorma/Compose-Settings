package com.alorma.compose.settings.ui

import androidx.compose.runtime.*
import com.alorma.compose.settings.storage.base.SettingValueState
import kotlin.coroutines.EmptyCoroutineContext

@Composable
internal fun <T> SyncOverAsync(
    state: SettingValueState<T>,
    block: @Composable AsyncSettingsItemScope<SingleValueSnapshotMarker, SingleValueStateSnapshot<T>, MutableSingleValueStateSnapshot<T>>.() -> Unit
) {
    val scope = rememberCoroutineScope()

    val itemScope by remember(scope, state) {
        derivedStateOf {
            AsyncSettingsItemScope(
                scope,
                EmptyCoroutineContext,
                SingleValueSnapshotProvider(state),
                onUpdateError = null
            )
        }
    }

    with(itemScope) { block() }
}