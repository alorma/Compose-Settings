import androidx.compose.runtime.Composable
import com.alorma.compose.settings.sample.shared.SettingsScreen
import com.alorma.compose.settings.sample.shared.theme.ComposeSettingsTheme
import com.alorma.compose.settings.storage.base.getValue
import com.alorma.compose.settings.storage.base.setValue
import com.alorma.compose.settings.storage.disk.rememberBooleanSettingState
import com.russhwolf.settings.Settings

@Composable
fun App() {
  val settings = Settings()
  var darkModeState by rememberBooleanSettingState(
    key = "darkMode",
    defaultValue = false,
    settings = settings,
  )
  ComposeSettingsTheme(
    darkTheme = darkModeState,
  ) {
    SettingsScreen(
      settings = settings,
      darkTheme = darkModeState,
      onDarkThemeChange = { darkModeState = it }
    )
  }
}