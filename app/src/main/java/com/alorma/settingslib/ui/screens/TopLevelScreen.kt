package com.alorma.settingslib.ui.screens

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.alorma.settings.composables.SettingsMenuLink
import com.alorma.settingslib.demo.AppScaffold
import com.alorma.settingslib.ui.Navigation

@Composable
fun TopLevelScreen(
  navController: NavController = rememberNavController(),
) {
  val navigations = listOf(
    Navigation.NAV_MENU_LINKS,
    Navigation.NAV_SWITCHES,
    Navigation.NAV_CHECKBOXES,
    Navigation.NAV_SLIDERS,
  )
  AppScaffold(
    title = { Text(text = "Switches") },
    onBack = { navController.popBackStack() },
  ) {
    LazyColumn {
      itemsIndexed(navigations) { index, nav ->
        SettingsMenuLink(
          title = { Text(text = nav.second) },
          onClick = { navController.navigate(route = nav.first) },
        )
        if (index < navigations.size) {
          Divider()
        }
      }
    }
  }
}