package com.alorma.compose.settings.sample.shared

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.AssistChip
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Scaffold
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
  val materialMode = remember { mutableStateOf(Material3Mode.STANDARD) }

  Scaffold(
    modifier = modifier,
    topBar = {
      TopAppBar(
        title = { Text(text = "Compose Settings") },
        actions = {
          Row(modifier = Modifier.padding(horizontal = 16.dp)) {
            FilterChip(
              selected = materialMode.value == Material3Mode.STANDARD,
              onClick = { materialMode.value = Material3Mode.STANDARD },
              label = { Text("M3") },
            )
            FilterChip(
              selected = materialMode.value == Material3Mode.EXPRESSIVE,
              onClick = { materialMode.value = Material3Mode.EXPRESSIVE },
              label = { Text("M3 Expressive") },
              modifier = Modifier.padding(start = 8.dp),
            )
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
    Box(
      modifier = Modifier
        .consumeWindowInsets(padding)
        .padding(top = padding.calculateTopPadding()),
    ) {
      when (materialMode.value) {
        Material3Mode.STANDARD -> StandardMaterial3Samples()
        Material3Mode.EXPRESSIVE -> ExpressiveMaterial3Samples()
      }
    }
  }
}
