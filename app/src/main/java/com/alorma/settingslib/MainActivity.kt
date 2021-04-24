package com.alorma.settingslib

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.alorma.settingslib.ui.screens.TopLevelSettings
import com.alorma.settingslib.ui.theme.SettingsLibTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SettingsLibTheme {
                TopLevelSettings()
            }
        }
    }
}