package com.alorma.compose.settings

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.alorma.compose.settings.sample.shared.previews.SuperPreviews
import com.alorma.compose.settings.ui.SettingsMenuLink
import theme.ComposeSettingsTheme

class SettingsMenuLinkTileTest {

  @SuperPreviews
  @Composable
  internal fun SettingsSwitchTilePreviewTest() {
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

  @SuperPreviews
  @Composable
  internal fun SettingsSwitchTileWithActionPreviewTest() {
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

  @SuperPreviews
  @Composable
  internal fun SettingsSwitchTileWithIconPreviewTest() {
    ComposeSettingsTheme {
      Surface {
        SettingsMenuLink(
          title = { Text(text = "Menu link tile") },
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
  internal fun SettingsSwitchTileWithIconAndActionPreviewTest() {
    ComposeSettingsTheme {
      Surface {
        SettingsMenuLink(
          title = { Text(text = "Menu link tile") },
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
}