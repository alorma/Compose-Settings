package com.alorma.settings.composables.internal

import androidx.compose.material.MaterialTheme
import androidx.compose.material.ProvideTextStyle
import androidx.compose.runtime.Composable

@Composable
internal fun SettingsTileTitle(title: @Composable() () -> Unit) {
    ProvideTextStyle(value = MaterialTheme.typography.subtitle1) {
        title()
    }
}