package com.alorma.compose.settings.example.demo

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.consumeWindowInsets
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
import androidx.navigation.NavController
import com.alorma.compose.settings.example.ui.Navigation
import com.alorma.compose.settings.storage.base.SettingValueState
import com.alorma.compose.settings.storage.base.rememberBooleanSettingState

@OptIn(
  ExperimentalMaterial3Api::class,
  ExperimentalLayoutApi::class,
)
@Composable
fun AppScaffold(
  navController: NavController,
  enabledState: SettingValueState<Boolean> = rememberBooleanSettingState(true),
  showSettings: Boolean = true,
  onBack: (() -> Unit)? = { navController.popBackStack() },
  title: @Composable (() -> Unit)? = null,
  snackbarHostState: SnackbarHostState = remember { SnackbarHostState() },
  content: @Composable (ColumnScope.() -> Unit),
) {
  val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()
  Scaffold(
    modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
    topBar = {
      if (title != null) {
        SettingsToolbar(
          title = title,
          enabledState = enabledState.value,
          onEnabledChange = { enabledState.value = it },
          showSettings = showSettings,
          onBack = onBack,
          onNavigateSettings = { navController.navigate(route = Navigation.NAV_SETTINGS.first) },
          scrollBehavior = scrollBehavior,
        )
      }
    },
    snackbarHost = {
      SnackbarHost(hostState = snackbarHostState)
    },
  ) { innerPadding ->
    Column(
      modifier = Modifier
          .consumeWindowInsets(innerPadding)
          .fillMaxSize()
          .padding(top = innerPadding.calculateTopPadding()),
      content = content,
    )
  }
}
