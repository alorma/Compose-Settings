package com.alorma.compose.settings.example.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.alorma.compose.settings.example.demo.AppScaffold
import com.alorma.compose.settings.example.ui.Navigation
import com.alorma.compose.settings.storage.base.SettingValueState
import com.alorma.compose.settings.ui.SettingsSwitch

@Composable
fun AppSettingsScreen(
    navController: NavHostController,
    darkThemePreference: SettingValueState<Boolean>
) {
    AppScaffold(
        navController = navController,
        showSettings = false,
        title = { Text(text = Navigation.NAV_TOP_SETTINGS.second) }
    ) {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            SettingsSwitch(
                state = darkThemePreference,
                title = { Text(text = "Dark theme") },
                subtitle = { Text(text = "Change between dark and light") },
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}
