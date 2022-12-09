package com.alorma.compose.settings.storage.datastore.proto

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.datastore.core.DataStore
import androidx.datastore.core.Serializer
import androidx.datastore.dataStore
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty


@Composable
fun <T> rememberProtoDataStoreState(
    context: Context = LocalContext.current,
    filename: String = "compose_settings_datastore_proto.pb",
    serializer: Serializer<T>
): ProtoDataStoreState<T> {
    return remember(context, filename, serializer) {
        ProtoDataStoreState(context, filename, serializer)
    }
}

@Composable
fun <T> rememberProtoDataStoreState(
    dataStore: DataStore<T>,
    serializer: Serializer<T>
): ProtoDataStoreState<T> {
    return remember(dataStore, serializer) {
        ProtoDataStoreState(dataStore, serializer)
    }
}

class ProtoDataStoreState<T>(
    val dataStore: DataStore<T>,
    val serializer: Serializer<T>
): ReadOnlyProperty<T?, DataStore<T>> {

    constructor(
        context: Context,
        filename: String,
        serializer: Serializer<T>
    ) : this(ProtoDataStoreStateSupporter(context, filename, serializer).dataStore, serializer)

    override fun getValue(thisRef: T?, property: KProperty<*>): DataStore<T> {
        return dataStore
    }

    class ProtoDataStoreStateSupporter<T>(
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
}
