package theme

import androidx.compose.material3.ColorScheme
import androidx.compose.runtime.Composable

@Composable
actual fun createColorScheme(systemInDarkTheme: Boolean): ColorScheme {
  return if (systemInDarkTheme) {
    DarkColorScheme
  } else {
    LightColorScheme
  }
}
