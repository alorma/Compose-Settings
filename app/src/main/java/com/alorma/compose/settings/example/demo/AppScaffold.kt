package com.alorma.compose.settings.example.demo

import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.consumedWindowInsets
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun AppScaffold(
  title: @Composable (() -> Unit)? = null,
  snackbarHostState: SnackbarHostState = remember { SnackbarHostState() },
  onBack: (() -> Unit)? = null,
  onSearch: (() -> Unit)? = null,
  content: @Composable (ColumnScope.() -> Unit),
) {
  Scaffold(
    topBar = {
      if (title != null) {
        SettingsToolbar(
          title = title,
          onBack = onBack,
          onSearch = onSearch,
        )
      }
    },
    snackbarHost = {
      SnackbarHost(hostState = snackbarHostState)
    }
  ) { innerPadding ->
    Column(
      modifier = Modifier
        .consumedWindowInsets(innerPadding)
        .scrollable(
          state = rememberScrollState(),
          orientation = Orientation.Vertical,
        ),
      content = content
    )
  }
}