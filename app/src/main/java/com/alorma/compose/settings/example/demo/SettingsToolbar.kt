package com.alorma.compose.settings.example.demo

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsToolbar(
  title: @Composable () -> Unit,
  onBack: (() -> Unit)? = null,
) {
  TopAppBar(
    title = title,
    navigationIcon = {
      if (onBack != null) {
        IconButton(onClick = { onBack.invoke() }) {
          Icon(
            imageVector = Icons.Default.ArrowBack,
            contentDescription = null,
          )
        }
      }
    },
  )
}

@Preview
@Composable
fun SearchToolbarPreview() {
  MaterialTheme {
    SettingsToolbar(
      title = { Text(text = "Title") },
      onBack = {},
    )
  }
}