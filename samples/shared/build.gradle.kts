import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl

plugins {
  alias(libs.plugins.kotlinMultiplatform)
  alias(libs.plugins.androidKmpLibrary)
  alias(libs.plugins.jetbrainsCompose)
  alias(libs.plugins.composeCompiler)
  alias(libs.plugins.detekt)
  alias(libs.plugins.ktlint)
}

version = "1.0-SNAPSHOT"

kotlin {
  androidLibrary {
    namespace = libs.versions.namespace.get() + ".samples.shared"
    compileSdk = libs.versions.android.compileSdk.get().toInt()
    minSdk = libs.versions.android.minSdk.get().toInt()
  }

  // jvm("desktop")

  js(IR) {
    browser()
    useEsModules()
  }

  @OptIn(ExperimentalWasmDsl::class)
  wasmJs { browser() }

  listOf(
    iosX64(),
    iosArm64(),
    iosSimulatorArm64(),
  ).forEach { iosTarget ->
    iosTarget.binaries.framework {
      baseName = "shared"
      isStatic = true
      binaryOption("bundleId", libs.versions.namespace.get() + ".sample")
    }
  }

  applyDefaultHierarchyTemplate()

  sourceSets {
    all {
      languageSettings {
        optIn("org.jetbrains.compose.resources.ExperimentalResourceApi")
      }
    }

    androidMain.dependencies {
      implementation(compose.uiTooling)
    }

    commonMain.dependencies {
      api(compose.ui)
      api(compose.runtime)
      api(compose.foundation)
      api(compose.material3)

      api(compose.components.resources)

      implementation(projects.uiBase)
      implementation(projects.uiTiles)
      implementation(projects.uiTilesExtended)
    }

    val jsMain by getting {
      dependencies {
        implementation(npm("uuid", "^9.0.1"))
      }
    }

    val wasmJsMain by getting {
      dependencies {
        implementation(npm("uuid", "^9.0.1"))
      }
    }

    /*
    val desktopMain by getting
    desktopMain.dependencies {
      api(compose.desktop.currentOs)
      implementation(compose.desktop.common)
    }
     */
  }
}

composeCompiler {
  includeSourceInformation = true
}

dependencies {
  detektPlugins(libs.compose.detekt.rules)
}
