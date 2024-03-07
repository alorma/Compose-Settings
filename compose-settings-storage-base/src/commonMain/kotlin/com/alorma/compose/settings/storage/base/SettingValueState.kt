package com.alorma.compose.settings.storage.base

interface SettingValueState<T> {
  fun reset()

  var value: T
}
