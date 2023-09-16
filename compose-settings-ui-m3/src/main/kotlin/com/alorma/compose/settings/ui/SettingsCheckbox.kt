package com.alorma.compose.settings.ui

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.selection.toggleable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxColors
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.internal.enableLiveLiterals
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.tooling.preview.Preview
import com.alorma.compose.settings.storage.base.SettingValueState
import com.alorma.compose.settings.storage.base.getValue
import com.alorma.compose.settings.storage.base.rememberBooleanSettingState
import com.alorma.compose.settings.storage.base.setValue
import com.alorma.compose.settings.ui.internal.SettingsTileScaffold

@Composable
fun SettingsCheckbox(
  modifier: Modifier = Modifier,
  enabled: Boolean = true,
  state: SettingValueState<Boolean> = rememberBooleanSettingState(),
  icon: @Composable (() -> Unit)? = null,
  title: @Composable () -> Unit,
  subtitle: @Composable (() -> Unit)? = null,
  checkboxColors: CheckboxColors = CheckboxDefaults.colors(),
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
        .toggleable(
          enabled = enabled,
          value = storageValue,
          role = Role.Checkbox,
          onValueChange = { update(!storageValue) }
        ),
      verticalAlignment = Alignment.CenterVertically,
    ) {
      SettingsTileScaffold(
        enabled = enabled,
        title = title,
        subtitle = subtitle,
        icon = icon,
        action = {
          Checkbox(
            enabled = enabled,
            checked = storageValue,
            onCheckedChange = update,
            colors = checkboxColors
          )
        },
      )
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
      icon = { Icon(imageVector = Icons.Default.Clear, contentDescription = "Clear") },
      title = { Text(text = "Hello") },
      subtitle = { Text(text = "This is a longer text") },
      onCheckedChange = { }
    )
  }
}