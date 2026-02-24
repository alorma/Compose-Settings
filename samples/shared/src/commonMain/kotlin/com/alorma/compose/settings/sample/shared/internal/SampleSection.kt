package com.alorma.compose.settings.sample.shared.internal

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.alorma.compose.settings.ui.SettingsGroup
import com.alorma.compose.settings.ui.SettingsTileDefaults
import com.alorma.compose.settings.ui.core.LocalSettingsTileColors

@Composable
internal fun SampleSection(
  title: String,
  enabled: Boolean = true,
  verticalArrangement: Arrangement.Vertical = Arrangement.spacedBy(12.dp),
  content: @Composable ColumnScope.() -> Unit,
) {
  SettingsGroup(
    contentPadding = PaddingValues(16.dp),
    verticalArrangement = verticalArrangement,
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
