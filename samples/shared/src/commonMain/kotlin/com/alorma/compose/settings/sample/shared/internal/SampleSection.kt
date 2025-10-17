package com.alorma.compose.settings.sample.shared.internal

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.alorma.compose.settings.ui.SettingsGroup
import com.alorma.compose.settings.ui.base.internal.LocalSettingsTileColors
import com.alorma.compose.settings.ui.base.internal.SettingsTileDefaults

@Composable
internal fun SampleSection(
  title: String,
  enabled: Boolean = true,
  content: @Composable ColumnScope.() -> Unit,
) {
  SettingsGroup(
    contentPadding = PaddingValues(16.dp),
    enabled = enabled,
    title = { Text(text = title) },
  ) {
    Card(
      colors = CardDefaults.cardColors(containerColor = (LocalSettingsTileColors.current ?: SettingsTileDefaults.colors()).containerColor),
    ) {
      content()
    }
  }
}
