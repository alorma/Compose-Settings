package com.alorma.settingslib.ui.screens

import androidx.compose.material.Divider
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.SortByAlpha
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.alorma.settings.SettingsList
import com.alorma.settings.composables.SettingsMenuLink
import com.alorma.settingslib.R
import com.alorma.settingslib.extensions.showSnackbar
import com.alorma.settingslib.extensions.toggle
import com.alorma.settingslib.ui.helpers.InfoBottomSheet
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MenuLinksSettings(
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

        SettingsList(
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
                    Icon(imageVector = Icons.Default.SortByAlpha,
                        contentDescription = "Menu 1")
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
            ) {
                coroutineScope.launch {
                    scaffoldState.showSnackbar(message = "Click on menu 2")
                }
            }
            Divider()
            SettingsMenuLink(
                title = { Text(text = "Menu 3") }, icon = {
                    Icon(imageVector = Icons.Default.SortByAlpha,
                        contentDescription = "Menu 1")
                }
            ) {
                coroutineScope.launch {
                    scaffoldState.showSnackbar(message = "Click on menu 3")
                }
            }
            SettingsMenuLink(
                title = { Text(text = "Menu 4") },
            ) {
                coroutineScope.launch {
                    scaffoldState.showSnackbar(message = "Click on menu 4")
                }
            }
        }
    }
}
