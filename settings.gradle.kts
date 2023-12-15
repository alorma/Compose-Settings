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

include(":app")
include(":compose-settings-ui-m3")
include(":compose-settings-storage-base")
include(":compose-settings-storage-preferences")
include(":compose-settings-bom")
