package com.alorma.settings.storage

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import kotlin.reflect.KProperty

@Composable
fun rememberBooleanStorage(defaultValue: Boolean = false): ValueStorage<Boolean> {
  return remember { InMemoryBooleanValueStorage(defaultValue) }
}

@Suppress("NOTHING_TO_INLINE")
inline operator fun <T> ValueStorage<T>.getValue(thisObj: Any?, property: KProperty<*>): T = value

@Suppress("NOTHING_TO_INLINE")
inline operator fun <T> ValueStorage<T>.setValue(thisObj: Any?, property: KProperty<*>, value: T) {
  this.value = value
}

interface ValueStorage<T> {
  var value: T
}

internal class InMemoryBooleanValueStorage(defaultValue: Boolean) : ValueStorage<Boolean> {
  override var value: Boolean by mutableStateOf(defaultValue)
}