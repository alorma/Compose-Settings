import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl

plugins {
  alias(libs.plugins.kotlinMultiplatform)
  alias(libs.plugins.jetbrainsCompose)
  alias(libs.plugins.composeCompiler)
  alias(libs.plugins.detekt)
}

kotlin {
  @OptIn(ExperimentalWasmDsl::class)
  wasmJs {
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
    wasmJsMain.dependencies {
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
