package com.alorma.compose.settings

import androidx.compose.runtime.Composable
import com.alorma.compose.settings.sample.shared.previews.SettingsSwitchTilePreview
import com.alorma.compose.settings.sample.shared.previews.SettingsSwitchTileWithActionPreview
import com.alorma.compose.settings.sample.shared.previews.SettingsSwitchTileWithIconPreview
import com.alorma.compose.settings.sample.shared.previews.SuperPreviews

class SettingsMenuLinkTileTest {

  @SuperPreviews
  @Composable
  internal fun SettingsSwitchTilePreviewTest() {
    SettingsSwitchTilePreview()
  }

  @SuperPreviews
  @Composable
  internal fun SettingsSwitchTileWithActionPreviewTest() {
    SettingsSwitchTileWithActionPreview()
  }

  @SuperPreviews
  @Composable
  internal fun SettingsSwitchTileWithIconPreviewTest() {
    SettingsSwitchTileWithIconPreview()
  }
}