package com.alorma.settings.storage

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.remember

@Composable
fun rememberBooleanStorage(): ValueStorage<Boolean> {
  return remember { InMemoryBooleanValueStorage() }
}

abstract class ValueStorage<T> : MutableState<T> {

  override fun component1(): T = value

  override fun component2(): (T) -> Unit {
    return { t -> value = t }
  }
}

internal class InMemoryBooleanValueStorage : ValueStorage<Boolean>() {
  override var value: Boolean = true
}