package com.alorma.compose.settings.storage.base

import kotlin.reflect.KProperty

inline operator fun <T> SettingValueState<T>.getValue(thisObj: Any?, property: KProperty<*>): T =
  value

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