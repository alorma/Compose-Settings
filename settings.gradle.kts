rootProject.name = "Compose-Settings"
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

pluginManagement {
  repositories {
    maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    google()
    gradlePluginPortal()
    mavenCentral()
  }
}

dependencyResolutionManagement {
  repositories {
    google()
    mavenCentral()
    maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
  }
}

include(":samples:shared")
include(":samples:desktopApp")
include(":samples:webApp")
include(":samples:iosApp")
include(":samples:androidApp")

include(":ui-base")
include(":ui-tiles")
include(":ui-tiles-extended")
