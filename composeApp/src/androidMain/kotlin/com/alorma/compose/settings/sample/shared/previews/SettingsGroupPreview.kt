package com.alorma.compose.settings.sample.shared.previews

import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.PreviewParameter
import com.alorma.compose.settings.ui.SettingsCheckbox
import com.alorma.compose.settings.ui.SettingsGroup
import com.alorma.compose.settings.ui.SettingsMenuLink
import com.alorma.compose.settings.ui.SettingsSwitch
import theme.ComposeSettingsTheme

@SuperPreviews
@Composable
internal fun SettingsGroupPreview(
  @PreviewParameter(provider = BooleanStateProvider::class) state: Boolean,
) {
  ComposeSettingsTheme {
    Surface {
      SettingsGroup(
        title = {
          Text(text = "SettingsGroupPreview")
        },
        enabled = state,
      ) {
        SettingsMenuLink(
          title = { Text(text = "Menu link tile") },
          onClick = {},
        )
        SettingsCheckbox(
          state = true,
          title = { Text(text = "Checkbox tile") },
          onCheckedChange = {},
        )
        SettingsSwitch(
          state = true,
          title = { Text(text = "Switch tile") },
          onCheckedChange = {},
        )
      }
    }
  }
}
