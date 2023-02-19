package com.alorma.compose.settings.example.ui.screens

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.alorma.compose.settings.example.demo.AppScaffold
import com.alorma.compose.settings.example.ui.Navigation
import com.alorma.compose.settings.storage.base.rememberBooleanSettingState
import com.alorma.compose.settings.ui.SettingsMenuLink

@Composable
fun TopLevelScreen(
  navController: NavController = rememberNavController(),
) {
  val navigations = listOf(
    Navigation.NAV_MENU_LINKS,
    Navigation.NAV_SWITCHES,
    Navigation.NAV_CHECKBOXES,
    Navigation.NAV_SLIDERS,
    Navigation.NAV_LIST,
  )
  val enabledState = rememberBooleanSettingState(true)
  AppScaffold(
    navController = navController,
    enabledState = enabledState,
    onBack = null,
    title = { Text(text = Navigation.NAV_TOP_SETTINGS.second) },
  ) {
    LazyColumn(
      modifier = Modifier.fillMaxWidth(),
    ) {
      itemsIndexed(navigations) { index, nav ->
        SettingsMenuLink(
          enabled = enabledState.value,
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