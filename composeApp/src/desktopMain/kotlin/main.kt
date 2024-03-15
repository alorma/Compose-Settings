import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

fun main() = application {
  Window(
    onCloseRequest = ::exitApplication,
    title = "Compose settings - sample",
  ) {
    App(isSystemInDark = isSystemInDarkTheme())
  }
}
