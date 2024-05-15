package com.alorma.compose.settings

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.PreviewParameter
import com.alorma.compose.settings.sample.shared.previews.NullableBooleanStateProvider
import com.alorma.compose.settings.sample.shared.previews.SettingsTriStateCheckboxTilePreview
import com.alorma.compose.settings.sample.shared.previews.SuperPreviews

class SettingsTriStateCheckboxTileTest {

  @SuperPreviews
  @Composable
  fun SettingsTriStateCheckboxTilePreviewTest(
    @PreviewParameter(provider = NullableBooleanStateProvider::class) state: Boolean?,
  ) {
    SettingsTriStateCheckboxTilePreview(state)
  }
}