package com.alorma.compose.settings.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.alorma.compose.settings.example.ui.Navigation
import com.alorma.compose.settings.example.ui.screens.AppSettingsScreen
import com.alorma.compose.settings.example.ui.screens.CheckboxesScreen
import com.alorma.compose.settings.example.ui.screens.ListScreen
import com.alorma.compose.settings.example.ui.screens.MenuLinksScreen
import com.alorma.compose.settings.example.ui.screens.SlidersScreen
import com.alorma.compose.settings.example.ui.screens.SwitchesScreen
import com.alorma.compose.settings.example.ui.screens.TopLevelScreen
import com.alorma.compose.settings.example.ui.theme.ComposeSettingsTheme
import com.alorma.compose.settings.storage.preferences.rememberPreferenceBooleanSettingState

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val darkThemePreference = rememberPreferenceBooleanSettingState(
                key = "darkThemePreference",
                defaultValue = true
            )

            val dynamicThemePreference = rememberPreferenceBooleanSettingState(
                key = "dynamicThemePreference",
                defaultValue = true
            )

            val navController = rememberNavController()
            ComposeSettingsTheme(
                darkThemePreference = darkThemePreference.value,
                dynamicThemePreference = dynamicThemePreference.value
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(color = MaterialTheme.colorScheme.background)
                ) {
                    NavHost(
                        navController = navController,
                        startDestination = Navigation.NAV_TOP_SETTINGS.first
                    ) {
                        composable(Navigation.NAV_TOP_SETTINGS.first) {
                            TopLevelScreen(navController = navController)
                        }
                        composable(Navigation.NAV_MENU_LINKS.first) {
                            MenuLinksScreen(navController = navController)
                        }
                        composable(Navigation.NAV_SWITCHES.first) {
                            SwitchesScreen(navController = navController)
                        }
                        composable(Navigation.NAV_CHECKBOXES.first) {
                            CheckboxesScreen(navController = navController)
                        }
                        composable(Navigation.NAV_SLIDERS.first) {
                            SlidersScreen(navController = navController)
                        }
                        composable(Navigation.NAV_LIST.first) {
                            ListScreen(navController = navController)
                        }
                        composable(Navigation.NAV_SETTINGS.first) {
                            AppSettingsScreen(
                                navController = navController,
                                darkThemePreference = darkThemePreference,
                                dynamicThemePreference = dynamicThemePreference
                            )
                        }
                    }
                }
            }
        }
    }
}
