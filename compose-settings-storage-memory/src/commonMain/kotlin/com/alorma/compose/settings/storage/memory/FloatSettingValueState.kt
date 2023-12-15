package com.alorma.compose.settings.storage.memory

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.alorma.compose.settings.storage.base.SettingValueState

@Composable
fun rememberFloatSettingState(defaultValue: Float = 0f): SettingValueState<Float> {
  return remember { InMemoryFloatSettingValueState(defaultValue) }
}

internal class InMemoryFloatSettingValueState(private val defaultValue: Float) :
  SettingValueState<Float> {
  override var value: Float by mutableStateOf(defaultValue)
  override fun reset() {
    value = defaultValue
  }
}