plugins {
  alias(libs.plugins.kotlinMultiplatform)
  alias(libs.plugins.jetbrainsCompose)
  alias(libs.plugins.composeCompiler)
  alias(libs.plugins.detekt)
}

kotlin {
  js(IR) {
    moduleName = "composesettings"
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

dependencies {
  detektPlugins(libs.compose.detekt.rules)
}
