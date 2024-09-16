package com.alorma.compose.settings.sample.shared.previews

import android.content.res.Configuration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.Wallpapers

@Retention(AnnotationRetention.BINARY)
@Target(
  AnnotationTarget.ANNOTATION_CLASS,
  AnnotationTarget.FUNCTION
)
@Preview(
  apiLevel = 33,
  name = "Light - No dynamic",
  wallpaper = Wallpapers.NONE,
)
@Preview(
  apiLevel = 33,
  name = "Light - Red",
  wallpaper = Wallpapers.RED_DOMINATED_EXAMPLE,
)
@Preview(
  apiLevel = 33,
  name = "Light - Blue",
  wallpaper = Wallpapers.BLUE_DOMINATED_EXAMPLE,
)
@Preview(
  apiLevel = 33,
  name = "Light - Green",
  wallpaper = Wallpapers.GREEN_DOMINATED_EXAMPLE,
)
@Preview(
  apiLevel = 33,
  name = "Light - Yellow",
  wallpaper = Wallpapers.YELLOW_DOMINATED_EXAMPLE,
)
@Preview(
  apiLevel = 33,
  name = "Dark - No dynamic",
  wallpaper = Wallpapers.NONE,
  uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Preview(
  apiLevel = 33,
  name = "Dark - Red",
  wallpaper = Wallpapers.RED_DOMINATED_EXAMPLE,
  uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Preview(
  apiLevel = 33,
  name = "Dark - Blue",
  wallpaper = Wallpapers.BLUE_DOMINATED_EXAMPLE,
  uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Preview(
  apiLevel = 33,
  name = "Dark - Green",
  wallpaper = Wallpapers.GREEN_DOMINATED_EXAMPLE,
  uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Preview(
  apiLevel = 33,
  name = "Dark - Yellow",
  wallpaper = Wallpapers.YELLOW_DOMINATED_EXAMPLE,
  uiMode = Configuration.UI_MODE_NIGHT_YES
)
annotation class SuperPreviews
