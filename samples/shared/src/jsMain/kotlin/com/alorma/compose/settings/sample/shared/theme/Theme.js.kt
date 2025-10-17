package theme

import androidx.compose.material3.ColorScheme
import androidx.compose.runtime.Composable

/**
 * JavaScript doesn't support dynamic colors, so return null.
 */
@Composable
actual fun getDynamicColorScheme(darkTheme: Boolean): ColorScheme? = null
