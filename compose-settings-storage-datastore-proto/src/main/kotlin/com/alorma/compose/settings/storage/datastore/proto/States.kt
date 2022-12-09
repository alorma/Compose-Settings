package com.alorma.compose.settings.storage.datastore.proto

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import androidx.datastore.core.DataStore
import androidx.datastore.core.Serializer
import kotlinx.coroutines.CoroutineScope

@Composable
fun <T> rememberProtoDataStoreSettingState(
  coroutineScope: CoroutineScope = rememberCoroutineScope(),
  protoDataStoreState: ProtoDataStoreState<T>,
): GenericDataStoreSettingValueState<T, T> {
  return rememberProtoDataStoreTransformSettingState(
    coroutineScope = coroutineScope,
    protoDataStoreState = protoDataStoreState,
    encoder = { _, newValue -> newValue },
    decoder = { it },
  )
}

@Composable
fun <T,R> rememberProtoDataStoreTransformSettingState(
  coroutineScope: CoroutineScope = rememberCoroutineScope(),
  protoDataStoreState: ProtoDataStoreState<R>,
  encoder: (savedValue: R, newValue: T) -> R,
  decoder: (R) -> T,
): GenericDataStoreSettingValueState<T, R> {
  return remember {
    GenericDataStoreSettingValueState(
      coroutineScope = coroutineScope,
      dataStore = protoDataStoreState.dataStore,
      defaultValue = decoder(protoDataStoreState.serializer.defaultValue),
      getter = decoder,
      setter = { savedValue, newValue -> encoder(savedValue, newValue) }
    )
  }
}