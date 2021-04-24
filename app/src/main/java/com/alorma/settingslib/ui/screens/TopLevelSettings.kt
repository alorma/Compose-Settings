package com.alorma.settingslib.ui.screens

import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckBox
import androidx.compose.material.icons.filled.SwitchLeft
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.alorma.settings.SettingsList
import com.alorma.settings.composables.SettingsMenuLink
import com.alorma.settingslib.extensions.popOrFinish

@Composable
fun TopLevelSettings(
    navController: NavController = rememberNavController(),
) {
    val context = LocalContext.current
    SettingsList(
        title = { Text(text = "Demo settings") },
        onBack = { navController.popOrFinish(context = context) },
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