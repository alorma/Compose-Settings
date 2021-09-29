package com.alorma.settings.storage.preferences

import android.content.Context
import android.content.SharedPreferences
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.edit
import androidx.preference.PreferenceManager
import com.alorma.settings.storage.ValueStorage

@Composable
fun rememberPreferenceBooleanStorage(key: String, defaultValue: Boolean): ValueStorage<Boolean> {
  val context = LocalContext.current
  return remember {
    PreferenceStorage(
      context = context,
      key = key,
      defaultValue = defaultValue
    )
  }
}

class PreferenceStorage(
  context: Context,
  private val key: String,
  private val defaultValue: Boolean = false,
) : ValueStorage<Boolean>() {

  private val preferences: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)

  override var value: Boolean = false
    get() {
      return preferences.getBoolean(key, defaultValue)
    }
    set(value) {
      field = value
      preferences.edit { putBoolean(key, value) }
    }
}