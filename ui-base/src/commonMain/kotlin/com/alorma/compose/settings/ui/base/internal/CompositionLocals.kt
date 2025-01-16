package com.alorma.compose.settings.ui.base.internal

import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.runtime.compositionLocalOf

val LocalSettingsGroupEnabled: ProvidableCompositionLocal<Boolean> = compositionLocalOf { true }

val LocalSettingsTileColors: ProvidableCompositionLocal<SettingsTileColors?> = compositionLocalOf { null }
