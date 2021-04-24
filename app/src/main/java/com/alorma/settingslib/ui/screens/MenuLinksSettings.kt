package com.alorma.settingslib.ui.screens

import androidx.compose.material.BottomSheetState
import androidx.compose.material.BottomSheetValue
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.material.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.alorma.settings.SettingsList
import com.alorma.settingslib.R
import com.alorma.settingslib.extensions.hideOr
import com.alorma.settingslib.extensions.toggle
import com.alorma.settingslib.ui.helpers.InfoBottomSheet
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MenuLinksSettings(
    navController: NavController = rememberNavController(),
) {
    val bottomSheetScaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = BottomSheetState(BottomSheetValue.Collapsed)
    )

    val coroutineScope = rememberCoroutineScope()

    InfoBottomSheet(
        scaffoldState = bottomSheetScaffoldState,
        title = stringResource(R.string.help_menu_links_title),
        text = stringResource(id = R.string.help_menu_links_text)
    ) {
        SettingsList(
            title = { Text(text = "Menu links") },
            onBack = {
                coroutineScope.launch {
                    bottomSheetScaffoldState.hideOr {
                        navController.popBackStack()
                    }
                }
            },
            onHelp = {
                coroutineScope.launch { bottomSheetScaffoldState.toggle() }
            }
        ) {

        }
    }
}