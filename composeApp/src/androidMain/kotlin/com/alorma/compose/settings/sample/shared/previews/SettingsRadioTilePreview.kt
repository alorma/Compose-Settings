package com.alorma.compose.settings.sample.shared.previews

import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.PreviewParameter
import com.alorma.compose.settings.ui.SettingsRadioButton
import theme.ComposeSettingsTheme

@SuperPreviews
@Composable
internal fun SettingsRadioButtonTilePreview(
  @PreviewParameter(provider = BooleanStateProvider::class) state: Boolean,
) {
  ComposeSettingsTheme {
    Surface {
      SettingsRadioButton(
        state = state,
        title = { Text(text = "RadioButton tile") },
        subtitle = { Text(text = "Some extra text") },
        onClick = {},
      )
    }
  }
}
