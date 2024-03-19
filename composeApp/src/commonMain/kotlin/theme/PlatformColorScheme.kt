package theme

import androidx.compose.material3.ColorScheme
import androidx.compose.runtime.Composable

@Composable
expect fun createColorScheme(systemInDarkTheme: Boolean): ColorScheme