package com.alorma.settings.composables.datastore

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.DrawerDefaults
import androidx.compose.material.FabPosition
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.SnackbarHost
import androidx.compose.material.SnackbarHostState
import androidx.compose.material.Text
import androidx.compose.material.contentColorFor
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.Dp
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import com.alorma.settings.composables.SettingsCheckbox
import com.alorma.settings.composables.SettingsSwitch
import kotlinx.coroutines.launch

@Composable
fun SettingsScaffold(
    modifier: Modifier = Modifier,
    scaffoldState: ScaffoldState = rememberScaffoldState(),
    topBar: @Composable () -> Unit = {},
    bottomBar: @Composable () -> Unit = {},
    snackbarHost: @Composable (SnackbarHostState) -> Unit = { SnackbarHost(it) },
    floatingActionButton: @Composable () -> Unit = {},
    floatingActionButtonPosition: FabPosition = FabPosition.End,
    isFloatingActionButtonDocked: Boolean = false,
    drawerContent: @Composable() (ColumnScope.() -> Unit)? = null,
    drawerGesturesEnabled: Boolean = true,
    drawerShape: Shape = MaterialTheme.shapes.large,
    drawerElevation: Dp = DrawerDefaults.Elevation,
    drawerBackgroundColor: Color = MaterialTheme.colors.surface,
    drawerContentColor: Color = contentColorFor(drawerBackgroundColor),
    drawerScrimColor: Color = DrawerDefaults.scrimColor,
    backgroundColor: Color = MaterialTheme.colors.background,
    contentColor: Color = contentColorFor(backgroundColor),
    booleanRoleMapper: (Preferences.Key<Boolean>) -> Role = { Role.Switch },
    preferenceTitleMapper: (Preferences.Key<Boolean>) -> String,
    dataStore: DataStore<Preferences>,
) {
    Scaffold(
        modifier = modifier,
        scaffoldState = scaffoldState,
        topBar = topBar,
        bottomBar = bottomBar,
        snackbarHost = snackbarHost,
        floatingActionButton = floatingActionButton,
        floatingActionButtonPosition = floatingActionButtonPosition,
        isFloatingActionButtonDocked = isFloatingActionButtonDocked,
        drawerContent = drawerContent,
        drawerGesturesEnabled = drawerGesturesEnabled,
        drawerShape = drawerShape,
        drawerElevation = drawerElevation,
        drawerBackgroundColor = drawerBackgroundColor,
        drawerContentColor = drawerContentColor,
        drawerScrimColor = drawerScrimColor,
        backgroundColor = backgroundColor,
        contentColor = contentColor,
    ) {
        val preferencesState by dataStore.data.collectAsState(initial = null)

        val preferencesMap = preferencesState?.asMap().orEmpty().filterValues { value ->
            value is Boolean
        }

        val coroutineScope = rememberCoroutineScope()

        LazyColumn {
            items(
                items = preferencesMap.keys.toList(),
                key = { prefKey -> prefKey.name }
            ) { prefKey ->
                val booleanKey = booleanPreferencesKey(prefKey.name)
                val value = (preferencesMap[booleanKey] ?: false) as Boolean

                val booleanRole = booleanRoleMapper(booleanKey)

                if (booleanRole == Role.Checkbox) {
                    DataStoreCheckbox(
                        title = preferenceTitleMapper(booleanKey),
                        value = value,
                        onChange = { newValue ->
                            coroutineScope.launch {
                                dataStore.edit { preferences ->
                                    preferences[booleanKey] = newValue
                                }
                            }
                        },
                    )
                } else if (booleanRole == Role.Switch) {
                    DataStoreSwitch(
                        title = preferenceTitleMapper(booleanKey),
                        value = value,
                        onChange = { newValue ->
                            coroutineScope.launch {
                                dataStore.edit { preferences ->
                                    preferences[booleanKey] = newValue
                                }
                            }
                        },
                    )
                }
            }
        }
    }
}

@Composable
internal fun DataStoreCheckbox(
    title: String,
    value: Boolean,
    onChange: (Boolean) -> Unit,
) {
    SettingsCheckbox(
        title = { Text(text = title) },
        checked = value,
        onCheckedChange = onChange
    )
}

@Composable
internal fun DataStoreSwitch(
    title: String,
    value: Boolean,
    onChange: (Boolean) -> Unit,
) {
    SettingsSwitch(
        title = { Text(text = title) },
        checked = value,
        onCheckedChange = onChange
    )
}
