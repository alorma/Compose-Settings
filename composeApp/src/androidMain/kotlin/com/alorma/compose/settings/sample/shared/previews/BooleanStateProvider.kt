package com.alorma.compose.settings.sample.shared.previews

import androidx.compose.ui.tooling.preview.datasource.CollectionPreviewParameterProvider

internal class BooleanStateProvider : CollectionPreviewParameterProvider<Boolean>(listOf(false, true))
internal class NullableBooleanStateProvider : CollectionPreviewParameterProvider<Boolean?>(listOf(null, false, true))