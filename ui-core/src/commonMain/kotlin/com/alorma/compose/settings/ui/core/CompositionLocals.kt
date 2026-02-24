package com.alorma.compose.settings.ui.core

import androidx.compose.material3.ListItemColors
import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.runtime.compositionLocalOf

val LocalSettingsGroupEnabled: ProvidableCompositionLocal<Boolean> = compositionLocalOf { true }

val LocalSettingsTileColors: ProvidableCompositionLocal<ListItemColors?> = compositionLocalOf { null }
