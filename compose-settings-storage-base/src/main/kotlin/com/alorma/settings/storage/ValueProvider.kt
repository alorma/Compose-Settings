package com.alorma.settings.storage

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import kotlin.reflect.KProperty

@Composable
fun rememberBooleanSettingState(defaultValue: Boolean = false): SettingValueState<Boolean> {
  return remember { InMemoryBooleanSettingValueState(defaultValue) }
}

@Composable
fun rememberFloatSettingState(defaultValue: Float = 0f): SettingValueState<Float> {
  return remember { InMemoryFloatSettingValueState(defaultValue) }
}

@Composable
fun rememberIntSettingState(defaultValue: Int = 0): SettingValueState<Int> {
  return remember { InMemoryIntSettingValueState(defaultValue) }
}

@Suppress("NOTHING_TO_INLINE")
inline operator fun <T> SettingValueState<T>.getValue(thisObj: Any?, property: KProperty<*>): T = value

@Suppress("NOTHING_TO_INLINE")
inline operator fun <T> SettingValueState<T>.setValue(thisObj: Any?, property: KProperty<*>, value: T) {
  this.value = value
}

interface SettingValueState<T> {
  var value: T
}

internal class InMemoryBooleanSettingValueState(defaultValue: Boolean) : SettingValueState<Boolean> {
  override var value: Boolean by mutableStateOf(defaultValue)
}

internal class InMemoryFloatSettingValueState(defaultValue: Float) : SettingValueState<Float> {
  override var value: Float by mutableStateOf(defaultValue)
}

internal class InMemoryIntSettingValueState(defaultValue: Int) : SettingValueState<Int> {
  override var value: Int by mutableStateOf(defaultValue)
}