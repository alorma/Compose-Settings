package com.alorma.compose.settings.storage.memory

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.alorma.compose.settings.storage.base.SettingValueState

@Composable
fun rememberMemoryTriStateSettingState(defaultValue: Boolean? = null): SettingValueState<Boolean?> {
  return remember { InMemoryTriStateSettingValueState(defaultValue) }
}

internal class InMemoryTriStateSettingValueState(private val defaultValue: Boolean?) :
  SettingValueState<Boolean?> {
  override var value: Boolean? by mutableStateOf(defaultValue)
  override fun reset() {
    value = defaultValue
  }
}
