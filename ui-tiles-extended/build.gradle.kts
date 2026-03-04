plugins {
  id("compose.library")
}

kotlin {
  android {
    namespace = libs.versions.namespace.get() + ".ui.extended"
  }

  sourceSets {
    commonMain.dependencies {
      api(projects.uiTiles)
    }
  }
}
