package com.alorma.compose.settings.sample.shared.internal

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.alorma.compose.settings.ui.SettingsGroup

@Composable
internal fun SampleSection(
  title: String,
  enabled: Boolean = true,
  paddingValues: PaddingValues = PaddingValues(16.dp),
  verticalArrangement: Arrangement.Vertical = Arrangement.spacedBy(12.dp),
  content: @Composable ColumnScope.() -> Unit,
) {
  SettingsGroup(
    contentPadding = paddingValues,
    verticalArrangement = verticalArrangement,
    enabled = enabled,
    title = { Text(text = title) },
  ) {
    content()
  }
}
