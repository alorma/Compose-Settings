plugins {
  id("compose.library")
}

kotlin {
  androidLibrary {
    namespace = libs.versions.namespace.get() + ".ui.extended"
  }

  sourceSets {
    commonMain.dependencies {
      api(projects.uiBase)
    }
  }
}
