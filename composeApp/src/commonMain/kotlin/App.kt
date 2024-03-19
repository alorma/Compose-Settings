import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.DismissibleDrawerSheet
import androidx.compose.material3.DismissibleNavigationDrawer
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import theme.ComposeSettingsTheme

@OptIn(
  ExperimentalMaterial3WindowSizeClassApi::class,
)
@Composable
fun App(
  modifier: Modifier = Modifier,
  isSystemInDark: Boolean = false,
) {
  val windowSizeClass = calculateWindowSizeClass()

  val darkMode = remember { mutableStateOf(isSystemInDark) }

  ComposeSettingsTheme() {
    Scaffold(
      modifier = Modifier.fillMaxSize().then(modifier),
      topBar = {
        SampleTopBar(
          darkMode = darkMode.value,
          onDarkModeChange = { newState -> darkMode.value = newState },
        )
      },
    ) {
      when (windowSizeClass.widthSizeClass) {
        WindowWidthSizeClass.Compact, WindowWidthSizeClass.Medium -> {
          SettingsScreen()
        }

        else -> {
          val drawerState = rememberDrawerState(DrawerValue.Open)
          val scope = rememberCoroutineScope()

          DismissibleNavigationDrawer(
            drawerState = drawerState,
            drawerContent = {
              DismissibleDrawerSheet { SettingsScreen() }
            },
            content = {
              Surface {
                Column(
                  modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                  horizontalAlignment = Alignment.CenterHorizontally,
                  verticalArrangement = Arrangement.Center,
                ) {
                  if (drawerState.isOpen) {
                    Button(onClick = { scope.launch { drawerState.close() } }) {
                      Text("Click to close")
                    }
                  } else {
                    Button(onClick = { scope.launch { drawerState.open() } }) {
                      Text("Click to open")
                    }
                  }
                }
              }
            }
          )
        }
      }
    }
  }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun SampleTopBar(
  darkMode: Boolean,
  onDarkModeChange: (Boolean) -> Unit,
) {
  TopAppBar(
    title = { Text(text = "Compose settings - Sample") },
    actions = { Switch(checked = darkMode, onCheckedChange = { newState -> onDarkModeChange(newState) }) },
    colors = TopAppBarDefaults.topAppBarColors(
      containerColor = MaterialTheme.colorScheme.secondaryContainer,
    ),
  )
}
