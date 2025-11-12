plugins {
  id("compose.library")
}

// Set the namespace suffix for this module
extra["namespaceSuffix"] = ".ui"

kotlin {
  sourceSets {
    commonMain.dependencies {
      api(projects.uiBase)
    }
  }
}
