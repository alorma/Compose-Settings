package com.alorma.compose.settings.ui.internal

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ProvideTextStyle
import androidx.compose.runtime.Composable

@Composable
internal fun SettingsTileSubtitle(subtitle: @Composable () -> Unit) {
  ProvideTextStyle(value = MaterialTheme.typography.bodyMedium) {
    subtitle()
  }
}
