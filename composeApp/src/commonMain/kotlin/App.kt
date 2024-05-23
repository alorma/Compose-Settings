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
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
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
) {
  val windowSizeClass = calculateWindowSizeClass()

  ComposeSettingsTheme {
    Scaffold(
      modifier = Modifier.fillMaxSize().then(modifier),
      topBar = { SampleTopBar() },
    ) {
      when (windowSizeClass.widthSizeClass) {
        WindowWidthSizeClass.Compact, WindowWidthSizeClass.Medium -> {
          SettingsScreen(modifier = Modifier.padding(it))
        }

        else -> {
          val drawerState = rememberDrawerState(DrawerValue.Open)
          val scope = rememberCoroutineScope()

          DismissibleNavigationDrawer(
            modifier = Modifier.padding(it),
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
) {
  TopAppBar(
    title = { Text(text = "Compose settings - Sample") },
    colors = TopAppBarDefaults.topAppBarColors(
      containerColor = MaterialTheme.colorScheme.secondaryContainer,
    ),
  )
}
