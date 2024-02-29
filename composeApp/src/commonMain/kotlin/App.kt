import androidx.compose.runtime.Composable
import com.alorma.compose.settings.sample.shared.SettingsScreen
import com.alorma.compose.settings.sample.shared.theme.ComposeSettingsTheme
import com.alorma.compose.settings.storage.base.getValue
import com.alorma.compose.settings.storage.base.setValue
import com.alorma.compose.settings.storage.memory.rememberMemoryBooleanSettingState

@Composable
fun App() {
  var darkModeState by rememberMemoryBooleanSettingState(defaultValue = false)
  ComposeSettingsTheme(
    darkTheme = darkModeState,
  ) {
    SettingsScreen(
      darkTheme = darkModeState,
      onDarkThemeChange = { darkModeState = it }
    )
  }
}