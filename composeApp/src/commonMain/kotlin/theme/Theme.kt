package theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

val DarkColorScheme = darkColorScheme(
  primary = Purple40,
  secondary = PurpleGrey40,
  tertiary = Pink40
)

val LightColorScheme = lightColorScheme(
  primary = Purple40,
  secondary = PurpleGrey40,
  tertiary = Pink40
)

@Composable
fun ComposeSettingsTheme(
  isSystemDark: Boolean = isSystemInDarkTheme(),
  content: @Composable () -> Unit
) {

  MaterialTheme(
    colorScheme = if (isSystemDark) {
      DarkColorScheme
    } else {
      LightColorScheme
    },
    typography = Typography,
    content = content
  )
}
