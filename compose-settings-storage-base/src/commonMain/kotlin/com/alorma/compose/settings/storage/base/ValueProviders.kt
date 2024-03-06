package com.alorma.compose.settings.storage.base

import kotlin.reflect.KProperty

@Deprecated(message = "To be deleted")
inline operator fun <T> SettingValueState<T>.getValue(thisObj: Any?, property: KProperty<*>): T =
  value

@Deprecated(message = "To be deleted")
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
