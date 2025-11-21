import com.android.build.api.dsl.ApplicationExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.jetbrains.compose.ComposeExtension
import org.jetbrains.compose.desktop.DesktopExtension
import org.jetbrains.compose.desktop.application.dsl.TargetFormat

class VersioningConventionPlugin : Plugin<Project> {
  override fun apply(target: Project) {
    with(target) {
      val libVersion = findProperty("libVersion")?.toString() ?: "1.0.0"

      // Apply version to project
      version = libVersion

      // Configure Android application version if Android plugin is applied
      pluginManager.withPlugin("com.android.application") {
        extensions.configure<ApplicationExtension> {
          defaultConfig {
            versionName = libVersion
            versionCode = calculateVersionCode(libVersion)
          }
        }
      }

      // Configure Desktop application version if Compose Desktop plugin is applied
      pluginManager.withPlugin("org.jetbrains.compose") {
        extensions.configure<ComposeExtension> {
          val desktopExtension = this as? DesktopExtension
          desktopExtension?.application {
            nativeDistributions {
              packageVersion = libVersion
            }
          }
        }
      }
    }
  }

  /**
   * Calculate versionCode from semantic version
   * Formula: major * 1,000,000 + minor * 1,000 + patch
   * Example: 2.15.0 -> 2,015,000
   */
  private fun calculateVersionCode(version: String): Int {
    val versionParts = version.split(".")
    return if (versionParts.size >= 3) {
      val major = versionParts[0].toIntOrNull() ?: 0
      val minor = versionParts[1].toIntOrNull() ?: 0
      // Handle versions like "2.15.0-SNAPSHOT" by taking only the numeric part
      val patch = versionParts[2].split("-")[0].toIntOrNull() ?: 0
      major * 1_000_000 + minor * 1_000 + patch
    } else {
      1
    }
  }
}