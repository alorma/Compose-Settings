import com.android.build.api.dsl.androidLibrary
import org.jetbrains.compose.desktop.application.dsl.TargetFormat
import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl

plugins {
  alias(libs.plugins.kotlinMultiplatform)
  alias(libs.plugins.kotlinMultiplatformAndroidLibrary)
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
    namespace = libs.versions.namespace.get() + ".ui.base"
    compileSdk =
      libs.versions.android.compileSdk
        .get()
        .toInt()

    minSdk =
      libs.versions.android.minSdk
        .get()
        .toInt()

    packaging {
      resources {
        excludes += "/META-INF/{AL2.0,LGPL2.1}"
      }
    }

    lint {
      checkReleaseBuilds = false
      abortOnError = false
    }

    compilations.configureEach {
      compilerOptions.configure {
        jvmTarget.set(
          org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_17,
        )
      }
    }
  }

  jvm("desktop")

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
      implementation(compose.runtime)
      implementation(compose.foundation)
      api(compose.material3)
    }
  }
}

compose.desktop {
  application {
    nativeDistributions {
      targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
      packageName = libs.versions.namespace.get() + ".ui.base"
      packageVersion = "1.0.0"
    }
  }
}

dependencies {
  detektPlugins(libs.compose.detekt.rules)
}
