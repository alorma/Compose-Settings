package com.alorma.compose.settings.sample.shared.previews

import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.PreviewParameter
import com.alorma.compose.settings.ui.SettingsCheckbox
import theme.ComposeSettingsTheme

@SuperPreview
@Composable
private fun SettingsCheckboxTilePreview(
  @PreviewParameter(provider = BooleanStateProvider::class) state: Boolean,
) {
  ComposeSettingsTheme {
    Surface {
      SettingsCheckbox(
        state = state,
        title = { Text(text = "Checkbox tile") },
        onCheckedChange = {},
      )
    }
  }
}