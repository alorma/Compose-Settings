package theme

import android.os.Build
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

@Composable
actual fun createColorScheme(systemInDarkTheme: Boolean): ColorScheme {
  return if (systemInDarkTheme) {
    if (Build.VERSION.SDK_INT > Build.VERSION_CODES.S) {
      dynamicDarkColorScheme(LocalContext.current)
    } else {
      DarkColorScheme
    }
  } else {
    if (Build.VERSION.SDK_INT > Build.VERSION_CODES.S) {
      dynamicLightColorScheme(LocalContext.current)
    } else {
      LightColorScheme
    }
  }
}
