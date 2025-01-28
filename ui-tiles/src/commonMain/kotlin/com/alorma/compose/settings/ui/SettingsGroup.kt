package com.alorma.compose.settings.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ProvideTextStyle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.SemanticsPropertyReceiver
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import com.alorma.compose.settings.ui.base.internal.LocalSettingsGroupEnabled
import com.alorma.compose.settings.ui.base.internal.SettingsTileColors
import com.alorma.compose.settings.ui.base.internal.SettingsTileDefaults

@Composable
fun SettingsGroup(
  modifier: Modifier = Modifier,
  enabled: Boolean = true,
  contentPadding: PaddingValues = PaddingValues(0.dp),
  colors: SettingsTileColors = SettingsTileDefaults.colors(),
  title: @Composable (() -> Unit)? = null,
  semanticProperties: (SemanticsPropertyReceiver.() -> Unit) = {},
  content: @Composable ColumnScope.() -> Unit,
) {
  Column(
    modifier =
      Modifier
        .fillMaxWidth()
        .semantics(properties = semanticProperties)
        .then(modifier)
        .padding(contentPadding),
  ) {
    CompositionLocalProvider(LocalContentColor provides colors.actionColor(enabled)) {
      if (title != null) {
        SettingsGroupTitle(title)
      }
    }
    CompositionLocalProvider(LocalSettingsGroupEnabled provides enabled) {
      content()
    }
  }
}

@Composable
internal fun SettingsGroupTitle(title: @Composable () -> Unit) {
  Box(
    modifier =
      Modifier
        .fillMaxWidth()
        .padding(vertical = 8.dp, horizontal = 16.dp),
    contentAlignment = Alignment.CenterStart,
  ) {
    val primary = MaterialTheme.colorScheme.primary
    val titleStyle = MaterialTheme.typography.headlineMedium.copy(color = primary)
    ProvideTextStyle(value = titleStyle) { title() }
  }
}
