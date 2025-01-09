package com.alorma.compose.settings.sample.shared

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SampleApp(modifier: Modifier = Modifier) {
  Scaffold(
    modifier = modifier,
    topBar = { TopAppBar(title = { Text(text = "Compose settings") }) },
  ) {
    Text(text = "Hello world")
  }
}