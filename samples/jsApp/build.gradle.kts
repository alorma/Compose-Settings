plugins {
  alias(libs.plugins.kotlinMultiplatform)
  alias(libs.plugins.jetbrainsCompose)
  alias(libs.plugins.composeCompiler)
}

kotlin {
  js(IR) {
    browser {
      commonWebpackConfig {
        outputFileName = "composesettings.js"
      }
    }
    binaries.executable()
    useEsModules()
  }

  sourceSets {
    jsMain.dependencies {
      implementation(projects.samples.shared)
    }
  }
}

composeCompiler {
  includeSourceInformation = true
}
