package com.alorma.compose.settings.example.demo

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.consumedWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll

@OptIn(
  ExperimentalMaterial3Api::class,
  ExperimentalLayoutApi::class,
)
@Composable
fun AppScaffold(
  title: @Composable (() -> Unit)? = null,
  snackbarHostState: SnackbarHostState = remember { SnackbarHostState() },
  onBack: (() -> Unit)? = null,
  content: @Composable (ColumnScope.() -> Unit),
) {
  val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()
  Scaffold(
    modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
    topBar = {
      if (title != null) {
        SettingsToolbar(
          title = title,
          onBack = onBack,
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
        .fillMaxSize()
        .padding(top = innerPadding.calculateTopPadding()),
      content = content,
    )
  }
}