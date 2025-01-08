package com.alorma.compose.settings.russwolf.settings

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.alorma.compose.settings.storage.base.SettingValueState
import com.russhwolf.settings.Settings

@Composable
fun rememberBooleanSettingState(
  key: String,
  defaultValue: Boolean = false,
  settings: Settings = Settings()
): BooleanSettingValueState {
  return remember {
    BooleanSettingValueState(
      settings = settings,
      key = key,
      defaultValue = defaultValue,
    )
  }
}

class BooleanSettingValueState(
  private val settings: Settings,
  val key: String,
  val defaultValue: Boolean = false,
) : SettingValueState<Boolean> {

  private var _value by mutableStateOf(settings.getBoolean(key, defaultValue))

  override var value: Boolean
    set(value) {
      _value = value
      settings.putBoolean(key, value)
    }
    get() = _value

  override fun reset() {
    value = defaultValue
  }
}
