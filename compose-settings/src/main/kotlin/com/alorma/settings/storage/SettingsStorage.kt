package com.alorma.settings.storage

import android.content.SharedPreferences
import androidx.compose.runtime.compositionLocalOf
import androidx.core.content.edit

interface SettingsStorage<T> {
    fun getValue(key: String, defValue: T): T
    fun setValue(key: String, value: T)
}

class BooleanAndroidPreferences(
    private val sharedPreferences: SharedPreferences,
) : SettingsStorage<Boolean> {

    override fun getValue(key: String, defValue: Boolean): Boolean {
        return sharedPreferences.getBoolean(key, defValue)
    }

    override fun setValue(key: String, value: Boolean) {
        sharedPreferences.edit {
            putBoolean(key, value)
        }
    }
}

val LocalBooleanSettings = compositionLocalOf<SettingsStorage<Boolean>> {
    object : SettingsStorage<Boolean> {
        private val map: MutableMap<String, Boolean> = mutableMapOf()
        override fun getValue(
            key: String,
            defValue: Boolean,
        ): Boolean {
            return map[key] ?: defValue
        }

        override fun setValue(key: String, value: Boolean) {
            map[key] = value
        }

    }
}