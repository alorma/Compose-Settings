package com.alorma.compose.settings.example.demo

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsToolbar(
  title: @Composable () -> Unit,
  onBack: (() -> Unit)? = null,
  scrollBehavior: TopAppBarScrollBehavior,
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
    scrollBehavior = scrollBehavior
  )
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun SearchToolbarPreview() {
  MaterialTheme {
    SettingsToolbar(
      title = { Text(text = "Title") },
      onBack = {},
      scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(),
    )
  }
}