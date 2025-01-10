package com.alorma.compose.settings.sample.shared

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SampleApp(modifier: Modifier = Modifier) {
  Scaffold(
    modifier = modifier,
    topBar = { TopAppBar(title = { Text(text = "Compose settings") }) },
  ) { paddingValues ->
    Column(
      modifier = Modifier
        .consumeWindowInsets(paddingValues)
        .padding(top = paddingValues.calculateTopPadding()),
    ) {
      Card(modifier = Modifier.padding(24.dp)) {
        Column(modifier = Modifier.padding(24.dp)) {
          Text(text = "Hello world")
          Button(onClick = {}) {
            Text(text = "Button")
          }
        }
      }
    }
  }
}
