package com.alorma.compose.settings.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.alorma.compose.settings.storage.base.SettingValueState
import com.alorma.compose.settings.storage.base.rememberIntSettingState
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun SettingsList(
  modifier: Modifier = Modifier,
  enabled: Boolean = true,
  state: SettingValueState<Int> = rememberIntSettingState(),
  title: @Composable () -> Unit,
  items: List<String>,
  icon: (@Composable () -> Unit)? = null,
  useSelectedValueAsSubtitle: Boolean = true,
  subtitle: (@Composable () -> Unit)? = null,
  closeDialogDelay: Long = 200,
  action: (@Composable (Boolean) -> Unit)? = null,
  onItemSelected: ((Int, String) -> Unit)? = null
) {

  if (state.value >= items.size) {
    throw IndexOutOfBoundsException("Current value for $title list setting cannot be grater than items size")
  }

  var showDialog by remember { mutableStateOf(false) }

  val safeSubtitle = if (state.value >= 0 && useSelectedValueAsSubtitle) {
    { Text(text = items[state.value]) }
  } else {
    subtitle
  }

  SettingsMenuLink(
    modifier = modifier,
    enabled = enabled,
    icon = icon,
    title = title,
    subtitle = safeSubtitle,
    action = action,
    onClick = { showDialog = true },
  )

  if (!showDialog) return

  val coroutineScope = rememberCoroutineScope()
  val onSelected: (Int, Boolean) -> Unit = { selectedIndex, updateState ->
    coroutineScope.launch {
      if (updateState) state.value = selectedIndex
      delay(closeDialogDelay)
      showDialog = false
    }
  }

  AlertDialog(
    title = title,
    text = {
      Column(
        modifier = Modifier
          .fillMaxWidth()
          .selectableGroup(),
      ) {
        if (subtitle != null) {
          subtitle()
          Spacer(modifier = Modifier.size(8.dp))
        }

        items.forEachIndexed { index, item ->
          val isSelected by rememberUpdatedState(newValue = state.value == index)
          Row(
            modifier = Modifier
              .fillMaxWidth()
              .height(56.dp)
              .selectable(
                role = Role.RadioButton,
                selected = isSelected,
                onClick = {
                  onSelected(index, !isSelected)
                  onItemSelected?.invoke(index, items[index])
                }
              ),
            verticalAlignment = Alignment.CenterVertically
          ) {
            RadioButton(
              selected = isSelected,
              onClick = null
            )
            Text(
              text = item,
              style = MaterialTheme.typography.bodyLarge,
              modifier = Modifier.padding(start = 16.dp)
            )
          }
        }
      }
    },
    onDismissRequest = { showDialog = false },
    confirmButton = {},
    dismissButton = {},
  )
}

@Preview
@Composable
internal fun ListLinkPreview() {
  MaterialTheme {
    SettingsList(
      items = listOf("Banana", "Kiwi", "Pineapple"),
      icon = { Icon(imageVector = Icons.Default.Clear, contentDescription = "Clear") },
      title = { Text(text = "Hello") },
      subtitle = { Text(text = "This is a longer text") },
    )
  }
}