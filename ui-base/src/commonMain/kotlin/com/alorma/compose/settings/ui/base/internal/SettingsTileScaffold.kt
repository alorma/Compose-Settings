package com.alorma.compose.settings.ui.base.internal

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemColors
import androidx.compose.material3.LocalContentColor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp

@Composable
fun SettingsTileScaffold(
  title: @Composable () -> Unit,
  modifier: Modifier = Modifier,
  enabled: Boolean = true,
  subtitle: @Composable (() -> Unit)? = null,
  icon: @Composable (() -> Unit)? = null,
  colors: SettingsTileColors = SettingsTileDefaults.colors(),
  tonalElevation: Dp = SettingsTileDefaults.Elevation,
  shadowElevation: Dp = SettingsTileDefaults.Elevation,
  action: @Composable (() -> Unit)? = null,
) {
  val decoratedTitle: @Composable () -> Unit = {
    ProvideContentColor(colors.titleColor(enabled)) {
      title()
    }
  }
  val decoratedSubtitle: @Composable (() -> Unit)? =
    subtitle?.let {
      {
        ProvideContentColor(colors.subtitleColor(enabled)) {
          subtitle()
        }
      }
    }
  val decoratedIcon: @Composable (() -> Unit)? =
    icon?.let {
      {
        ProvideContentColor(colors.iconColor(enabled)) {
          icon()
        }
      }
    }
  val decoratedAction: @Composable (() -> Unit)? =
    action?.let {
      {
        ProvideContentColor(colors.actionColor(enabled)) {
          action()
        }
      }
    }

  ListItem(
    modifier = Modifier.fillMaxWidth().then(modifier),
    headlineContent = decoratedTitle,
    supportingContent = decoratedSubtitle,
    leadingContent = decoratedIcon,
    trailingContent = decoratedAction,
    colors =
      ListItemColors(
        containerColor = colors.containerColor,
        headlineColor = colors.titleColor,
        leadingIconColor = colors.iconColor,
        overlineColor = colors.actionColor,
        supportingTextColor = colors.subtitleColor,
        trailingIconColor = colors.actionColor,
        disabledHeadlineColor = colors.disabledTitleColor,
        disabledLeadingIconColor = colors.disabledIconColor,
        disabledTrailingIconColor = colors.disabledActionColor,
      ),
    tonalElevation = tonalElevation,
    shadowElevation = shadowElevation,
  )
}

@Composable
private fun ProvideContentColor(
  contentColor: Color,
  content: @Composable () -> Unit,
) {
  CompositionLocalProvider(LocalContentColor provides contentColor) {
    content()
  }
}
