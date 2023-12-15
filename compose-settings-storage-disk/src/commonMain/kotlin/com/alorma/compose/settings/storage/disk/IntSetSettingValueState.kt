package com.alorma.compose.settings.storage.disk

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.alorma.compose.settings.storage.base.SettingValueState
import com.russhwolf.settings.Settings

@Composable
fun rememberIntSetSettingState(
  key: String,
  defaultValue: Set<Int> = emptySet(),
  delimiter: String = "|",
  settings: Settings = Settings()
): IntSetSettingValueState {
  return remember {
    IntSetSettingValueState(
      settings = settings,
      key = key,
      defaultValue = defaultValue,
      delimiter = delimiter,
    )
  }
}

class IntSetSettingValueState(
  private val settings: Settings,
  val key: String,
  val defaultValue: Set<Int> = emptySet(),
  val delimiter: String = "|",
) : SettingValueState<Set<Int>> {

  private var _value by mutableStateOf(
    settings.getString(key, defaultValue.toPrefString(delimiter))
      .split(delimiter)
      .filter { it.isNotEmpty() }
      .map { it.toInt() }
      .toSet(),
  )

  override var value: Set<Int>
    set(value) {
      _value = value.sorted().toSet()
      settings.putString(key, value.toPrefString(delimiter))
    }
    get() = _value

  private fun Set<Int>.toPrefString(delimiter: String) =
    joinToString(separator = delimiter) { it.toString() }

  override fun reset() {
    value = defaultValue
  }
}
