package com.alorma.compose.settings.storage.base

import kotlin.reflect.KProperty

@Suppress("NOTHING_TO_INLINE")
inline operator fun <T> SettingValueState<T>.getValue(thisObj: Any?, property: KProperty<*>): T =
  value

@Suppress("NOTHING_TO_INLINE")
inline operator fun <T> SettingValueState<T>.setValue(
  thisObj: Any?,
  property: KProperty<*>,
  value: T,
) {
  this.value = value
}

interface SettingValueState<T> {
  fun reset()

  var value: T
}