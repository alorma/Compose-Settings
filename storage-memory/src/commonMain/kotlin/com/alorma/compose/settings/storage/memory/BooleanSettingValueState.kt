package com.alorma.compose.settings.storage.memory

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.alorma.compose.settings.storage.base.SettingValueState

@Composable
fun rememberMemoryBooleanSettingState(defaultValue: Boolean = false): SettingValueState<Boolean> {
  return remember { InMemoryBooleanSettingValueState(defaultValue) }
}

internal class InMemoryBooleanSettingValueState(private val defaultValue: Boolean) :
  SettingValueState<Boolean> {
  override var value: Boolean by mutableStateOf(defaultValue)
  override fun reset() {
    value = defaultValue
  }
}
