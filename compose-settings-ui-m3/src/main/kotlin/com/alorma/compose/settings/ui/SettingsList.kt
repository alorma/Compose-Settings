package com.alorma.compose.settings.ui

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.alorma.compose.settings.storage.base.SettingValueState
import com.alorma.compose.settings.storage.base.rememberIntSettingState
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun SettingsList(
  modifier: Modifier = Modifier,
  state: SettingValueState<Int> = rememberIntSettingState(),
  title: @Composable () -> Unit,
  items: List<String>,
  icon: (@Composable () -> Unit)? = null,
  useSelectedValueAsSubtitle: Boolean = true,
  subtitle: (@Composable () -> Unit)? = null,
  closeDialogDelay: Long = 200,
  action: (@Composable () -> Unit)? = null,
) {

  if (state.value >= items.size) {
    throw IndexOutOfBoundsException("Current value for $title list setting cannot be grater than items size")
  }

  var showDialog by remember { mutableStateOf(false) }

  val safeSubtitle = if (state.value >= 0 && useSelectedValueAsSubtitle) {
    { Text(text = items[state.value]) }
  } else subtitle

  SettingsMenuLink(
    modifier = modifier,
    icon = icon,
    title = title,
    subtitle = safeSubtitle,
    action = action,
    onClick = { showDialog = true },
  )

  if (!showDialog) return

  val coroutineScope = rememberCoroutineScope()
  val onSelected: (Int) -> Unit = { selectedIndex ->
    coroutineScope.launch {
      state.value = selectedIndex
      delay(closeDialogDelay)
      showDialog = false
    }
  }

  AlertDialog(
    title = title,
    text = subtitle,
    onDismissRequest = { showDialog = false },
    confirmButton = {},
    dismissButton = {},
    /*buttons = {
      Column {
        items.forEachIndexed { index, item ->
          val isSelected by rememberUpdatedState(newValue = state.value == index)
          Row(
            modifier = Modifier
              .fillMaxWidth()
              .selectable(
                role = Role.RadioButton,
                selected = isSelected,
                onClick = { if (!isSelected) onSelected(index) }
              )
              .padding(
                start = 33.dp,
                top = 16.dp,
                end = 33.dp,
                bottom = 16.dp
              ),
            verticalAlignment = Alignment.CenterVertically
          ) {
            RadioButton(
              selected = isSelected,
              onClick = { if (!isSelected) onSelected(index) }
            )
            Text(
              text = item,
              style = MaterialTheme.typography.bodyMedium,
              modifier = Modifier.padding(start = 16.dp)
            )
          }
        }
      }
    }*/
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