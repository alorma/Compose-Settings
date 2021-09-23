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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.alorma.settings.composables.internal.SettingsTileAction
import com.alorma.settings.composables.internal.SettingsTileIcon
import com.alorma.settings.composables.internal.SettingsTileTexts
import com.alorma.settings.storage.ValueStorage
import com.alorma.settings.storage.rememberBooleanStorage

@Composable
fun SettingsCheckbox(
  modifier: Modifier = Modifier,
  icon: @Composable (() -> Unit)? = null,
  title: @Composable () -> Unit,
  subtitle: @Composable (() -> Unit)? = null,
  checked: Boolean,
  onCheckedChange: (Boolean) -> Unit,
) {
  Surface {
    Row(
      modifier = modifier
        .fillMaxWidth()
        .clickable(onClick = { onCheckedChange(!checked) }),
      verticalAlignment = Alignment.CenterVertically,
    ) {
      SettingsTileIcon(icon = icon)
      SettingsTileTexts(title = title, subtitle = subtitle)
      SettingsTileAction {
        Checkbox(
          checked = checked,
          onCheckedChange = { onCheckedChange(!checked) }
        )
      }
    }
  }
}

@Composable
fun SettingsCheckbox(
  modifier: Modifier = Modifier,
  key: String,
  storage: ValueStorage<Boolean> = rememberBooleanStorage(),
  icon: @Composable (() -> Unit)? = null,
  title: @Composable () -> Unit,
  subtitle: @Composable (() -> Unit)? = null,
) {
  var state by storage.state(key = key)

  SettingsCheckbox(
    modifier = modifier,
    icon = icon,
    title = title,
    subtitle = subtitle,
    checked = state,
    onCheckedChange = { newValue ->
      storage.save(key = key, value = newValue)
      state = newValue
    },
  )
}

@Preview
@Composable
internal fun SettingsCheckboxPreview() {
  MaterialTheme {
    var state by remember { mutableStateOf(true) }
    SettingsCheckbox(
      icon = { Icon(imageVector = Icons.Default.Wifi, contentDescription = "Wifi") },
      title = { Text(text = "Hello") },
      subtitle = { Text(text = "This is a longer text") },
      checked = state,
      onCheckedChange = { state = it }
    )
  }
}