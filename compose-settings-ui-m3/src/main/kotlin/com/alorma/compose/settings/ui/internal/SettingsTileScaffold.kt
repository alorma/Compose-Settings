package com.alorma.compose.settings.ui.internal

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Divider
import androidx.compose.material3.DividerDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ListItem
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun SettingsTileScaffold(
  enabled: Boolean = true,
  title: @Composable () -> Unit,
  subtitle: @Composable (() -> Unit)? = null,
  icon: (@Composable () -> Unit)? = null,
  action: (@Composable (Boolean) -> Unit)? = null,
  actionDivider: Boolean = false,
) {
  val minHeight = if (subtitle == null) 72.dp else 88.dp
  ListItem(
    modifier = Modifier
      .height(IntrinsicSize.Min)
      .defaultMinSize(minHeight = minHeight),
    headlineText = {
      WrapContentColor(enabled = enabled) {
        title()
      }
    },
    supportingText = if (subtitle == null) {
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
        Row(
          modifier = Modifier.fillMaxHeight(),
          verticalAlignment = Alignment.CenterVertically
        ) {
          if (actionDivider) {
            val color = DividerDefaults.color.copy(
              alpha = if (enabled) {
                1f
              } else {
                0.6f
              }
            )
            Divider(
              color = color,
              modifier = Modifier
                .padding(vertical = 4.dp)
                .fillMaxHeight()
                .width(1.dp),
            )
            Spacer(modifier = Modifier.width(2.dp))
          }
          action(enabled)
        }
      }
    },
  )
}

