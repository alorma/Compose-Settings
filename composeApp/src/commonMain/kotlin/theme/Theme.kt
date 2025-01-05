package theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import com.alorma.compose.settings.storage.base.SettingValueState
import com.alorma.compose.settings.storage.disk.rememberBooleanSettingState
import com.alorma.compose.settings.storage.memory.rememberMemoryBooleanSettingState

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
  darkModeState: SettingValueState<Boolean> = rememberMemoryBooleanSettingState(),
  content: @Composable () -> Unit
) {

  MaterialTheme(
    colorScheme = if (darkModeState.value) {
      DarkColorScheme
    } else {
      LightColorScheme
    },
    typography = Typography,
    content = content
  )
}
