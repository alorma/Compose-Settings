package com.alorma.compose.settings.ui.base.internal

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemColors
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.LocalContentColor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp

private const val ListItemDisabledLabelTextOpacity = 0.3f
private const val ListItemDisabledLeadingIconOpacity = 0.38f
private const val ListItemDisabledTrailingIconOpacity = 0.38f

@Composable
fun SettingsTileScaffold(
  title: @Composable () -> Unit,
  modifier: Modifier = Modifier,
  enabled: Boolean = true,
  subtitle: @Composable (() -> Unit)? = null,
  icon: @Composable (() -> Unit)? = null,
  colors: ListItemColors = ListItemDefaults.colors(),
  tonalElevation: Dp = ListItemDefaults.Elevation,
  shadowElevation: Dp = ListItemDefaults.Elevation,
  action: @Composable (() -> Unit)? = null,
) {
  val decoratedTitle: @Composable () -> Unit = {
    ProvideContentColor(labelContentColor(enabled)) {
      title()
    }
  }
  val decoratedSubtitle: @Composable (() -> Unit)? = subtitle?.let {
    {
      ProvideContentColor(labelContentColor(enabled)) {
        subtitle()
      }
    }
  }
  val decoratedIcon: @Composable (() -> Unit)? = icon?.let {
    {
      ProvideContentColor(iconContentColor(enabled)) {
        icon()
      }
    }
  }
  val decoratedAction: @Composable (() -> Unit)? = action?.let {
    {
      ProvideContentColor(actionContentColor(enabled)) {
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
    colors = colors,
    tonalElevation = tonalElevation,
    shadowElevation = shadowElevation,
  )
}

@ReadOnlyComposable
@Composable
private fun labelContentColor(enabled: Boolean): Color {
  return LocalContentColor.current.let {
    if (enabled) it else it.copy(alpha = ListItemDisabledLabelTextOpacity)
  }
}

@ReadOnlyComposable
@Composable
private fun iconContentColor(enabled: Boolean): Color {
  return LocalContentColor.current.let {
    if (enabled) it else it.copy(alpha = ListItemDisabledLeadingIconOpacity)
  }
}

@ReadOnlyComposable
@Composable
private fun actionContentColor(enabled: Boolean): Color {
  return LocalContentColor.current.let {
    if (enabled) it else it.copy(alpha = ListItemDisabledTrailingIconOpacity)
  }
}

@Composable
private fun ProvideContentColor(
  contentColor: Color,
  content: @Composable () -> Unit
) {
  CompositionLocalProvider(LocalContentColor provides contentColor) {
    content()
  }
}
