package com.alorma.compose.settings.example.demo

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsToolbar(
    title: @Composable () -> Unit,
    onBack: (() -> Unit)? = null,
    showSettings: Boolean = true,
    enabledState: Boolean = true,
    onEnabledChange: (Boolean) -> Unit,
    onNavigateSettings: () -> Unit,
    scrollBehavior: TopAppBarScrollBehavior,
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
        scrollBehavior = scrollBehavior,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
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
            scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(),
        )
    }
}
