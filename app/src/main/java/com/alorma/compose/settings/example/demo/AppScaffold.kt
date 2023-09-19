package com.alorma.compose.settings.example.demo

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.consumedWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.SnackbarHost
import androidx.compose.material.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.alorma.compose.settings.example.ui.Navigation
import com.alorma.compose.settings.storage.base.SettingValueState
import com.alorma.compose.settings.storage.base.rememberBooleanSettingState

@OptIn(
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
    Scaffold(
        topBar = {
            if (title != null) {
                SettingsToolbar(
                    title = title,
                    enabledState = enabledState.value,
                    onEnabledChange = { enabledState.value = it },
                    showSettings = showSettings,
                    onBack = onBack,
                    onNavigateSettings = { navController.navigate(route = Navigation.NAV_SETTINGS.first) },
                )
            }
        },
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        },
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
