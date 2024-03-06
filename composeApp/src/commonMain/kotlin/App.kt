import androidx.compose.runtime.Composable
import theme.ComposeSettingsTheme
import com.russhwolf.settings.Settings

@Composable
fun App() {
  val settings = Settings()
  ComposeSettingsTheme {
    SettingsScreen(settings = settings)
  }
}
