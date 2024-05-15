package com.alorma.compose.settings.sample.shared.previews

import androidx.compose.ui.tooling.preview.datasource.CollectionPreviewParameterProvider

class BooleanStateProvider : CollectionPreviewParameterProvider<Boolean>(listOf(false, true))
class NullableBooleanStateProvider : CollectionPreviewParameterProvider<Boolean?>(listOf(null, false, true))
