package com.alorma.settings.storage

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.rememberSaveable

@Composable
fun rememberSettingsStorage(): ValueStorage {
  return rememberSaveable(saver = ValueStorage.Saver) {
    ValueStorage.NoOp
  }
}

interface ValueStorage {
  val key: String

  fun save(key: String, value: Boolean)

  fun save(value: Boolean) = save(key, value)

  fun getBoolean(key: String): Boolean

  fun getBoolean() = getBoolean(key)

  companion object {
    val NoOp = object : ValueStorage {
      override val key: String = "NoOp"

      override fun save(key: String, value: Boolean) {
        Log.i("alorma", "Key: $key: $value")
      }

      override fun getBoolean(key: String): Boolean = true
    }

    val Saver: Saver<ValueStorage, *> = androidx.compose.runtime.saveable.Saver(
      save = { "" },
      restore = { NoOp },
    )
  }
}