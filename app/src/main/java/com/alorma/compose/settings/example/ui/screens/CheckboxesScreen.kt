package com.alorma.compose.settings.example.ui.screens

import androidx.compose.material.Icon
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.SortByAlpha
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavHostController
import com.alorma.compose.settings.example.demo.AppScaffold
import com.alorma.compose.settings.storage.base.SettingValueState
import com.alorma.compose.settings.storage.base.rememberBooleanSettingState
import com.alorma.compose.settings.storage.preferences.rememberPreferenceBooleanSettingState
import com.alorma.compose.settings.ui.SettingsCheckbox
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun CheckboxesScreen(navController: NavHostController) {
    val coroutineScope = rememberCoroutineScope()

    val scaffoldState = rememberScaffoldState()

    AppScaffold(
        scaffoldState = scaffoldState,
        title = { Text(text = "Checkboxes") },
        onBack = { navController.popBackStack() },
    ) {
        val memoryStorage = rememberBooleanSettingState(defaultValue = false)
        SettingsCheckbox(
            state = memoryStorage,
            icon = {
                Icon(
                    imageVector = Icons.Default.SortByAlpha,
                    contentDescription = "Memory switch 1"
                )
            },
            title = { Text(text = "Memory") },
            onCheckedChange = {
                scaffoldState.showChange(
                    coroutineScope = coroutineScope,
                    key = "Memory",
                    state = memoryStorage
                )
            },
        )
        val preferenceStorage = rememberPreferenceBooleanSettingState(
            key = "switch_2",
            defaultValue = false,
        )
        SettingsCheckbox(
            state = preferenceStorage,
            icon = {
                Icon(
                    imageVector = Icons.Default.SortByAlpha,
                    contentDescription = "Preferences switch 1"
                )
            },
            title = { Text(text = "Preferences") },
            onCheckedChange = {
                scaffoldState.showChange(
                    coroutineScope = coroutineScope,
                    key = "Preferences",
                    state = preferenceStorage,
                )
            },
        )
    }
}

private fun ScaffoldState.showChange(
    coroutineScope: CoroutineScope,
    key: String,
    state: SettingValueState<Boolean>
) {
    coroutineScope.launch {
        snackbarHostState.currentSnackbarData?.dismiss()
        snackbarHostState.showSnackbar(message = "[$key]:  ${state.value}")
    }
}