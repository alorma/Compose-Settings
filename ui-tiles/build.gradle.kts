import org.jetbrains.compose.desktop.application.dsl.TargetFormat
import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl

plugins {
  alias(libs.plugins.kotlinMultiplatform)
  alias(libs.plugins.androidKmpLibrary)
  alias(libs.plugins.jetbrainsCompose)
  alias(libs.plugins.composeCompiler)
  alias(libs.plugins.dokka)
  alias(libs.plugins.detekt)
  alias(libs.plugins.ktlint)
}

apply(from = "${rootProject.projectDir}/scripts/publish-module.gradle")

kotlin {

  applyDefaultHierarchyTemplate()

  withSourcesJar()

  androidLibrary {
    namespace = libs.versions.namespace.get() + ".ui"
    compileSdk = libs.versions.android.compileSdk.get().toInt()
    minSdk = libs.versions.android.minSdk.get().toInt()
  }

  // jvm("desktop")

  iosX64()
  iosArm64()
  iosSimulatorArm64()

  js(IR) {
    browser()
  }

  @OptIn(ExperimentalWasmDsl::class)
  wasmJs {
    browser()
  }

  sourceSets {
    androidMain.dependencies {
      implementation(libs.androidx.preference.preference)
      implementation(libs.androidx.preference.ktx)
    }

    commonMain.dependencies {
      api(projects.uiBase)

      implementation(compose.runtime)
      implementation(compose.foundation)
      implementation(compose.material3)
    }
  }
}

/*
compose.desktop {
  application {
    nativeDistributions {
      targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
      packageName = libs.versions.namespace.get() + ".ui"
      packageVersion = "1.0.0"
    }
  }
}
 */

dependencies {
  detektPlugins(libs.compose.detekt.rules)
}
