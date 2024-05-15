package com.alorma.compose.settings

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.PreviewParameter
import com.alorma.compose.settings.sample.shared.previews.BooleanStateProvider
import com.alorma.compose.settings.sample.shared.previews.SettingsRadioButtonTilePreview
import com.alorma.compose.settings.sample.shared.previews.SuperPreviews

class SettingsRadioButtonTileTest {

  @SuperPreviews
  @Composable
  fun SettingsRadioButtonTilePreviewTest(
    @PreviewParameter(provider = BooleanStateProvider::class) state: Boolean,
  ) {
    SettingsRadioButtonTilePreview(state)
  }
}