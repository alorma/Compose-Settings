package com.alorma.compose.settings.sample.shared.previews

import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.PreviewParameter
import com.alorma.compose.settings.ui.SettingsTriStateCheckbox
import theme.ComposeSettingsTheme

@SuperPreview
@Composable
private fun SettingsTriStateCheckboxTilePreview(
  @PreviewParameter(provider = NullableBooleanStateProvider::class) state: Boolean?,
) {
  ComposeSettingsTheme {
    Surface {
      SettingsTriStateCheckbox(
        state = state,
        title = { Text(text = "TriStateCheckbox tile") },
        onCheckedChange = {},
      )
    }
  }
}