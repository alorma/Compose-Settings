package com.alorma.compose.settings.ui.internal

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Divider
import androidx.compose.material3.DividerDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ListItem
import androidx.compose.material3.LocalContentColor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
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

  ListItem(
    modifier = Modifier.height(56.dp),
    headlineText = {
      WrapContentColor(enabled = enabled) {
        title()
      }
    },
    supportingText = {
      WrapContentColor(enabled = enabled) {
        subtitle?.invoke()
      }
    },
    leadingContent = {
      WrapContentColor(enabled = enabled) {
        icon?.invoke()
      }
    },
    trailingContent = if (action == null) {
      null
    } else {
      {
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
        }
        Spacer(modifier = Modifier.width(2.dp))
        action(enabled)
      }
    },
  )
}

@Composable
fun WrapContentColor(
  enabled: Boolean,
  content: @Composable () -> Unit,
) {
  val alpha = if (enabled) {
    1.0f
  } else {
    0.6f
  }
  val contentColor = LocalContentColor.current.copy(alpha = alpha)
  CompositionLocalProvider(LocalContentColor provides contentColor) {
    content()
  }
}
