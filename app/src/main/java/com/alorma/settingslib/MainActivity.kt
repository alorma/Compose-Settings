package com.alorma.settingslib

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.alorma.settingslib.ui.Navigation
import com.alorma.settingslib.ui.screens.TopLevelSettings
import com.alorma.settingslib.ui.theme.SettingsLibTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            SettingsLibTheme {
                NavHost(
                    navController = navController,
                    startDestination = Navigation.NAV_TOP_SETTINGS,
                ) {
                    composable(Navigation.NAV_TOP_SETTINGS) {
                        TopLevelSettings(navController = navController)
                    }
                }

            }
        }
    }
}