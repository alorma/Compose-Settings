package com.alorma.settings.storage.preferences

import android.content.Context
import android.content.SharedPreferences
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.edit
import androidx.preference.PreferenceManager
import com.alorma.settings.storage.SettingValueState

@Composable
fun rememberPreferenceBooleanState(key: String, defaultValue: Boolean): BooleanPreferenceSettingValueState {
  val context = LocalContext.current
  return remember {
    BooleanPreferenceSettingValueState(
      context = context,
      key = key,
      defaultValue = defaultValue
    )
  }
}

class BooleanPreferenceSettingValueState(
  context: Context,
  private val preferences: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(context),
  val key: String,
  val defaultValue: Boolean = false,
) : SettingValueState<Boolean> {

  private var _value by mutableStateOf(preferences.getBoolean(key, defaultValue))

  override var value: Boolean
    set(value) {
      _value = value
      preferences.edit { putBoolean(key, value) }
    }
    get() = _value
}
