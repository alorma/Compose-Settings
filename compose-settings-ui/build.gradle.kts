import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
  alias(libs.plugins.kotlinMultiplatform)
  alias(libs.plugins.androidLibrary)
  alias(libs.plugins.jetbrainsCompose)
}

apply(from = "${rootProject.projectDir}/scripts/publish-module.gradle")

kotlin {
  applyDefaultHierarchyTemplate()

  withSourcesJar()

  androidTarget {
    publishLibraryVariants("release")
    compilations.all {
      kotlinOptions {
        jvmTarget = JavaVersion.VERSION_17.toString()
      }
    }
  }

  jvm("desktop")

  listOf(
    iosX64(),
    iosArm64(),
    iosSimulatorArm64()
  ).forEach { iosTarget ->
    iosTarget.binaries.framework {
      binaryOption("bundleId", libs.versions.namespace.get() + ".ui")
    }
  }

  sourceSets {
    androidMain.dependencies {
      implementation(libs.androidx.preference.preference)
      implementation(libs.androidx.preference.ktx)
    }

    commonMain.dependencies {
      api(projects.composeSettingsStorageBase)

      implementation(libs.androidx.annotation)
      implementation(compose.runtime)
      implementation(compose.foundation)
      implementation(compose.material3)
    }
  }
}

android {
  namespace = libs.versions.namespace.get() + ".ui"
  compileSdk = libs.versions.android.compileSdk.get().toInt()

  sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
  sourceSets["main"].res.srcDirs("src/androidMain/res")
  sourceSets["main"].resources.srcDirs("src/commonMain/resources")

  defaultConfig {
    minSdk = libs.versions.android.minSdk.get().toInt()
  }
  buildFeatures {
    compose = true
  }
  composeOptions {
    kotlinCompilerExtensionVersion = libs.versions.compose.compiler.get()
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
    debugImplementation(libs.compose.ui.tooling)
  }
}

compose.desktop {
  application {
    nativeDistributions {
      targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
      packageName = libs.versions.namespace.get() + ".ui"
      packageVersion = "1.0.0"
    }
  }
}