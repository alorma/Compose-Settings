plugins {
  id("compose.library")
}

kotlin {
  android {
    namespace = libs.versions.namespace.get() + ".ui.core"
  }

  sourceSets {
    commonMain.dependencies {
      api(libs.compose.material3)
    }
  }
}
