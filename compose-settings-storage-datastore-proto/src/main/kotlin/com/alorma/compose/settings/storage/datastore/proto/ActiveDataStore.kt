package com.alorma.compose.settings.storage.datastore.proto

import androidx.datastore.core.DataStore
import java.util.concurrent.locks.ReentrantReadWriteLock
import kotlin.concurrent.read
import kotlin.concurrent.write
import kotlin.reflect.KClass

object ActiveDataStore {
    private val ACTIVE_DATASTORES = mutableMapOf<KClass<*>, DataStore<*>>()
    private val lock = ReentrantReadWriteLock()

    @Deprecated(
        message = "This property is for Compose-Settings library.",
        level = DeprecationLevel.WARNING,
    )
    @Synchronized
    fun getActiveDataStores() = lock.read { ACTIVE_DATASTORES }

    @Deprecated(
        message = "This property is for Compose-Settings library.",
        level = DeprecationLevel.WARNING,
    )
    @Synchronized
    fun <T : Any> addActiveDataStore(dataStore: DataStore<T>, kClass: KClass<T>) {
        lock.write {
            ACTIVE_DATASTORES[kClass] = dataStore
        }
    }
}
