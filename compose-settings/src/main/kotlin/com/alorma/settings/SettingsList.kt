package com.alorma.settings

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable

@Composable
fun SettingsList(
    title: @Composable () -> Unit,
) {
    Scaffold(
        topBar = {
            SettingsToolbar(
                title = title,
                onBack = {},
                onSearch = {},
                onHelp = {},
            )
        }
    ) {

    }
}

