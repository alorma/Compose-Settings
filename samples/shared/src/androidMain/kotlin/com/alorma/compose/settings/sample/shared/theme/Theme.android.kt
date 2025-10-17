package theme

import android.os.Build
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

/**
 * Returns dynamic color scheme on Android 12+ (API 31+), or null on older versions.
 *
 * Dynamic colors adapt to the user's wallpaper and system theme (Material You).
 */
@Composable
actual fun getDynamicColorScheme(darkTheme: Boolean): ColorScheme? {
  return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
    val context = LocalContext.current
    if (darkTheme) {
      dynamicDarkColorScheme(context)
    } else {
      dynamicLightColorScheme(context)
    }
  } else {
    null
  }
}
