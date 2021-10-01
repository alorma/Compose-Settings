package com.alorma.settings.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Switch
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
import com.alorma.settings.storage.ValueSetting
import com.alorma.settings.storage.getValue
import com.alorma.settings.storage.rememberBooleanSetting
import com.alorma.settings.storage.setValue

@Composable
fun SettingsSwitch(
  modifier: Modifier = Modifier,
  setting: ValueSetting<Boolean> = rememberBooleanSetting(),
  icon: @Composable (() -> Unit)? = null,
  title: @Composable () -> Unit,
  subtitle: @Composable (() -> Unit)? = null,
  onCheckedChange: (Boolean) -> Unit = {},
) {
  var storageValue by setting
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
        Switch(
          checked = storageValue,
          onCheckedChange = update
        )
      }
    }
  }
}

@Preview
@Composable
internal fun SettingsSwitchPreview() {
  MaterialTheme {
    val storage = rememberBooleanSetting(defaultValue = true)
    SettingsSwitch(
      setting = storage,
      icon = { Icon(imageVector = Icons.Default.Wifi, contentDescription = "Wifi") },
      title = { Text(text = "Hello") },
      subtitle = { Text(text = "This is a longer text") },
      onCheckedChange = { }
    )
  }
}