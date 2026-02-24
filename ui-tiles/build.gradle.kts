plugins {
  id("compose.library")
}

kotlin {
  androidLibrary {
    namespace = libs.versions.namespace.get() + ".ui.tiles"
  }

  sourceSets {
    commonMain.dependencies {
      api(projects.uiCore)
      api(libs.compose.material3)
    }
  }
}
