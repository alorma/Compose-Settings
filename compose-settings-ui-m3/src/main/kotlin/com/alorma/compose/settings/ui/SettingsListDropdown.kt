package com.alorma.compose.settings.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowDropDown
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.alorma.compose.settings.storage.base.SettingValueState
import com.alorma.compose.settings.storage.base.rememberIntSettingState
import com.alorma.compose.settings.ui.internal.SettingsTileScaffold
import com.alorma.compose.settings.ui.internal.WrapContentColor

@Composable
fun SettingsListDropdown(
  modifier: Modifier = Modifier,
  enabled: Boolean = true,
  state: SettingValueState<Int> = rememberIntSettingState(),
  title: @Composable () -> Unit,
  items: List<String>,
  icon: (@Composable () -> Unit)? = null,
  subtitle: (@Composable () -> Unit)? = null,
  onItemSelected: ((Int, String) -> Unit)? = null,
  menuItem: (@Composable (index: Int, text: String) -> Unit)? = null,
) {
  if (state.value > items.size) {
    throw IndexOutOfBoundsException("Current value of state for list setting cannot be grater than items size")
  }

  Surface {
    var isDropdownExpanded by remember { mutableStateOf(false) }

    Row(
      modifier = modifier.fillMaxWidth()
        .clickable(enabled = enabled) { isDropdownExpanded = true },
      verticalAlignment = Alignment.CenterVertically
    ) {
      SettingsTileScaffold(
        enabled = enabled,
        title = title,
        subtitle = subtitle,
        icon = icon,
        action = {
          WrapContentColor(enabled = enabled) {
            Column(
              modifier = Modifier.padding(end = 8.dp)
            ) {
              Row(
                modifier = Modifier.padding(vertical = 5.dp),
                verticalAlignment = Alignment.CenterVertically
              ) {
                Text(text = items[state.value])
                Icon(
                  modifier = Modifier.padding(start = 8.dp),
                  imageVector = Icons.Outlined.ArrowDropDown,
                  contentDescription = null
                )
              }

              DropdownMenu(
                expanded = isDropdownExpanded,
                onDismissRequest = { isDropdownExpanded = false }
              ) {
                items.forEachIndexed { index, text ->
                  DropdownMenuItem(
                    text = {
                      if (menuItem != null) {
                        menuItem(index, text)
                      } else {
                        Text(text = text)
                      }
                    },
                    onClick = {
                      state.value = index
                      isDropdownExpanded = false
                      onItemSelected?.invoke(index, text)
                    }
                  )
                }
              }
            }
          }
        },
      )
    }
  }
}