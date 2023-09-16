package com.alorma.compose.settings.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowDropDown
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
import com.alorma.compose.settings.ui.internal.SettingsTileIcon
import com.alorma.compose.settings.ui.internal.SettingsTileTexts
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
    WrapContentColor(enabled = enabled) {
      var isDropdownExpanded by remember { mutableStateOf(false) }

      Row(
        modifier = modifier.fillMaxWidth()
          .clickable(enabled = enabled) { isDropdownExpanded = true },
        verticalAlignment = Alignment.CenterVertically
      ) {
        Row(
          modifier = Modifier.weight(1f),
          verticalAlignment = Alignment.CenterVertically,
        ) {
          SettingsTileIcon(icon = icon)
          SettingsTileTexts(
            title = title,
            subtitle = subtitle
          )
        }

        Column(
          modifier = Modifier.padding(end = 8.dp)
        ) {
          Row(
            modifier = Modifier
              .padding(vertical = 5.dp),
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
                onClick = {
                  state.value = index
                  isDropdownExpanded = false
                  onItemSelected?.invoke(index, text)
                }
              ) {
                if (menuItem != null) {
                  menuItem(index, text)
                } else {
                  Text(text = text)
                }
              }
            }
          }
        }
      }
    }
  }
}