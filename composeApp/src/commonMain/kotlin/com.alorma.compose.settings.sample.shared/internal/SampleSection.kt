package com.alorma.compose.settings.sample.shared.internal

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.alorma.compose.settings.ui.SettingsGroup

@Composable
internal fun SampleSection(
  title: String,
  content: @Composable ColumnScope.() -> Unit,
) {
  SettingsGroup(
    contentPadding = PaddingValues(16.dp),
    title = { Text(text = title) },
  ) {
    ElevatedCard { content() }
  }
}