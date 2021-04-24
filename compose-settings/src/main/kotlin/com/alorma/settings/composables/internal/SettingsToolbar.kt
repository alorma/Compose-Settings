package com.alorma.settings.composables.internal

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.HelpOutline
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.alorma.settings.R

@Composable
fun SettingsToolbar(
    title: @Composable () -> Unit,
    onBack: (() -> Unit)? = null,
    onSearch: (() -> Unit)? = null,
    onHelp: (() -> Unit)? = null,
) {
    TopAppBar(
        title = title,
        navigationIcon = {
            IconButton(onClick = { onBack?.invoke() }) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = stringResource(
                        R.string.compose_settings_header_back_action
                    ),
                )
            }
        },
        backgroundColor = MaterialTheme.colors.surface,
        contentColor = MaterialTheme.colors.primary,
        actions = {
            IconButton(onClick = { onSearch?.invoke() }) {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = stringResource(
                        R.string.compose_settings_header_search_action
                    ),
                )
            }
            IconButton(onClick = { onHelp?.invoke() }) {
                Icon(
                    imageVector = Icons.Default.HelpOutline,
                    contentDescription = stringResource(
                        R.string.compose_settings_header_help_action
                    ),
                )
            }
        },
    )
}

@Preview
@Composable
fun SearchToolbarPreview() {
    MaterialTheme {
        SettingsToolbar(
            title = { Text(text = "Title") }
        )
    }
}