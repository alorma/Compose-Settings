package com.alorma.settings.storage

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.rememberSaveable

@Composable
fun rememberBooleanStorage(): ValueStorage<Boolean> {
  return rememberSaveable(saver = ValueStorage.Saver) {
    ValueStorage.noOp()
  }
}

abstract class ValueStorage<T> {

  @Composable
  fun state(key: String): MutableState<T> = remember { mutableStateOf(get(key)) }

  abstract fun save(key: String, value: Boolean)

  abstract fun get(key: String): T

  companion object {
    internal fun noOp() = object : ValueStorage<Boolean>() {
      override fun save(key: String, value: Boolean) {}
      override fun get(key: String): Boolean = false
    }

    val Saver: Saver<ValueStorage<Boolean>, *> = androidx.compose.runtime.saveable.Saver(
      save = { "" },
      restore = { noOp() },
    )
  }
}