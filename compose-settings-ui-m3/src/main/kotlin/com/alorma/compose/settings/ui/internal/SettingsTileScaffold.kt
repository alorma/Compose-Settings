package com.alorma.compose.settings.ui.internal

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ListItem
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun SettingsTileScaffold(
  title: @Composable () -> Unit,
  subtitle: @Composable (() -> Unit)? = null,
  icon: (@Composable () -> Unit)? = null,
  action: (@Composable () -> Unit)? = null,
  actionDivider: Boolean = false,
) {
  ListItem(
    modifier = Modifier.height(56.dp),
    headlineText = title,
    supportingText = subtitle,
    leadingContent = icon,
    trailingContent = if (action == null) {
      null
    } else {
      {
        if (actionDivider) {
          Divider(
            modifier = Modifier
              .padding(vertical = 4.dp)
              .fillMaxHeight()
              .width(1.dp),
          )
        }
        Spacer(modifier = Modifier.width(2.dp))
        action()
      }
    },
  )
}
