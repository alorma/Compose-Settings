package theme

import androidx.compose.material3.ColorScheme
import androidx.compose.runtime.Composable

/**
 * WebAssembly doesn't support dynamic colors, so return null.
 */
@Composable
actual fun getDynamicColorScheme(darkTheme: Boolean): ColorScheme? = null
