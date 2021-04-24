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
import com.alorma.settings.composables.SettingsMenuLink
import com.alorma.settings.composables.SettingsSwitch
import com.alorma.settings.composables.internal.SettingsToolbar

@Composable
fun SettingsList(
    title: @Composable () -> Unit,
    onBack: (() -> Unit)? = null,
    onSearch: (() -> Unit)? = null,
    onHelp: (() -> Unit)? = null,
    settings: @Composable ColumnScope.() -> Unit,
) {
    SettingsList(
        header = {
            SettingsToolbar(
                title = title,
                onBack = onBack,
                onSearch = onSearch,
                onHelp = onHelp,
            )
        },
        settings = settings,
    )
}


@Composable
fun SettingsList(
    header: (@Composable () -> Unit)? = null,
    settings: @Composable ColumnScope.() -> Unit,
) {
    Scaffold(
        topBar = {
            if (header != null) {
                header()
            }
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
                    icon = { Icon(imageVector = Icons.Default.Wifi, contentDescription = "Wifi") },
                    title = { Text(text = "Demo 1") },
                    checked = false
                ) {}
                Divider()
                SettingsCheckbox(
                    icon = {
                        Icon(imageVector = Icons.Default.VerifiedUser,
                            contentDescription = "User")
                    },
                    title = { Text(text = "Demo 2") },
                    subtitle = { Text(text = "This a subtitle") },
                    checked = true
                ) {}
            }
            SettingsGroup(
                title = { Text(text = "Group 2") },
            ) {
                SettingsMenuLink(
                    icon = { Icon(imageVector = Icons.Default.Wifi, contentDescription = "Wifi") },
                    title = { Text(text = "Demo 3") },
                    onClick = {},
                )
                Divider()
                SettingsMenuLink(
                    icon = { Icon(imageVector = Icons.Default.Wifi, contentDescription = "Wifi") },
                    title = { Text(text = "Demo 4") },
                    subtitle = { Text(text = "This a subtitle") },
                ) {}
            }
        }
    }
}

