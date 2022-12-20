package com.alorma.compose.settings.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.alorma.compose.settings.example.ui.Navigation
import com.alorma.compose.settings.example.ui.screens.CheckboxesScreen
import com.alorma.compose.settings.example.ui.screens.ListScreen
import com.alorma.compose.settings.example.ui.screens.MenuLinksScreen
import com.alorma.compose.settings.example.ui.screens.ProtoScreen
import com.alorma.compose.settings.example.ui.screens.SlidersScreen
import com.alorma.compose.settings.example.ui.screens.SwitchesScreen
import com.alorma.compose.settings.example.ui.screens.TopLevelScreen
import com.alorma.compose.settings.example.ui.theme.ComposeSettingsTheme
import com.alorma.compose.settings.storage.preferences.rememberPreferenceBooleanSettingState
import com.alorma.compose.settings.ui.SettingsSwitch

class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {

      val darkThemePreference = rememberPreferenceBooleanSettingState(
        key = "darkThemePreference",
        defaultValue = true,
      )

      val dynamicThemePreference = rememberPreferenceBooleanSettingState(
        key = "dynamicThemePreference",
        defaultValue = true,
      )

      val navController = rememberNavController()
      ComposeSettingsTheme(
        darkThemePreference = darkThemePreference.value,
        dynamicThemePreference = dynamicThemePreference.value
      ) {
        Column(
          modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background),
        ) {
          Card(
            modifier = Modifier
              .fillMaxWidth()
              .padding(8.dp),
            elevation = CardDefaults.elevatedCardElevation(),
          ) {
            Column(
              modifier = Modifier.fillMaxWidth(),
            ) {
              SettingsSwitch(
                state = darkThemePreference,
                title = { Text(text = "Dark theme") },
                subtitle = { Text(text = "Change between dark and light") },
                modifier = Modifier.fillMaxWidth(),
              )
              SettingsSwitch(
                state = dynamicThemePreference,
                title = { Text(text = "Dynamic theme") },
                subtitle = { Text(text = "Dynamic theme based on wallpaper") },
                modifier = Modifier.fillMaxWidth(),
              )
            }
          }
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
            composable(Navigation.NAV_LIST.first) {
              ListScreen(navController = navController)
            }
            composable(Navigation.NAV_PROTO_CLASS.first) {
              ProtoScreen(navController = navController)
            }
          }
        }
      }
    }
  }
}
