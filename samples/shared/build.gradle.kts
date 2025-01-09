import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl

plugins {
  alias(libs.plugins.kotlinMultiplatform)
  alias(libs.plugins.androidLibrary)
  alias(libs.plugins.jetbrainsCompose)
  alias(libs.plugins.composeCompiler)
  alias(libs.plugins.detekt)
}

version = "1.0-SNAPSHOT"

kotlin {
  androidTarget()
  jvm("desktop")

  js(IR) {
    browser()
    useEsModules()
  }

  @OptIn(ExperimentalWasmDsl::class)
  wasmJs { browser() }

  listOf(
    iosX64(),
    iosArm64(),
    iosSimulatorArm64()
  ).forEach { iosTarget ->
    iosTarget.binaries.framework {
      baseName = "shared"
      isStatic = true
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

    val desktopMain by getting
    desktopMain.dependencies {
      api(compose.desktop.currentOs)
      implementation(compose.desktop.common)
    }
  }
}

android {
  namespace = libs.versions.namespace.get() + ".sample.shared"
  compileSdk = libs.versions.android.compileSdk.get().toInt()

  sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
  sourceSets["main"].res.srcDirs("src/androidMain/res")
  sourceSets["main"].resources.srcDirs("src/commonMain/resources")

  defaultConfig {
    minSdk = libs.versions.android.minSdkSample.get().toInt()
  }
}

composeCompiler {
  includeSourceInformation = true
}

dependencies {
  detektPlugins(libs.compose.detekt.rules)
}