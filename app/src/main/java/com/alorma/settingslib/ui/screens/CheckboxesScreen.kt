package com.alorma.settingslib.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.SortByAlpha
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.alorma.settings.composables.SettingsCheckbox
import com.alorma.settings.storage.preferences.rememberPreferenceBooleanStorage
import com.alorma.settingslib.demo.AppScaffold
import com.alorma.settingslib.extensions.showSnackbar
import kotlinx.coroutines.launch
import kotlin.random.Random

@Composable
fun CheckboxesScreen(navController: NavHostController) {
  val coroutineScope = rememberCoroutineScope()

  val scaffoldState = rememberScaffoldState()

  AppScaffold(
    scaffoldState = scaffoldState,
    title = { Text(text = "Checkboxes") },
    onBack = { navController.popBackStack() },
  ) {
    Box(
      modifier = Modifier
          .heightIn(100.dp)
          .padding(16.dp),
      contentAlignment = Alignment.CenterStart,
    ) {
      Text(
        text = "Storage style",
        style = MaterialTheme.typography.subtitle1,
      )
    }
    val storage = rememberPreferenceBooleanStorage()
    SettingsCheckbox(
      key = "Ccheckbox1",
      storage = storage,
      title = { Text(text = "Menu 1") },
      subtitle = { Text(text = "Subtitle of menu 1") },
      icon = {
        Icon(
          imageVector = Icons.Default.SortByAlpha,
          contentDescription = "Menu 1"
        )
      },
    )
    Divider()
    Box(
      modifier = Modifier
          .heightIn(100.dp)
          .padding(16.dp),
      contentAlignment = Alignment.CenterStart,
    ) {
      Text(
        text = "Lambda style",
        style = MaterialTheme.typography.subtitle1,
      )
    }
    Divider()
    var check1 by remember { mutableStateOf(Random.nextBoolean()) }
    SettingsCheckbox(
      title = { Text(text = "Menu 1") },
      subtitle = { Text(text = "Subtitle of menu 1") },
      icon = {
        Icon(
          imageVector = Icons.Default.SortByAlpha,
          contentDescription = "Menu 1"
        )
      },
      checked = check1,
    ) { changed ->
      coroutineScope.launch {
        check1 = changed
        scaffoldState.showSnackbar(message = "Switch changed to:  $changed")
      }
    }
  }
}