package com.alorma.compose.settings.ui

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.selection.toggleable
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Switch
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.tooling.preview.Preview
import com.alorma.compose.settings.storage.base.*
import com.alorma.compose.settings.ui.internal.SettingsTileAction
import com.alorma.compose.settings.ui.internal.SettingsTileIcon
import com.alorma.compose.settings.ui.internal.SettingsTileTexts

@Composable
fun SettingsSwitch(
    modifier: Modifier = Modifier,
    state: SettingValueState<Boolean> = rememberBooleanSettingState(),
    icon: @Composable (() -> Unit)? = null,
    title: @Composable () -> Unit,
    subtitle: @Composable (() -> Unit)? = null,
    onCheckedChange: (Boolean) -> Unit = {},
) {
    var storageValue by state
    val update: (Boolean) -> Unit = { boolean ->
        storageValue = boolean
        onCheckedChange(storageValue)
    }
    Surface {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .toggleable(
                    value = storageValue,
                    role = Role.Switch,
                    onValueChange = { update(!storageValue) }
                ),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            SettingsTileIcon(icon = icon)
            SettingsTileTexts(title = title, subtitle = subtitle)
            SettingsTileAction {
                Switch(
                    checked = storageValue,
                    onCheckedChange = update
                )
            }
        }
    }
}

@Composable
fun<TMarker : SettingsSnapshotMarker> AsyncSettingsItemScope<TMarker, *, *>.SettingsSwitch(
    modifier: Modifier = Modifier,
    key: SettingsSnapshot.Key<TMarker, Boolean>,
    icon: @Composable (() -> Unit)? = null,
    title: @Composable () -> Unit,
    subtitle: @Composable (() -> Unit)? = null,
    onCheckedChange: (Boolean) -> Unit = {},
) {
    val storageValue by getValueFlow(key).collectAsState(initial = key.defaultValue)

    // When an user clicks on the switch, it's expected that the change will be observed instantly.
    // As 'update' is async operation, storageValue won't be updated instantly.
    // visualValue fixes that issue. It mirrors storageValue and allows the change to be observed instantly.
    var visualValue by remember(storageValue) {
        mutableStateOf(storageValue)
    }

    val updateHandler by remember(key) {
        derivedStateOf { updateHandlerFor(key) }
    }

    val update: (Boolean) -> Unit = { newValue ->
        visualValue = newValue
        updateHandler.update(newValue)
        onCheckedChange(newValue)
    }

    Surface {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .toggleable(
                    value = visualValue,
                    role = Role.Switch,
                    onValueChange = { update(!visualValue) }
                ),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            SettingsTileIcon(icon = icon)
            SettingsTileTexts(title = title, subtitle = subtitle)
            SettingsTileAction {
                Switch(
                    checked = visualValue,
                    onCheckedChange = update
                )
            }
        }
    }
}

@Preview
@Composable
internal fun SettingsSwitchPreview() {
    MaterialTheme {
        val storage = rememberBooleanSettingState(defaultValue = true)
        SettingsSwitch(
            state = storage,
            icon = { Icon(imageVector = Icons.Default.Clear, contentDescription = "Clear") },
            title = { Text(text = "Hello") },
            subtitle = { Text(text = "This is a longer text") },
            onCheckedChange = { }
        )
    }
}