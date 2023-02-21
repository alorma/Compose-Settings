package com.alorma.compose.settings.storage.preferences

import android.content.SharedPreferences
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.edit
import androidx.preference.PreferenceManager
import com.alorma.compose.settings.storage.base.SettingValueState

@Composable
fun <T> rememberSettingsPreferenceState(
  key: String,
  defaultValue: T,
  delimiter: String = "|",
  preferences: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(LocalContext.current),
): PreferenceSettingValueState<T> {
  return remember {
      PreferenceSettingValueState(
          key = key, defaultValue = defaultValue,
          delimiter = delimiter, preferences = preferences
      )
  }
}

@Suppress("unchecked_cast")
class PreferenceSettingValueState<T>(
  val key: String,
  val defaultValue: T,
  val delimiter: String = "|",
  private val preferences: SharedPreferences,
) : SettingValueState<T> {

  private var _value by mutableStateOf(
    if (defaultValue is Set<*>) {
        preferences.getString(key, (defaultValue as Set<*>).toPrefString(delimiter))
          .orEmpty().split(delimiter)
          .filter { it.isNotEmpty() }.map { it.toInt() }
          .toMutableSet()
    } else preferences.all[key] as T ?: defaultValue
  )

  override var value: T
      set(value) {
          _value = value
          when (value) {
              is Boolean -> preferences.edit { putBoolean(key, value) }
              is String -> preferences.edit { putString(key, value) }
              is Int -> preferences.edit { putInt(key, value) }
              is Float -> preferences.edit { putFloat(key, value) }
              is Set<*> -> preferences.edit { putString(key, value.toPrefString(delimiter)) }
              else -> throw IllegalArgumentException("Type ${value!!::class.simpleName} is not supported")
          }
      }
      get() = _value as T

  private fun Set<*>.toPrefString(delimiter: String): String {
    return joinToString(separator = delimiter) { it.toString() }
  }

  override fun reset() {
      value = defaultValue
  }
}
