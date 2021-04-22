package com.alorma.settings

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.VerifiedUser
import androidx.compose.material.icons.filled.Wifi
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.alorma.settings.composables.SettingsCheckbox
import com.alorma.settings.composables.SettingsGroup
import com.alorma.settings.composables.SettingsSwitch

@Composable
fun SettingsList(
    title: @Composable () -> Unit,
    settings: @Composable ColumnScope.() -> Unit,
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
        Column(content = settings)
    }
}

@Preview
@Composable
fun SettingsListPreview() {
    MaterialTheme {
        SettingsList(
            title = { Text(text = "Preview settings") }
        ) {
            SettingsGroup {
                SettingsSwitch(
                    key = "Demo1",
                    icon = { Icon(imageVector = Icons.Default.Wifi, contentDescription = "Wifi") },
                    title = { Text(text = "Demo 1") },
                    checked = false
                )
                Divider()
                SettingsCheckbox(
                    key = "Demo2",
                    icon = {
                        Icon(imageVector = Icons.Default.VerifiedUser,
                            contentDescription = "User")
                    },
                    title = { Text(text = "Demo 2") },
                    subtitle = { Text(text = "This a subtitle") },
                    checked = true
                )
            }
            SettingsGroup(
                title = { Text(text = "Group 2") },
            ) {
                SettingsSwitch(
                    key = "Demo3",
                    icon = { Icon(imageVector = Icons.Default.Wifi, contentDescription = "Wifi") },
                    title = { Text(text = "Demo 3") },
                    checked = false
                )
                Divider()
                SettingsCheckbox(
                    key = "Demo4",
                    icon = {
                        Icon(imageVector = Icons.Default.VerifiedUser,
                            contentDescription = "User")
                    },
                    title = { Text(text = "Demo 4") },
                    subtitle = { Text(text = "This a subtitle") },
                    checked = true
                )
            }
        }
    }
}

