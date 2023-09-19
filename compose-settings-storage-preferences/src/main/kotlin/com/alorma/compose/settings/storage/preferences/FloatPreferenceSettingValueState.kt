package com.alorma.compose.settings.storage.preferences

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
fun rememberPreferenceFloatSettingState(
    key: String,
    defaultValue: Float = 0f,
    preferences: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(LocalContext.current),
): FloatPreferenceSettingValueState {
    return remember {
        FloatPreferenceSettingValueState(
            key = key,
            preferences = preferences,
            defaultValue = defaultValue,
        )
    }
}

class FloatPreferenceSettingValueState(
    private val preferences: SharedPreferences,
    val key: String,
    val defaultValue: Float = 0f,
) : SettingValueState<Float> {

    private var _value by mutableStateOf(preferences.getFloat(key, defaultValue))

    override var value: Float
        set(value) {
            _value = value
            preferences.edit { putFloat(key, value) }
        }
        get() = _value

    override fun reset() {
        value = defaultValue
    }
}
