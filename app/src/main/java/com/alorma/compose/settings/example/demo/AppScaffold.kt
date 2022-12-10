package com.alorma.compose.settings.example.demo

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
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
          scrollBehavior = scrollBehavior
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