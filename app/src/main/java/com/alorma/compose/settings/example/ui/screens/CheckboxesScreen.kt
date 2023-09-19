package com.alorma.compose.settings.example.ui.screens

import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.SnackbarHostState
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.SortByAlpha
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
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
    val snackbarHostState = remember { SnackbarHostState() }
    val enabledState = rememberBooleanSettingState(true)

    AppScaffold(
        enabledState = enabledState,
        navController = navController,
        title = { Text(text = "Checkboxes") },
        snackbarHostState = snackbarHostState,
    ) {
        val memoryStorage = rememberBooleanSettingState(defaultValue = false)
        SettingsCheckbox(
            enabled = enabledState.value,
            state = memoryStorage,
            icon = {
                Icon(
                    imageVector = Icons.Default.SortByAlpha,
                    contentDescription = "Memory switch 1",
                )
            },
            title = { Text(text = "Memory") },
            onCheckedChange = {
                snackbarHostState.showChange(
                    coroutineScope = coroutineScope,
                    key = "Memory",
                    state = memoryStorage,
                )
            },
        )
        Divider()
        val preferenceStorage = rememberPreferenceBooleanSettingState(
            key = "switch_2",
            defaultValue = false,
        )
        SettingsCheckbox(
            enabled = enabledState.value,
            state = preferenceStorage,
            icon = {
                Icon(
                    imageVector = Icons.Default.SortByAlpha,
                    contentDescription = "Preferences switch 1",
                )
            },
            title = { Text(text = "Preferences") },
            onCheckedChange = {
                snackbarHostState.showChange(
                    coroutineScope = coroutineScope,
                    key = "Preferences",
                    state = preferenceStorage,
                )
            },
        )
        Divider()
        val preferenceDataStoreStorage = rememberPreferenceBooleanSettingState(
            key = "checkbox_dataStore_preference",
            defaultValue = false,
        )
        SettingsCheckbox(
            enabled = enabledState.value,
            state = preferenceDataStoreStorage,
            icon = {
                Icon(
                    imageVector = Icons.Default.SortByAlpha,
                    contentDescription = "Preferences switch 2",
                )
            },
            title = { Text(text = "PreferenceDataStore") },
            onCheckedChange = {
                snackbarHostState.showChange(
                    coroutineScope = coroutineScope,
                    key = "PreferenceDataStore",
                    state = preferenceDataStoreStorage,
                )
            },
        )
        Divider()
    }
}

private fun SnackbarHostState.showChange(
    coroutineScope: CoroutineScope,
    key: String,
    state: SettingValueState<Boolean>,
) {
    coroutineScope.launch {
        currentSnackbarData?.dismiss()
        showSnackbar(message = "[$key]:  ${state.value}")
    }
}
