package com.alorma.compose.settings.sample.shared.previews

import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.alorma.compose.settings.ui.SettingsSegmented
import theme.ComposeSettingsTheme

@SuperPreviews
@Composable
internal fun SettingsSegmentedPreview() {
    val selectState = remember { mutableStateOf(false) }

    ComposeSettingsTheme {
        Surface {
            val darkModes = listOf(true, false)

            SettingsSegmented(
                title = { Text("Theme mode") },
                items = darkModes,
                selectedItem = selectState.value,
                onItemSelected = { darkMode -> selectState.value = darkMode },
                itemTitleMap = { item ->
                    when (item) {
                        true -> "Dark"
                        false -> "Light"
                    }
                },
            )
        }
    }
}
