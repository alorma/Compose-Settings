import androidx.compose.runtime.Composable
import com.alorma.compose.settings.sample.shared.SettingsScreen
import com.alorma.compose.settings.sample.shared.theme.ComposeSettingsTheme
import com.alorma.compose.settings.storage.disk.rememberBooleanSettingState
import com.russhwolf.settings.Settings

@Composable
fun App() {
  val settings = Settings()
  val darkModeState = rememberBooleanSettingState(
    key = "darkMode",
    defaultValue = false,
    settings = settings,
  )
  ComposeSettingsTheme(
    darkTheme = darkModeState.value,
  ) {
    SettingsScreen(
      settings = settings,
      darkTheme = darkModeState.value,
      onDarkThemeChange = { darkModeState.value = it }
    )
  }
}