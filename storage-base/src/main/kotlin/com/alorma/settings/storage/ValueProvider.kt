package com.alorma.settings.storage

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import kotlin.reflect.KProperty

@Composable
fun rememberBooleanSetting(defaultValue: Boolean = false): ValueSetting<Boolean> {
  return remember { InMemoryBooleanValueSetting(defaultValue) }
}

@Suppress("NOTHING_TO_INLINE")
inline operator fun <T> ValueSetting<T>.getValue(thisObj: Any?, property: KProperty<*>): T = value

@Suppress("NOTHING_TO_INLINE")
inline operator fun <T> ValueSetting<T>.setValue(thisObj: Any?, property: KProperty<*>, value: T) {
  this.value = value
}

interface ValueSetting<T> {
  var value: T
}

internal class InMemoryBooleanValueSetting(defaultValue: Boolean) : ValueSetting<Boolean> {
  override var value: Boolean by mutableStateOf(defaultValue)
}