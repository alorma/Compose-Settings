package com.alorma.compose.settings.ui.base.internal

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import androidx.compose.ui.graphics.Color

@Immutable
class SettingsTileColors(
  val containerColor: Color,
  val titleColor: Color,
  val iconColor: Color,
  val subtitleColor: Color,
  val actionColor: Color,
  val disabledTitleColor: Color,
  val disabledIconColor: Color,
  val disabledSubtitleColor: Color,
  val disabledActionColor: Color,
) {
  @Stable
  fun titleColor(enabled: Boolean): Color {
    return if (enabled) titleColor else disabledTitleColor
  }

  @Stable
  fun iconColor(enabled: Boolean): Color {
    return if (enabled) iconColor else disabledIconColor
  }

  @Stable
  fun subtitleColor(enabled: Boolean): Color {
    return if (enabled) subtitleColor else disabledSubtitleColor
  }

  @Stable
  fun actionColor(enabled: Boolean): Color {
    return if (enabled) actionColor else disabledActionColor
  }
}
