package com.alorma.compose.settings.ui.internal

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.DividerDefaults
import androidx.compose.material3.ListItem
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

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
    modifier = Modifier.defaultMinSize(minHeight = minHeight),
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
        Row(
          modifier = Modifier.size(minHeight),
          verticalAlignment = Alignment.CenterVertically,
        ) {
          if (actionDivider) {
            val color = DividerDefaults.color.copy(
              alpha = if (enabled) {
                1f
              } else {
                0.6f
              },
            )
            SettingsTileVerticalDivider(
              color = color,
              modifier = Modifier.fillMaxHeight(),
            )
          }
          Box(
            modifier = Modifier.size(minHeight - DividerDefaults.Thickness),
            contentAlignment = Alignment.Center,
          ) {
            action(enabled)
          }
        }
      }
    },
  )
}

@Composable
private fun SettingsTileVerticalDivider(
  modifier: Modifier = Modifier,
  thickness: Dp = DividerDefaults.Thickness,
  color: Color = DividerDefaults.color,
) = Canvas(modifier.width(thickness)) {
  drawLine(
    color = color,
    strokeWidth = thickness.toPx(),
    start = Offset(thickness.toPx() / 2, 0f),
    end = Offset(thickness.toPx() / 2, size.height),
  )
}
