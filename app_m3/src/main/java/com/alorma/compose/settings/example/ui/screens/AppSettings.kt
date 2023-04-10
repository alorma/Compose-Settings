package com.alorma.compose.settings.example.ui.screens

import android.os.Build
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.alorma.compose.settings.example.demo.AppScaffold
import com.alorma.compose.settings.example.ui.Navigation
import com.alorma.compose.settings.storage.preferences.BooleanPreferenceSettingValueState
import com.alorma.compose.settings.ui.SettingsSwitch

@Composable
fun AppSettingsScreen(
    navController: NavHostController,
    darkThemePreference: BooleanPreferenceSettingValueState,
    dynamicThemePreference: BooleanPreferenceSettingValueState
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

            // Dynamic theme is not supported on lower API levels.
            if (Build.VERSION.SDK_INT >= 31) {
                SettingsSwitch(
                    state = dynamicThemePreference,
                    title = { Text(text = "Dynamic theme") },
                    subtitle = { Text(text = "Dynamic theme based on wallpaper") },
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}
