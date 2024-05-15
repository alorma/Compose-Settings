package com.alorma.compose.settings.sample.shared.previews

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.alorma.compose.settings.ui.SettingsMenuLink
import theme.ComposeSettingsTheme

@SuperPreviews
@Composable
internal fun SettingsSwitchTilePreview() {
  ComposeSettingsTheme {
    Surface {
      SettingsMenuLink(
        title = { Text(text = "A - Menu link tile") },
        subtitle = { Text(text = "Some extra text") },
        onClick = {},
      )
    }
  }
}

@SuperPreviews
@Composable
internal fun SettingsSwitchTileWithActionPreview() {
  ComposeSettingsTheme {
    Surface {
      SettingsMenuLink(
        title = { Text(text = "A - Menu link tile") },
        subtitle = { Text(text = "Some extra text") },
        action = {
          IconButton(onClick = { /*TODO*/ }) {
            Icon(
              imageVector = Icons.Default.Settings,
              contentDescription = null,
            )
          }
        },
        onClick = {},
      )
    }
  }
}

@SuperPreviews
@Composable
internal fun SettingsSwitchTileWithIconPreview() {
  ComposeSettingsTheme {
    Surface {
      SettingsMenuLink(
        title = { Text(text = "A - Menu link tile") },
        subtitle = { Text(text = "Some extra text") },
        icon = {
          Icon(
            imageVector = Icons.Default.Settings,
            contentDescription = null,
          )
        },
        onClick = {},
      )
    }
  }
}

@SuperPreviews
@Composable
internal fun SettingsSwitchTileWithIconAndActionPreview() {
  ComposeSettingsTheme {
    Surface {
      SettingsMenuLink(
        title = { Text(text = "A - Menu link tile") },
        subtitle = { Text(text = "Some extra text") },
        icon = {
          Icon(
            imageVector = Icons.Default.Settings,
            contentDescription = null,
          )
        },
        action = {
          IconButton(onClick = { /*TODO*/ }) {
            Icon(
              imageVector = Icons.Default.Check,
              contentDescription = null,
            )
          }
        },
        onClick = {},
      )
    }
  }
}
