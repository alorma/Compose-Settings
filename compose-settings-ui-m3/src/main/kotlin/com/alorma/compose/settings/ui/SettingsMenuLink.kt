package com.alorma.compose.settings.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.alorma.compose.settings.ui.internal.SettingsTileScaffold

@Composable
fun SettingsMenuLink(
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    icon: (@Composable () -> Unit)? = null,
    title: @Composable () -> Unit,
    subtitle: (@Composable () -> Unit)? = null,
    action: (@Composable (Boolean) -> Unit)? = null,
    onClick: () -> Unit,
) {
    Surface {
        Row(
            modifier = modifier.fillMaxWidth()
                .clickable(
                    enabled = enabled,
                    onClick = onClick,
                ),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            SettingsTileScaffold(
                title = title,
                enabled = enabled,
                subtitle = subtitle,
                icon = icon,
                action = action,
                actionDivider = true,
            )
        }
    }
}

@Preview
@Composable
internal fun SettingsMenuLinkPreview() {
    MaterialTheme {
        SettingsMenuLink(
            icon = { Icon(imageVector = Icons.Default.Clear, contentDescription = "Clear") },
            title = { Text(text = "Hello") },
            subtitle = { Text(text = "This is a longer text") },
        ) {
        }
    }
}

@Preview
@Composable
internal fun SettingsMenuLinkActionPreview() {
    var rememberCheckBoxState by remember { mutableStateOf(true) }
    MaterialTheme {
        SettingsMenuLink(
            icon = { Icon(imageVector = Icons.Default.Clear, contentDescription = "Clear") },
            title = { Text(text = "Hello") },
            subtitle = { Text(text = "This is a longer text") },
            action = {
                Checkbox(checked = rememberCheckBoxState, onCheckedChange = { newState ->
                    rememberCheckBoxState = newState
                })
            },
        ) {
        }
    }
}
