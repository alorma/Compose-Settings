package com.alorma.compose.settings

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.PreviewParameter
import com.alorma.compose.settings.sample.shared.previews.BooleanStateProvider
import com.alorma.compose.settings.sample.shared.previews.SettingsSwitchTilePreview
import com.alorma.compose.settings.sample.shared.previews.SuperPreviews

class SettingsSwitchTileTest {

  @SuperPreviews
  @Composable
  fun SettingsSwitchTilePreviewTest(
    @PreviewParameter(provider = BooleanStateProvider::class) state: Boolean,
  ) {
    SettingsSwitchTilePreview(state)
  }
}