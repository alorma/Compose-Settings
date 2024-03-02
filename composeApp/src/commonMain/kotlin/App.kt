import androidx.compose.runtime.Composable
import com.alorma.compose.settings.sample.shared.SettingsScreen
import com.alorma.compose.settings.sample.shared.theme.ComposeSettingsTheme
import com.russhwolf.settings.Settings

@Composable
fun App() {
  val settings = Settings()
  ComposeSettingsTheme {
    SettingsScreen(settings = settings)
  }
}