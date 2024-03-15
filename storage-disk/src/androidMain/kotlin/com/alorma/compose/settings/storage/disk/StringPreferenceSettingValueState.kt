package com.alorma.compose.settings.storage.disk

import android.content.SharedPreferences
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.edit
import androidx.preference.PreferenceManager
import com.alorma.compose.settings.storage.base.SettingValueState

@Composable
fun rememberPreferenceStringSettingState(
  key: String,
  defaultValue: String? = null,
  preferences: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(LocalContext.current),
): StringPreferenceSettingValueState {
  return remember {
    StringPreferenceSettingValueState(
      key = key,
      preferences = preferences,
      defaultValue = defaultValue,
    )
  }
}

class StringPreferenceSettingValueState(
  private val preferences: SharedPreferences,
  val key: String,
  val defaultValue: String?,
) : SettingValueState<String?> {

  private var _value by mutableStateOf(preferences.getString(key, defaultValue))

  override var value: String?
    set(value) {
      _value = value
      preferences.edit { putString(key, value) }
    }
    get() = _value

  override fun reset() {
    value = defaultValue
  }
}
