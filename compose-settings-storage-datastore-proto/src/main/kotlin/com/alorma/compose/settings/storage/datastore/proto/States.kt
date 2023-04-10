package com.alorma.compose.settings.storage.datastore.proto

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import kotlinx.coroutines.CoroutineScope

@Composable
fun <T> rememberProtoDataStoreSettingState(
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
    protoDataStoreState: ProtoDataStoreState<T>
): GenericProtoDataStoreSettingValueState<T, T> {
    return rememberProtoDataStoreTransformSettingState(
        coroutineScope = coroutineScope,
        protoDataStoreState = protoDataStoreState,
        encoder = { _, newValue -> newValue },
        decoder = { it }
    )
}

@Composable
fun <T, R> rememberProtoDataStoreTransformSettingState(
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
    protoDataStoreState: ProtoDataStoreState<R>,
    encoder: (savedValue: R, newValue: T) -> R,
    decoder: (R) -> T
): GenericProtoDataStoreSettingValueState<T, R> {
    return remember {
        GenericProtoDataStoreSettingValueState(
            coroutineScope = coroutineScope,
            dataStore = protoDataStoreState.dataStore,
            defaultValue = decoder(protoDataStoreState.serializer.defaultValue),
            getter = decoder,
            setter = { savedValue, newValue -> encoder(savedValue, newValue) }
        )
    }
}
