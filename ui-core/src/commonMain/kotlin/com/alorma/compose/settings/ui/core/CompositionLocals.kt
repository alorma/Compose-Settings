package com.alorma.compose.settings.ui.core

import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.runtime.compositionLocalOf

val LocalSettingsGroupEnabled: ProvidableCompositionLocal<Boolean> = compositionLocalOf { true }

val LocalSettingsTileColors: ProvidableCompositionLocal<SettingsTileColors?> = compositionLocalOf { null }

val LocalSettingsTextStyles: ProvidableCompositionLocal<SettingsTextStyles?> = compositionLocalOf { null }
