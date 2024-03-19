package com.alorma.compose.settings.sample.shared.previews

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.PreviewParameter
import com.alorma.compose.settings.ui.SettingsMenuLink
import theme.ComposeSettingsTheme

@SuperPreview
@Composable
private fun SettingsSwitchTilePreview(
  @PreviewParameter(provider = BooleanStateProvider::class) state: Boolean,
) {
  ComposeSettingsTheme {
    Surface {
      SettingsMenuLink(
        title = { Text(text = "Menu link tile") },
        subtitle = { Text(text = "Some extra text") },
        onClick = {},
      )
    }
  }
}

@SuperPreview
@Composable
private fun SettingsSwitchTileWithActionPreview(
  @PreviewParameter(provider = BooleanStateProvider::class) state: Boolean,
) {
  ComposeSettingsTheme {
    Surface {
      SettingsMenuLink(
        title = { Text(text = "Menu link tile") },
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