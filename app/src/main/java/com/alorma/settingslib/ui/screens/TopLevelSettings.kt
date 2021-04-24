package com.alorma.settingslib.ui.screens

import android.widget.Toast
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckBox
import androidx.compose.material.icons.filled.SwitchLeft
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.alorma.settings.SettingsList
import com.alorma.settings.composables.SettingsMenuLink

@Composable
fun TopLevelSettings() {
    val context = LocalContext.current
    SettingsList(
        title = { Text(text = "Demo settings") },
        onBack = { TODO() },
    ) {
        SettingsMenuLink(
            icon = {
                Icon(
                    imageVector = Icons.Default.SwitchLeft,
                    contentDescription = "Switches",
                )
            },
            title = { Text(text = "Switches") },
            onClick = {

            },
        )
        Divider()
        SettingsMenuLink(
            icon = {
                Icon(
                    imageVector = Icons.Default.CheckBox,
                    contentDescription = "Checkboxes",
                )
            },
            title = { Text(text = "Demo 4") },
        ) {

        }
    }
}