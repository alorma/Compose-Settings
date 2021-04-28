package com.alorma.settingslib.ui.screens

import androidx.compose.material.Divider
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckBox
import androidx.compose.material.icons.filled.Link
import androidx.compose.material.icons.filled.SwitchLeft
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import androidx.navigation.compose.navigate
import androidx.navigation.compose.rememberNavController
import com.alorma.settings.composables.SettingsGroup
import com.alorma.settings.composables.SettingsMenuLink
import com.alorma.settingslib.demo.AppScaffold
import com.alorma.settingslib.extensions.popOrFinish
import com.alorma.settingslib.ui.Navigation
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun TopLevelScreen(
    navController: NavController = rememberNavController(),
) {
    val sheetState = rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden)
    val scaffoldState = rememberScaffoldState()
    val coroutineScope = rememberCoroutineScope()

    val context = LocalContext.current

    ModalBottomSheetLayout(
        sheetState = sheetState,
        sheetContent = {
            TopLevelScreenContent(
                navController = navController,
                sheetState = sheetState,
            )
        }) {
        AppScaffold(
            scaffoldState = scaffoldState,
            title = { Text(text = "Demo settings") },
            onBack = { navController.popOrFinish(context = context) },
        ) {

            TopLevelScreenContent(
                navController = navController,
                sheetState = sheetState,
            )
            SettingsGroup(
                title = { Text(text = "Second group") },
            ) {
                SettingsMenuLink(
                    title = { Text(text = "Bottom sheet") },
                    onClick = { coroutineScope.launch { sheetState.show() } },
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun TopLevelScreenContent(
    navController: NavController,
    sheetState: ModalBottomSheetState,
) {
    val coroutineScope = rememberCoroutineScope()

    SettingsMenuLink(
        icon = {
            Icon(
                imageVector = Icons.Default.Link,
                contentDescription = "Menu link",
            )
        },
        title = { Text(text = "Menu links") },
    ) {
        coroutineScope.launch {
            sheetState.hide()
            navController.navigate(Navigation.NAV_MENU_LINKS)
        }
    }
    Divider()
    SettingsMenuLink(
        icon = {
            Icon(
                imageVector = Icons.Default.SwitchLeft,
                contentDescription = "Switches",
            )
        },
        title = { Text(text = "Switches") },
        onClick = {
            coroutineScope.launch {
                sheetState.hide()
                navController.navigate(Navigation.NAV_SWITCHES)
            }
        },
    )
    Divider()
    SettingsMenuLink(
        icon = {
            Icon(
                imageVector = Icons.Default.CheckBox,
                contentDescription = "Checkboxes",
            )
        },
        title = { Text(text = "Demo 4") },
    ) {
        coroutineScope.launch {
            sheetState.hide()
            navController.navigate(Navigation.NAV_CHECKBOXES)
        }
    }
}