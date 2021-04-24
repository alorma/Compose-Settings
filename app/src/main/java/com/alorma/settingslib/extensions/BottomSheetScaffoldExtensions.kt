package com.alorma.settingslib.extensions

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.material.ScaffoldState
import androidx.compose.material.SnackbarDuration
import androidx.compose.material.SnackbarResult

@OptIn(ExperimentalMaterialApi::class)
suspend fun ModalBottomSheetState.toggle() {
    if (isVisible) {
        hide()
    } else {
        show()
    }
}

@OptIn(ExperimentalMaterialApi::class)
suspend fun ScaffoldState.showSnackbar(
    message: String,
    actionLabel: String? = null,
    duration: SnackbarDuration = SnackbarDuration.Short,
): SnackbarResult {
    return snackbarHostState.showSnackbar(
        message,
        actionLabel,
        duration,
    )
}