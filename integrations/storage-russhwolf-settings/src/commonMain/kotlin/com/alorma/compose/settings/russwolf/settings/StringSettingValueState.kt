package com.alorma.compose.settings.russwolf.settings

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.alorma.compose.settings.storage.base.SettingValueState
import com.russhwolf.settings.Settings

@Composable
fun rememberStringSettingState(
  key: String,
  defaultValue: String? = null,
  settings: Settings = Settings()
): StringSettingValueState {
  return remember {
    StringSettingValueState(
      settings = settings,
      key = key,
      defaultValue = defaultValue,
    )
  }
}

class StringSettingValueState(
  private val settings: Settings,
  val key: String,
  val defaultValue: String?,
) : SettingValueState<String?> {

  private var _value by mutableStateOf(
    if (defaultValue == null) {
      settings.getStringOrNull(key)
    } else {
      settings.getString(key, defaultValue)
    }
  )

  override var value: String?
    set(value) {
      _value = value
      if (value == null) {
        settings.remove(key)
      } else {
        settings.putString(key, value)
      }
    }
    get() = _value ?: defaultValue

  override fun reset() {
    value = defaultValue
  }
}
