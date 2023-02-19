package com.alorma.compose.settings.example.demo

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.consumedWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.navigation.NavController
import com.alorma.compose.settings.example.ui.Navigation
import com.alorma.compose.settings.storage.base.SettingValueState
import com.alorma.compose.settings.storage.base.rememberBooleanSettingState
import com.alorma.compose.settings.ui.SettingsSwitch
import kotlinx.coroutines.launch

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
  val coroutineScope = rememberCoroutineScope()

  val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()
  Scaffold(
    modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
    topBar = {
      if (title != null) {
        SettingsToolbar(
          title = title,
          showSettings = showSettings,
          onBack = onBack,
          onNavigateSettings = { navController.navigate(route = Navigation.NAV_SETTINGS.first) },
          scrollBehavior = scrollBehavior
        )
      }
    },
    snackbarHost = {
      SnackbarHost(hostState = snackbarHostState)
    }
  ) { innerPadding ->
    LaunchedEffect(key1 = enabledState) {
      coroutineScope.launch {
        val message = "Enabled: ${enabledState.value}"
        snackbarHostState.showSnackbar(message)
      }
    }

    Column(
      modifier = Modifier
        .consumedWindowInsets(innerPadding)
        .fillMaxSize()
        .padding(top = innerPadding.calculateTopPadding()),
      content = {
        SettingsSwitch(
          state = enabledState,
          title = { Text(text = "Enabled") },
        )
        Divider()
        content()
      },
    )
  }
}