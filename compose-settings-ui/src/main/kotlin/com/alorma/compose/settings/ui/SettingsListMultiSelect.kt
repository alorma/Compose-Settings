package com.alorma.compose.settings.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectable
import androidx.compose.material.AlertDialog
import androidx.compose.material.Checkbox
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ProvideTextStyle
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp
import com.alorma.compose.settings.storage.base.SettingValueState
import com.alorma.compose.settings.storage.base.rememberIntSetSettingState

@Composable
fun SettingsListMultiSelect(
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    state: SettingValueState<Set<Int>> = rememberIntSetSettingState(),
    title: @Composable () -> Unit,
    items: List<String>,
    icon: @Composable (() -> Unit)? = null,
    confirmButton: String,
    useSelectedValuesAsSubtitle: Boolean = true,
    subtitle: @Composable (() -> Unit)? = null,
    onItemsSelected: ((List<String>) -> Unit)? = null,
    action: @Composable ((Boolean) -> Unit)? = null
) {
    if (state.value.any { index -> index >= items.size }) {
        throw IndexOutOfBoundsException("Current indexes for $title list setting cannot be grater than items size")
    }

    var showDialog by remember { mutableStateOf(false) }

    val safeSubtitle = if (state.value.size >= 0 && useSelectedValuesAsSubtitle) {
        {
            Text(
                text = state.value.map { index -> items[index] }
                    .joinToString(separator = ", ") { it }
            )
        }
    } else {
        subtitle
    }

    SettingsMenuLink(
        enabled = enabled,
        modifier = modifier,
        icon = icon,
        title = title,
        subtitle = safeSubtitle,
        action = action,
        onClick = { showDialog = true }
    )

    if (!showDialog) return

    val onAdd: (Int) -> Unit = { selectedIndex ->
        val mutable = state.value.toMutableSet()
        mutable.add(selectedIndex)
        state.value = mutable
    }
    val onRemove: (Int) -> Unit = { selectedIndex ->
        val mutable = state.value.toMutableSet()
        mutable.remove(selectedIndex)
        state.value = mutable
    }

    AlertDialog(
        title = title,
        text = {
            Column {
                subtitle?.invoke()
                items.forEachIndexed { index, item ->
                    val isSelected by rememberUpdatedState(newValue = state.value.contains(index))
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .selectable(
                                role = Role.Checkbox,
                                selected = isSelected,
                                onClick = {
                                    if (isSelected) {
                                        onRemove(index)
                                    } else {
                                        onAdd(index)
                                    }
                                }
                            )
                            .padding(
                                top = 16.dp,
                                bottom = 16.dp
                            ),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = item,
                            style = MaterialTheme.typography.body1,
                            modifier = Modifier.weight(1f)
                        )
                        Checkbox(
                            checked = isSelected,
                            onCheckedChange = { checked ->
                                if (checked) {
                                    onAdd(index)
                                } else {
                                    onRemove(index)
                                }
                            }
                        )
                    }
                }
            }
        },
        onDismissRequest = { showDialog = false },
        confirmButton = {
            TextButton(
                onClick = {
                    showDialog = false
                    onItemsSelected?.invoke(state.value.map { index -> items[index] })
                }
            ) {
                ProvideTextStyle(
                    value = MaterialTheme.typography.button.copy(fontFeatureSettings = "c2sc, smcp")
                ) {
                    Text(text = confirmButton)
                }
            }
        }
    )
}
