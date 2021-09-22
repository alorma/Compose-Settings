package com.alorma.settingslib

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.alorma.settingslib.ui.Navigation
import com.alorma.settingslib.ui.screens.CheckboxesScreen
import com.alorma.settingslib.ui.screens.MenuLinksScreen
import com.alorma.settingslib.ui.screens.SlidersScreen
import com.alorma.settingslib.ui.screens.SwitchesScreen
import com.alorma.settingslib.ui.screens.TopLevelScreen
import com.alorma.settingslib.ui.theme.SettingsLibTheme

class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      val navController = rememberNavController()
      SettingsLibTheme {
        NavHost(
          navController = navController,
          startDestination = Navigation.NAV_TOP_SETTINGS.first,
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
        }
      }
    }
  }
}
