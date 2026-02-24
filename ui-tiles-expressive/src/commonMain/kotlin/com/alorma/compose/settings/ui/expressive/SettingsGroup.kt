package com.alorma.compose.settings.ui.expressive

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.ProvideTextStyle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.SemanticsPropertyReceiver
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import com.alorma.compose.settings.ui.core.LocalSettingsGroupEnabled
import com.alorma.compose.settings.ui.core.SettingsTextStyles
import com.alorma.compose.settings.ui.core.SettingsTileColors

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun SettingsGroup(
  modifier: Modifier = Modifier,
  enabled: Boolean = true,
  contentPadding: PaddingValues = PaddingValues(0.dp),
  verticalArrangement: Arrangement.Vertical = Arrangement.spacedBy(8.dp),
  colors: SettingsTileColors = SettingsTileDefaults.colors(),
  textStyles: SettingsTextStyles = SettingsTileDefaults.textStyles(),
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
    verticalArrangement = verticalArrangement,
  ) {
    if (title != null) {
      CompositionLocalProvider(LocalContentColor provides colors.groupTitleColor(enabled)) {
        ProvideTextStyle(
          textStyles.groupTitleStyle,
        ) {
          SettingsGroupTitle(title)
        }
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
    title()
  }
}
