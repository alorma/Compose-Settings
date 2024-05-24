package com.alorma.compose.settings

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.PreviewParameter
import com.alorma.compose.settings.sample.shared.previews.BooleanStateProvider
import com.alorma.compose.settings.sample.shared.previews.SettingsGroupPreview
import com.alorma.compose.settings.sample.shared.previews.SuperPreviews

class SettingsGroupTest {
    @SuperPreviews
    @Composable
    fun SettingsGroupPreviewTest(
        @PreviewParameter(provider = BooleanStateProvider::class) state: Boolean,
    ) {
        SettingsGroupPreview(state)
    }
}