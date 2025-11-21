plugins {
  id("compose.library")
}

kotlin {
  androidLibrary {
    namespace = libs.versions.namespace.get() + ".ui.tiles"
  }

  sourceSets {
    commonMain.dependencies {
      api(projects.uiBase)
    }
  }
}
