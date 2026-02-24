plugins {
  id("compose.library")
}

kotlin {
  androidLibrary {
    namespace = libs.versions.namespace.get() + ".ui.base"
  }

  sourceSets {
    commonMain.dependencies {
      api(projects.uiCore)
    }
  }
}
