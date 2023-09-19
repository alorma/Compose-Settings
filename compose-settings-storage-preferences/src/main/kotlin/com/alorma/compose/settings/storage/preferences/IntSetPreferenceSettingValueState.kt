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
fun rememberPreferenceIntSetSettingState(
    key: String,
    defaultValue: Set<Int> = emptySet(),
    delimiter: String = "|",
    preferences: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(LocalContext.current),
): IntSetPreferenceSettingValueState {
    return remember {
        IntSetPreferenceSettingValueState(
            key = key,
            preferences = preferences,
            defaultValue = defaultValue,
            delimiter = delimiter,
        )
    }
}

class IntSetPreferenceSettingValueState(
    private val preferences: SharedPreferences,
    val key: String,
    val defaultValue: Set<Int> = emptySet(),
    val delimiter: String = "|",
) : SettingValueState<Set<Int>> {

    private var _value by mutableStateOf(
        preferences.getString(key, defaultValue.toPrefString(delimiter))
            .orEmpty()
            .split(delimiter)
            .filter { it.isNotEmpty() }
            .map { it.toInt() }
            .toMutableSet(),
    )

    override var value: Set<Int>
        set(value) {
            _value = value.toSortedSet()
            preferences.edit {
                putString(key, value.toPrefString(delimiter))
            }
        }
        get() = _value

    private fun Set<Int>.toPrefString(delimiter: String) =
        joinToString(separator = delimiter) { it.toString() }

    override fun reset() {
        value = defaultValue
    }
}
