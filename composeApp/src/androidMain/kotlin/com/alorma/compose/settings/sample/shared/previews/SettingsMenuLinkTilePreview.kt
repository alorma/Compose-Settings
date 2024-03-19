package com.alorma.compose.settings.sample.shared.previews

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