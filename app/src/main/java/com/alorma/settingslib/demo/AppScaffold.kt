package com.alorma.settingslib.demo

import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.alorma.settings.composables.internal.SettingsToolbar

@Composable
fun AppScaffold(
    scaffoldState: ScaffoldState = rememberScaffoldState(),
    title: @Composable (() -> Unit)? = null,
    onBack: (() -> Unit)? = null,
    onSearch: (() -> Unit)? = null,
    onHelp: (() -> Unit)? = null,
    content: @Composable (ColumnScope.() -> Unit),
) {
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            if (title != null) {
                SettingsToolbar(
                    title = title,
                    onBack = onBack,
                    onSearch = onSearch,
                    onHelp = onHelp,
                )
            }
        },
    ) {
        Column(
            modifier = Modifier.scrollable(
                state = rememberScrollState(),
                orientation = Orientation.Vertical,
            ),
            content = content
        )
    }
}