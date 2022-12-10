package com.alorma.compose.settings.storage.datastore.proto

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.datastore.core.DataStore
import androidx.datastore.core.Serializer
import androidx.datastore.dataStore
import com.alorma.compose.settings.storage.datastore.proto.ActiveDataStore.addActiveDataStore
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty


@Composable
inline fun <reified T : Any> rememberProtoDataStoreState(
    context: Context = LocalContext.current,
    filename: String = "compose_settings_datastore_proto.pb",
    serializer: Serializer<T>
): ProtoDataStoreState<T> {

    @Suppress("UNCHECKED_CAST")
    val dataStore: DataStore<T> = remember(context, filename, serializer) {
        (((ActiveDataStore.getActiveDataStores()[T::class] as? DataStore<T>)
            ?: ProtoDataStoreCreateSupporter(context, filename, serializer).dataStore)).also {
            addActiveDataStore(it, T::class)
        }
    }
    return rememberProtoDataStoreState(dataStore, serializer)
}

@Composable
inline fun <reified T : Any> rememberProtoDataStoreState(
    dataStore: DataStore<T>,
    serializer: Serializer<T>
): ProtoDataStoreState<T> {
    LaunchedEffect(dataStore) {
        if (ActiveDataStore.getActiveDataStores()[T::class] == null) {
            addActiveDataStore(dataStore, T::class)
        }
    }
    return remember(dataStore, serializer) {
        ProtoDataStoreState(dataStore, serializer)
    }
}

class ProtoDataStoreState<T>(
    val dataStore: DataStore<T>,
    val serializer: Serializer<T>
): ReadOnlyProperty<T?, DataStore<T>> {

    override fun getValue(thisRef: T?, property: KProperty<*>) = dataStore
}
class ProtoDataStoreCreateSupporter<T>(
    context: Context,
    filename: String,
    serializer: Serializer<T>
) {
    private val Context.dataStore: DataStore<T> by dataStore(
        fileName = filename,
        serializer = serializer
    )
    val dataStore: DataStore<T> = context.applicationContext.dataStore
}