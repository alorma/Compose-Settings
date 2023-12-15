package com.alorma.compose.settings.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ProvideTextStyle
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun SettingsGroup(
  modifier: Modifier = Modifier,
  title: @Composable (() -> Unit)? = null,
  content: @Composable ColumnScope.() -> Unit,
) {
  Surface {
    Column(
      modifier = modifier.fillMaxWidth(),
    ) {
      if (title != null) {
        SettingsGroupTitle(title)
      }
      content()
    }
  }
}

@Composable
internal fun SettingsGroupTitle(title: @Composable () -> Unit) {
  Box(
    modifier = Modifier
      .fillMaxWidth()
      .height(64.dp)
      .padding(horizontal = 16.dp),
    contentAlignment = Alignment.CenterStart,
  ) {
    val primary = MaterialTheme.colorScheme.primary
    val titleStyle = MaterialTheme.typography.headlineMedium.copy(color = primary)
    ProvideTextStyle(value = titleStyle) { title() }
  }
}
