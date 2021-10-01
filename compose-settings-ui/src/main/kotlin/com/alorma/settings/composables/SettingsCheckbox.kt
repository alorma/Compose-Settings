package com.alorma.settings.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Checkbox
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Wifi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.alorma.settings.composables.internal.SettingsTileAction
import com.alorma.settings.composables.internal.SettingsTileIcon
import com.alorma.settings.composables.internal.SettingsTileTexts
import com.alorma.settings.storage.SettingValueState
import com.alorma.settings.storage.getValue
import com.alorma.settings.storage.rememberBooleanSettingState
import com.alorma.settings.storage.setValue

@Composable
fun SettingsCheckbox(
  modifier: Modifier = Modifier,
  state: SettingValueState<Boolean> = rememberBooleanSettingState(),
  icon: @Composable (() -> Unit)? = null,
  title: @Composable () -> Unit,
  subtitle: @Composable (() -> Unit)? = null,
  onCheckedChange: (Boolean) -> Unit = {},
) {
  var storageValue by state
  val update: (Boolean) -> Unit = { boolean ->
    storageValue = boolean
    onCheckedChange(storageValue)
  }
  Surface {
    Row(
      modifier = modifier
        .fillMaxWidth()
        .clickable(onClick = { update(!storageValue) }),
      verticalAlignment = Alignment.CenterVertically,
    ) {
      SettingsTileIcon(icon = icon)
      SettingsTileTexts(title = title, subtitle = subtitle)
      SettingsTileAction {
        Checkbox(
          checked = storageValue,
          onCheckedChange = update
        )
      }
    }
  }
}

@Preview
@Composable
internal fun SettingsCheckboxPreview() {
  MaterialTheme {
    val storage = rememberBooleanSettingState(defaultValue = true)
    SettingsCheckbox(
      state = storage,
      icon = { Icon(imageVector = Icons.Default.Wifi, contentDescription = "Wifi") },
      title = { Text(text = "Hello") },
      subtitle = { Text(text = "This is a longer text") },
      onCheckedChange = { }
    )
  }
}