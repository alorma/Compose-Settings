plugins {
  alias(libs.plugins.kotlinMultiplatform)
  alias(libs.plugins.jetbrainsCompose)
  alias(libs.plugins.composeCompiler)
}

kotlin {
  js {
    browser {
      commonWebpackConfig {
        outputFileName = "composeApp.js"
      }
    }
    binaries.executable()
    useEsModules()
  }

  wasmJs {
    browser {
      commonWebpackConfig {
        outputFileName = "composeApp.js"
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
