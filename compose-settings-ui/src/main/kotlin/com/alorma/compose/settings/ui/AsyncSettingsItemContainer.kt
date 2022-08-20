package com.alorma.compose.settings.ui

import android.util.Log
import android.widget.TextView
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.alorma.compose.settings.storage.base.*
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.filterIsInstance
import kotlinx.coroutines.flow.map
import kotlin.coroutines.CoroutineContext

class AsyncSettingsItemScope<TMarker : SettingsSnapshotMarker, TReadonly : SettingsSnapshot<TMarker>, TMutable : MutableSettingsSnapshot<TMarker>>(
    private val updateScope: CoroutineScope,
    private val updateContext: CoroutineContext,
    private val provider: AsyncSettingsSnapshotProvider<TMarker, TReadonly, TMutable>,
    private val onUpdateError: ((cause: Throwable) -> Unit)?
) {
    fun<TValue> getValueFlow(key: SettingsSnapshot.Key<TMarker, TValue>) = provider.snapshotStateFlow
        .filterIsInstance<SettingsSnapshotLoadState.Success<TReadonly>>()
        .map { it.value[key] }

    fun<TValue> update(key: SettingsSnapshot.Key<TMarker, TValue>, value: TValue): Job {
        return updateScope.launch(updateContext) {
            try {
                provider.updateSnapshot {
                    it[key] = value
                }
            } catch (cause: Throwable) {
                // Cancellation shouldn't be misinterpreted as actual update error.
                if (cause !is CancellationException) {
                    val onUpdateError = onUpdateError
                    if (onUpdateError != null) {
                        onUpdateError(cause)
                    } else {
                        // If there's no handler, re-throw the exception to be exception-transparent.
                        throw cause
                    }
                }
            }
        }
    }

    fun<TValue> updateHandlerFor(key: SettingsSnapshot.Key<TMarker, TValue>) = AsyncValueUpdateHandler(this, key)
}

class AsyncValueUpdateHandler<TMarker : SettingsSnapshotMarker, TValue, TReadonly : SettingsSnapshot<TMarker>, TMutable : MutableSettingsSnapshot<TMarker>>(
    private val itemScope: AsyncSettingsItemScope<TMarker, TReadonly, TMutable>,
    private val key: SettingsSnapshot.Key<TMarker, TValue>,
) {
    @Volatile
    private var lastJob: Job? = null

    fun update(value: TValue) {
        synchronized(this) {
            lastJob?.let {
                if(it.isActive) {
                    it.cancel()
                }
            }
            lastJob?.cancel()
            lastJob = itemScope.update(key, value)
        }
    }
}

class ErrorScreenManager(private val provider: AsyncSettingsSnapshotProvider<*, *, *>) {
    fun retry() {
        provider.retryLoadSnapshot()
    }
}

@Composable
fun <TMarker : SettingsSnapshotMarker, TReadonly : SettingsSnapshot<TMarker>, TMutable : MutableSettingsSnapshot<TMarker>> AsyncSettingsItemContainer(
    modifier: Modifier = Modifier,
    provider: AsyncSettingsSnapshotProvider<TMarker, TReadonly, TMutable>,
    updateScope: CoroutineScope,
    updateContext: CoroutineContext = Dispatchers.IO,
    onUpdateError: ((cause: Throwable) -> Unit)? = null,
    loadingIndicator: @Composable BoxScope.() -> Unit,
    errorScreen: @Composable BoxScope.(cause: Throwable, retry: () -> Unit) -> Unit,
    content: @Composable AsyncSettingsItemScope<TMarker, TReadonly, TMutable>.() -> Unit
) {
    val snapshotState by provider.snapshotStateFlow.collectAsState(initial = SettingsSnapshotLoadState.Loading())
    val itemScope by remember(updateScope, updateContext, provider, onUpdateError) {
        derivedStateOf {
            AsyncSettingsItemScope(updateScope, updateContext, provider, onUpdateError)
        }
    }

    val retryLambda by remember(provider) {
        derivedStateOf {
            { provider.retryLoadSnapshot() }
        }
    }

    Box(modifier = modifier) {
        when (snapshotState) {
            is SettingsSnapshotLoadState.Loading -> {
                loadingIndicator()
            }
            is SettingsSnapshotLoadState.Error -> {
                val cause = (snapshotState as SettingsSnapshotLoadState.Error<TReadonly>).cause

                errorScreen(cause, retryLambda)
            }
            is SettingsSnapshotLoadState.Success -> {
                content(itemScope)
            }
        }
    }
}