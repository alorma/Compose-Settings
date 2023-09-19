package com.alorma.compose.settings.example.ui.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable

@Composable
fun ComposeSettingsTheme(
    darkThemePreference: Boolean,
    content: @Composable () -> Unit,
) {
    MaterialTheme(
        colors =
        if (darkThemePreference) {
            darkColors()
        } else {
            lightColors()
        },
        content = content,
    )
}
