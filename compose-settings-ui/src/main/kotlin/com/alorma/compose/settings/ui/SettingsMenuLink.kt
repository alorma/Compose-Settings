package com.alorma.compose.settings.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.alorma.compose.settings.ui.internal.SettingsTileAction
import com.alorma.compose.settings.ui.internal.SettingsTileIcon
import com.alorma.compose.settings.ui.internal.SettingsTileTexts

@Composable
fun SettingsMenuLink(
    modifier: Modifier = Modifier,
    icon: (@Composable () -> Unit)? = null,
    title: @Composable () -> Unit,
    subtitle: (@Composable () -> Unit)? = null,
    action: (@Composable () -> Unit)? = null,
    onClick: () -> Unit,
) {
    Surface {
        Row(
            modifier = modifier.fillMaxWidth(),
        ) {
            Row(
                modifier = Modifier
                    .weight(1f)
                    .clickable(onClick = onClick),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                SettingsTileIcon(icon = icon)
                SettingsTileTexts(title = title, subtitle = subtitle)
            }
            if (action != null) {
                Divider(
                    modifier = Modifier
                        .padding(vertical = 4.dp)
                        .height(56.dp)
                        .width(1.dp),
                )
                SettingsTileAction {
                    action.invoke()
                }
            }
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