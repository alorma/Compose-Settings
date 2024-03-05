package com.alorma.compose.settings.ui.internal

import androidx.compose.material3.ListItem
import androidx.compose.runtime.Composable

@Composable
internal fun SettingsTileScaffold(
  enabled: Boolean = true,
  title: @Composable () -> Unit,
  subtitle: @Composable() (() -> Unit)? = null,
  icon: @Composable() (() -> Unit)? = null,
  action: @Composable() ((Boolean) -> Unit)? = null,
) {
  ListItem(
    headlineContent = { title() },
    supportingContent = if (subtitle == null) {
      null
    } else {
      { subtitle() }
    },
    leadingContent = if (icon == null) {
      null
    } else {
      { icon() }
    },
    trailingContent = if (action == null) {
      null
    } else {
      { action(enabled) }
    },
  )
}