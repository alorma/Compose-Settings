package com.alorma.compose.settings.ui.base.internal

import androidx.compose.material3.ListItemColors
import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.runtime.compositionLocalOf

val LocalSettingsGroupEnabled = compositionLocalOf {
    true
}

val LocalSettingsTileColors: ProvidableCompositionLocal<ListItemColors?> = compositionLocalOf {
    null
}