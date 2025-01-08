package com.alorma.compose.settings.russwolf.settings

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.alorma.compose.settings.storage.base.SettingValueState
import com.russhwolf.settings.Settings

@Composable
fun rememberFloatSettingState(
  key: String,
  defaultValue: Float = 0f,
  settings: Settings = Settings()
): FloatSettingValueState {
  return remember {
    FloatSettingValueState(
      settings = settings,
      key = key,
      defaultValue = defaultValue,
    )
  }
}

class FloatSettingValueState(
  private val settings: Settings,
  val key: String,
  val defaultValue: Float = 0f,
) : SettingValueState<Float> {

  private var _value by mutableStateOf(settings.getFloat(key, defaultValue))

  override var value: Float
    set(value) {
      _value = value
      settings.putFloat(key, value)
    }
    get() = _value

  override fun reset() {
    value = defaultValue
  }
}
