package com.alorma.compose.settings.example.demo

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Switch
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun SettingsToolbar(
    title: @Composable () -> Unit,
    onBack: (() -> Unit)? = null,
    showSettings: Boolean = true,
    enabledState: Boolean = true,
    onEnabledChange: (Boolean) -> Unit,
    onNavigateSettings: () -> Unit,
) {
    TopAppBar(
        title = title,
        navigationIcon = {
            if (onBack != null) {
                IconButton(onClick = { onBack.invoke() }) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = null,
                    )
                }
            }
        },
        actions = {
            Switch(checked = enabledState, onCheckedChange = {
                onEnabledChange(it)
            })
            if (showSettings) {
                IconButton(onClick = onNavigateSettings) {
                    Icon(imageVector = Icons.Default.Settings, contentDescription = "Settings")
                }
            }
        },
    )
}

@Preview
@Composable
fun SearchToolbarPreview() {
    MaterialTheme {
        SettingsToolbar(
            title = { Text(text = "Title") },
            onBack = {},
            enabledState = true,
            onNavigateSettings = {},
            onEnabledChange = {},
        )
    }
}
