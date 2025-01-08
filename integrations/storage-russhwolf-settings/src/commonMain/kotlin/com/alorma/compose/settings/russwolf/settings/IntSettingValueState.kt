package com.alorma.compose.settings.russwolf.settings

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.alorma.compose.settings.storage.base.SettingValueState
import com.russhwolf.settings.Settings

@Composable
fun rememberIntSettingState(
  key: String,
  defaultValue: Int = -1,
  settings: Settings = Settings()
): IntSettingValueState {
  return remember {
    IntSettingValueState(
      settings = settings,
      key = key,
      defaultValue = defaultValue,
    )
  }
}

class IntSettingValueState(
  private val settings: Settings,
  val key: String,
  val defaultValue: Int = 0,
) : SettingValueState<Int> {

  private var _value by mutableStateOf(settings.getInt(key, defaultValue))

  override var value: Int
    set(value) {
      _value = value
      settings.putInt(key, value)
    }
    get() = _value

  override fun reset() {
    value = defaultValue
  }
}
