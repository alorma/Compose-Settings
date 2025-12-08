plugins {
  id("compose.library")
}

kotlin {
  androidLibrary {
    namespace = libs.versions.namespace.get() + ".ui.expressive"
  }

  sourceSets {
    commonMain.dependencies {
      api(projects.uiBase)
      // Use material3-expressive instead of standard material3
      api(libs.compose.material3.expressive)
    }
  }
}
