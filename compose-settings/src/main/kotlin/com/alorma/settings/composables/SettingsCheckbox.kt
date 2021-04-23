package com.alorma.settings.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.Checkbox
import androidx.compose.material.ContentAlpha
import androidx.compose.material.Icon
import androidx.compose.material.LocalContentAlpha
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ProvideTextStyle
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Wifi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.alorma.settings.composables.internal.SettingsIcon

@Composable
fun SettingsCheckbox(
    icon: @Composable() (() -> Unit)? = null,
    title: @Composable() () -> Unit,
    subtitle: @Composable() (() -> Unit)? = null,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
) {

    var rememberCheckboxPref by remember {
        mutableStateOf(checked)
    }

    fun updateValue(newValue: Boolean) {
        onCheckedChange(newValue)
        rememberCheckboxPref = newValue
    }

    Surface {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable(
                    onClick = {
                        val newVal = !rememberCheckboxPref
                        updateValue(newValue = newVal)
                    },
                ),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            SettingsIcon(icon = icon)
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.Center,
            ) {
                ProvideTextStyle(value = MaterialTheme.typography.subtitle1) {
                    title()
                }
                if (subtitle != null) {
                    Spacer(modifier = Modifier.size(2.dp))
                    ProvideTextStyle(value = MaterialTheme.typography.caption) {
                        CompositionLocalProvider(
                            LocalContentAlpha provides ContentAlpha.medium,
                            content = subtitle
                        )
                    }
                }
            }
            Box(
                modifier = Modifier.size(64.dp),
                contentAlignment = Alignment.Center,
            ) {
                Checkbox(
                    checked = rememberCheckboxPref,
                    onCheckedChange = { updateValue(newValue = it) }
                )
            }
        }
    }
}

@Preview
@Composable
fun SettingsCheckboxPreview() {
    MaterialTheme {
        SettingsCheckbox(
            icon = { Icon(imageVector = Icons.Default.Wifi, contentDescription = "Wifi") },
            title = { Text(text = "Hello") },
            subtitle = { Text(text = "This is a longer text") },
            checked = true,
        ) {}
    }
}