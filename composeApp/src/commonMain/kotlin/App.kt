import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.alorma.compose.settings.storage.disk.rememberBooleanSettingState
import com.russhwolf.settings.Settings
import theme.ComposeSettingsTheme

@OptIn(
  ExperimentalMaterial3WindowSizeClassApi::class,
  ExperimentalMaterial3Api::class,
)
@Composable
fun App() {
  val windowSizeClass = calculateWindowSizeClass()

  val settings = Settings()

  val darkMode = rememberBooleanSettingState(
    key = "darkMode",
    defaultValue = false,
    settings = settings,
  )

  ComposeSettingsTheme(isSystemDark = darkMode.value) {
    Scaffold(
      modifier = Modifier.fillMaxSize(),
      topBar = {
        TopAppBar(
          title = { Text(text = "Compose settings - Sample") },
          actions = {
            Switch(checked = darkMode.value, onCheckedChange = { newState ->
              darkMode.value = newState
            })
          },
        )
      },
    ) {
      when (windowSizeClass.widthSizeClass) {
        WindowWidthSizeClass.Compact, WindowWidthSizeClass.Medium -> {
          SettingsScreen(settings = settings)
        }

        else -> {
          Column(
            modifier = Modifier.width(360.dp),
          ) {
            SettingsScreen(settings = settings)
          }
        }
      }
    }

  }
}