package com.alorma.settingslib.extensions

import androidx.compose.material.BottomSheetScaffoldState
import androidx.compose.material.ExperimentalMaterialApi

@OptIn(ExperimentalMaterialApi::class)
suspend fun BottomSheetScaffoldState.toggle() {
    if (bottomSheetState.isCollapsed) {
        bottomSheetState.expand()
    } else {
        bottomSheetState.collapse()
    }
}

@OptIn(ExperimentalMaterialApi::class)
suspend fun BottomSheetScaffoldState.hideOr(block: () -> Unit) {
    if (bottomSheetState.isExpanded) {
        bottomSheetState.collapse()
    } else {
        block()
    }
}