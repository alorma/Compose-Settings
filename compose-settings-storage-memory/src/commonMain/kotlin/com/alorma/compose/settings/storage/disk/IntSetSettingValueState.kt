package com.alorma.compose.settings.storage.disk

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.alorma.compose.settings.storage.base.SettingValueState

@Composable
fun rememberIntSetSettingState(defaultValue: Set<Int> = emptySet()): SettingValueState<Set<Int>> {
  return remember { InMemoryIntSetSettingValueState(defaultValue) }
}

internal class InMemoryIntSetSettingValueState(private val defaultValue: Set<Int>) :
  SettingValueState<Set<Int>> {
  override var value: Set<Int> by mutableStateOf(defaultValue)
  override fun reset() {
    value = defaultValue
  }
}
