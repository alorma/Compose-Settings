package com.alorma.compose.settings.example.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Checkbox
import androidx.compose.material.Divider
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.SortByAlpha
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.alorma.compose.settings.example.R
import com.alorma.compose.settings.example.demo.AppScaffold
import com.alorma.compose.settings.example.extensions.showSnackbar
import com.alorma.compose.settings.example.extensions.toggle
import com.alorma.compose.settings.example.ui.helpers.InfoBottomSheet
import com.alorma.compose.settings.ui.SettingsMenuLink
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MenuLinksScreen(
    navController: NavController = rememberNavController(),
) {
    val coroutineScope = rememberCoroutineScope()
    val bottomSheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden,
    )

    InfoBottomSheet(
        sheetState = bottomSheetState,
        title = stringResource(R.string.help_menu_links_title),
        text = stringResource(id = R.string.help_menu_links_text)
    ) {

        val scaffoldState = rememberScaffoldState()

        AppScaffold(
            scaffoldState = scaffoldState,
            title = { Text(text = "Menu links") },
            onBack = {
                navController.popBackStack()
            },
            onHelp = {
                coroutineScope.launch {
                    bottomSheetState.toggle()
                }
            }
        ) {
            SettingsMenuLink(
                title = { Text(text = "Menu 1") },
                subtitle = { Text(text = "Subtitle of menu 1") },
                icon = {
                    Icon(
                        imageVector = Icons.Default.SortByAlpha,
                        contentDescription = "Menu 1"
                    )
                }
            ) {
                coroutineScope.launch {
                    scaffoldState.showSnackbar(message = "Click on menu 1")
                }
            }
            Divider()
            SettingsMenuLink(
                title = { Text(text = "Menu 2") },
                subtitle = { Text(text = "Without icon") },
                action = {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .clickable {
                                coroutineScope.launch {
                                    scaffoldState.snackbarHostState.showSnackbar(
                                        message = "Action click"
                                    )
                                }
                            },
                        contentAlignment = Alignment.Center,
                    ) {
                        Icon(
                            imageVector = Icons.Default.ArrowForward,
                            contentDescription = null,
                        )
                    }
                },
            ) {
                coroutineScope.launch {
                    scaffoldState.showSnackbar(message = "Click on menu 2")
                }
            }
            Divider()
            SettingsMenuLink(
                title = { Text(text = "Menu 3") }, icon = {
                    Icon(
                        imageVector = Icons.Default.SortByAlpha,
                        contentDescription = "Menu 1"
                    )
                }
            ) {
                coroutineScope.launch {
                    scaffoldState.showSnackbar(message = "Click on menu 3")
                }
            }
            Divider()
            var rememberCheckBoxState by remember { mutableStateOf(true) }
            SettingsMenuLink(
                title = { Text(text = "Menu 4") },
                action = {
                    Checkbox(checked = rememberCheckBoxState, onCheckedChange = { newState ->
                        rememberCheckBoxState = newState
                        coroutineScope.launch {
                            scaffoldState.snackbarHostState.showSnackbar(
                                message = "Checkbox update to: $newState"
                            )
                        }
                    })
                },
            ) {
                coroutineScope.launch {
                    scaffoldState.showSnackbar(message = "Click on menu 4")
                }
            }
        }
    }
}
