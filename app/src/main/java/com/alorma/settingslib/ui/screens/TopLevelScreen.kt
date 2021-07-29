package com.alorma.settingslib.ui.screens

import android.content.Context
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.semantics.Role
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.alorma.settings.composables.datastore.SettingsScaffold
import com.alorma.settings.composables.internal.SettingsToolbar

val Context.datastore by preferencesDataStore(name = "TopLevel")

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun TopLevelScreen(
    navController: NavController = rememberNavController(),
) {
    val usedPrefs = listOf(
        Triple(booleanPreferencesKey("clock_enabled"), "Enable clock", Role.Checkbox),
        Triple(booleanPreferencesKey("sync_enabled"), "Enable sync", Role.Switch),
    )
    val scaffoldState = rememberScaffoldState()

    val context = LocalContext.current

    LaunchedEffect(key1 = usedPrefs) {
        context.datastore.edit { preferences ->
            usedPrefs.toList()
                .map { it.first }
                .forEach { key ->
                    if (!preferences.contains(key)) {
                        preferences[key] = false
                    }
                }
        }
    }

    SettingsScaffold(
        scaffoldState = scaffoldState,
        topBar = {
            SettingsToolbar(
                title = { Text(text = "Demo settings") },
            )
        },
        dataStore = context.datastore,
        booleanRoleMapper = { prefKey ->
            usedPrefs.first { it.first == prefKey }.third
        },
        preferenceTitleMapper = { prefKey ->
            usedPrefs.first { it.first == prefKey }.second
        }
    )
}