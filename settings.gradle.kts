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

include(":composeApp")

include(":compose-settings-ui")
include(":compose-settings-storage-base")
include(":compose-settings-storage-disk")
include(":compose-settings-storage-memory")
