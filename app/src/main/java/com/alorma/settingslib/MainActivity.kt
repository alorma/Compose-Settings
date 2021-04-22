package com.alorma.settingslib

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.platform.LocalContext
import androidx.preference.PreferenceManager
import com.alorma.settings.SettingsList
import com.alorma.settings.composables.SettingsSwitch
import com.alorma.settings.storage.BooleanAndroidPreferences
import com.alorma.settings.storage.LocalBooleanSettings
import com.alorma.settingslib.ui.theme.SettingsLibTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SettingsLibTheme {
                SettingsList(title = { Text(text = "Demo settings") }) {
                    SettingsSwitch(
                        key = "Demo1",
                        title = { Text(text = "Demo 1") },
                        checked = true
                    )
                    Divider()
                    SettingsSwitch(
                        key = "Demo2",
                        icon = {
                            Icon(
                                imageVector = Icons.Default.Settings,
                                contentDescription = "Settings",
                            )
                        },
                        title = { Text(text = "Demo 2") },
                        subtitle = { Text(text = "This a subtitle") },
                        checked = false
                    )
                }
            }
        }
    }
}