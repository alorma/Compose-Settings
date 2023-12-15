package com.alorma.compose.settings.storage.disk

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

  private var _value by mutableStateOf(settings.getStringOrNull(key))

  override var value: String?
    set(value) {
      _value = value
      settings.putString(key, value.orEmpty())
    }
    get() = _value

  override fun reset() {
    value = defaultValue
  }
}
