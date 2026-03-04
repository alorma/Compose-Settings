plugins {
  id("compose.library")
}

kotlin {
  android {
    namespace = libs.versions.namespace.get() + ".ui.expressive"
  }

  sourceSets {
    commonMain.dependencies {
      implementation(projects.uiCore)
      // Use material3-expressive instead of standard material3
      api(libs.compose.material3.expressive)
    }
  }
}
