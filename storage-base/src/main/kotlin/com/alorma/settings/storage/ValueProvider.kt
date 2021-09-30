package com.alorma.settings.storage

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

@Composable
fun rememberBooleanStorage(defaultValue: Boolean = false): ValueStorage<Boolean> {
  return remember { InMemoryBooleanValueStorage(defaultValue) }
}

interface ValueStorage<T> {
  var value: T
}

internal class InMemoryBooleanValueStorage(defaultValue: Boolean) : ValueStorage<Boolean> {
  override var value: Boolean by mutableStateOf(defaultValue)
}