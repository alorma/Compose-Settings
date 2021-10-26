package com.alorma.compose.settings.example.ui.helpers

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.ProvideTextStyle
import androidx.compose.material.Text
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun InfoBottomSheet(
    sheetState: ModalBottomSheetState = rememberModalBottomSheetState(ModalBottomSheetValue.Hidden),
    title: String,
    text: String,
    content: @Composable () -> Unit,
) {
    ModalBottomSheetLayout(
        sheetState = sheetState,
        sheetContent = {
            Column(
                modifier = Modifier.padding(vertical = 8.dp, horizontal = 16.dp)
            ) {
                Box(
                    modifier = Modifier
                        .height(56.dp)
                        .fillMaxWidth(),
                    contentAlignment = Alignment.CenterStart,
                ) {
                    ProvideTextStyle(value = MaterialTheme.typography.h6) {
                        Text(text = title)
                    }
                }
                Spacer(modifier = Modifier.height(8.dp))
                ProvideTextStyle(value = MaterialTheme.typography.body1) {
                    Text(text = text)
                }
            }
        },
        content = content,
    )
}