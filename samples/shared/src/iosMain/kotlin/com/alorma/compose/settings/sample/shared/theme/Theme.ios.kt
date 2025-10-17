package theme

import androidx.compose.material3.ColorScheme
import androidx.compose.runtime.Composable

/**
 * iOS doesn't support Material You dynamic colors, so return null.
 */
@Composable
actual fun getDynamicColorScheme(darkTheme: Boolean): ColorScheme? = null
