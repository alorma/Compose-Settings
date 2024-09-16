import org.jetbrains.compose.desktop.application.dsl.TargetFormat
import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl

plugins {
  alias(libs.plugins.kotlinMultiplatform)
  alias(libs.plugins.androidLibrary)
  alias(libs.plugins.jetbrainsCompose)
  alias(libs.plugins.composeCompiler)
  alias(libs.plugins.detekt)
}

apply(from = "${rootProject.projectDir}/scripts/publish-module.gradle")

kotlin {
  
  applyDefaultHierarchyTemplate()

  withSourcesJar()

  androidTarget {
    publishLibraryVariants("release")
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
      api(projects.uiBase)

      implementation(compose.runtime)
      implementation(compose.foundation)
      implementation(compose.material3)
    }
  }
}

android {
  namespace = libs.versions.namespace.get() + ".ui.extended"
  compileSdk = libs.versions.android.compileSdk.get().toInt()

  sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
  sourceSets["main"].res.srcDirs("src/androidMain/res")
  sourceSets["main"].resources.srcDirs("src/commonMain/resources")

  defaultConfig {
    minSdk = libs.versions.android.minSdk.get().toInt()
  }
  packaging {
    resources {
      excludes += "/META-INF/{AL2.0,LGPL2.1}"
    }
  }
  lint {
    checkReleaseBuilds = false
    abortOnError = false
  }
  buildTypes {
    getByName("release") {
      isMinifyEnabled = false
    }
  }
  compileOptions {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
  }
  dependencies {
    debugImplementation(compose.uiTooling)
  }
}

compose.desktop {
  application {
    nativeDistributions {
      targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
      packageName = libs.versions.namespace.get() + ".ui.extended"
      packageVersion = "1.0.0"
    }
  }
}

dependencies {
  detektPlugins(libs.compose.detekt.rules)
}