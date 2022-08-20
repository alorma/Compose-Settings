plugins {
  id("com.android.library")
  kotlin("android")
}

ext["PUBLISH_ARTIFACT_ID"] = "compose-settings"

apply(from = "${rootProject.projectDir}/scripts/publish-module.gradle")

android {
  compileSdk = 31

  defaultConfig {
    minSdk = 21
    targetSdk = 31
  }

  buildTypes {
    named("release") {
      isMinifyEnabled = false
    }
  }

  compileOptions {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
  }

  lint {
    checkReleaseBuilds = false
    abortOnError = false
  }

  buildFeatures {
    compose = true
  }

  composeOptions {
    kotlinCompilerExtensionVersion = libs.versions.composeCompiler.get()
  }

  kotlinOptions {
    jvmTarget = JavaVersion.VERSION_1_8.toString()

    freeCompilerArgs = freeCompilerArgs + listOf(
      "-Xopt-in=kotlin.RequiresOptIn",
      "-Xopt-in=kotlin.Experimental",
      "-Xuse-experimental=kotlin.Experimental",
      "-XXLanguage:+NonParenthesizedAnnotationsOnFunctionalTypes",
    )
  }
}

dependencies {

  api(projects.composeSettingsStorageBase)

  implementation(libs.compose.foundation.foundation)
  implementation(libs.compose.foundation.layout)
  implementation(libs.compose.material.material)
  implementation(libs.compose.ui.ui)
  implementation(libs.compose.ui.tooling)
}
