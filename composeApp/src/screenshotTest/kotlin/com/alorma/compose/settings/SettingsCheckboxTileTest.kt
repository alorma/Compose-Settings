package com.alorma.compose.settings

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.PreviewParameter
import com.alorma.compose.settings.sample.shared.previews.BooleanStateProvider
import com.alorma.compose.settings.sample.shared.previews.SettingsCheckboxTilePreview
import com.alorma.compose.settings.sample.shared.previews.SuperPreviews

class SettingsCheckboxTileTest {

  @SuperPreviews
  @Composable
  fun SettingsCheckboxTilePreviewTest(
    @PreviewParameter(provider = BooleanStateProvider::class) state: Boolean,
  ) {
    SettingsCheckboxTilePreview(state)
  }
}