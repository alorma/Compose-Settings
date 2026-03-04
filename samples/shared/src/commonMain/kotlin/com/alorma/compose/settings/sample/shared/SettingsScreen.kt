package com.alorma.compose.settings.sample.shared

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AssistChip
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.PrimaryTabRow
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

enum class Material3Mode {
  STANDARD,
  EXPRESSIVE
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(modifier: Modifier = Modifier) {
  Scaffold(
    modifier = modifier,
    topBar = {
      TopAppBar(
        title = { Text(text = "Compose Settings") },
        actions = {
          Row(modifier = Modifier.padding(horizontal = 16.dp)) {
            AssistChip(
              modifier = Modifier.padding(start = 8.dp),
              label = { Text(text = Version.LIB_VERSION) },
              onClick = {},
            )
          }
        },
      )
    },
  ) { padding ->
    Column(
      modifier = Modifier
        .consumeWindowInsets(padding)
        .padding(top = padding.calculateTopPadding()),
    ) {
      val materialMode = remember {
        mutableStateOf(Material3Mode.EXPRESSIVE)
      }

      PrimaryTabRow(
        selectedTabIndex = Material3Mode.entries.indexOf(materialMode.value),
        divider = {},
        tabs = {
          Tab(
            selected = materialMode.value == Material3Mode.STANDARD,
            text = { Text(text = "Standard") },
            onClick = { materialMode.value = Material3Mode.STANDARD },
          )
          Tab(
            selected = materialMode.value == Material3Mode.EXPRESSIVE,
            text = { Text(text = "Expressive") },
            onClick = { materialMode.value = Material3Mode.EXPRESSIVE },
          )
        },
      )

      when (materialMode.value) {
        Material3Mode.STANDARD -> StandardMaterial3Samples()
        Material3Mode.EXPRESSIVE -> ExpressiveMaterial3Samples()
      }
    }
  }
}
