package com.alorma.settings.storage.preferences

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.edit
import androidx.preference.PreferenceManager
import com.alorma.settings.storage.ValueStorage

@Composable
fun rememberPreferenceBooleanStorage(): ValueStorage<Boolean> {
  val context = LocalContext.current
  return rememberSaveable(saver = ValueStorage.Saver) {
    PreferenceStorage(context = context)
  }
}

class PreferenceStorage(context: Context) : ValueStorage<Boolean>() {

  private val preferences: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)

  override fun save(key: String, value: Boolean) {
    Log.i("alorma-ktx", "Save [$key]:$value")
    preferences.edit { putBoolean(key, value) }
  }

  override fun get(key: String): Boolean {
    val value = preferences.getBoolean(key, false)
    Log.i("alorma-ktx", "Request [$key]: $value")
    return value
  }
}