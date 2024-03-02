package com.alorma.compose.settings.storage.disk

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.alorma.compose.settings.storage.base.SettingValueState
import com.russhwolf.settings.Settings

@Composable
fun rememberTriStateSetting(
  key: String,
  defaultValue: Boolean? = null,
  settings: Settings = Settings()
): TriStateSettingValueState {
  return remember {
    TriStateSettingValueState(
      settings = settings,
      key = key,
      defaultValue = defaultValue,
    )
  }
}

class TriStateSettingValueState(
  private val settings: Settings,
  val key: String,
  val defaultValue: Boolean? = null,
) : SettingValueState<Boolean?> {

  private var _value by mutableStateOf(settings.getBooleanOrNull(key))

  override var value: Boolean?
    set(value) {
      _value = value
      if (value == null) {
        settings.remove(key)
      } else {
        settings.putBoolean(key, value)
      }
    }
    get() = _value

  override fun reset() {
    value = defaultValue
  }
}