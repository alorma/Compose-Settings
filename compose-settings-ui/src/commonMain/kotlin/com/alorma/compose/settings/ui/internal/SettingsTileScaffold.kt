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
    headlineContent = {
      WrapContentColor(enabled = enabled) {
        title()
      }
    },
    supportingContent = if (subtitle == null) {
      null
    } else {
      {
        WrapContentColor(enabled = enabled) {
          subtitle()
        }
      }
    },
    leadingContent = if (icon == null) {
      null
    } else {
      {
        WrapContentColor(enabled = enabled) {
          icon()
        }
      }
    },
    trailingContent = if (action == null) {
      null
    } else {
      {
        WrapContentColor(enabled = enabled) {
          action(enabled)
        }
      }
    },
  )
}