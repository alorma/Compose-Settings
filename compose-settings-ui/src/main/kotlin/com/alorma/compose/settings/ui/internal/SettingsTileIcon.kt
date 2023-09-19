package com.alorma.compose.settings.ui.internal

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
internal fun SettingsTileIcon(
    modifier: Modifier = Modifier,
    icon: @Composable (() -> Unit)? = null,
) {
    if (icon == null) {
        Spacer(
            modifier = modifier
                .padding(end = 16.dp)
                .size(width = 16.dp, height = 64.dp),
        )
    } else {
        Box(
            modifier = modifier.size(64.dp),
            contentAlignment = Alignment.Center,
        ) {
            icon()
        }
    }
}

@Preview
@Composable
fun SettingsIconPreview() {
    MaterialTheme {
        SettingsTileIcon {
            Icon(imageVector = Icons.Default.Clear, contentDescription = "")
        }
    }
}

@Preview
@Composable
fun SettingsIconPreviewEmpty() {
    MaterialTheme {
        SettingsTileIcon()
    }
}
