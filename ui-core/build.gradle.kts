plugins {
  id("compose.library")
}

kotlin {
  androidLibrary {
    namespace = libs.versions.namespace.get() + ".ui.core"
  }
}
