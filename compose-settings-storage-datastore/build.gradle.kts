plugins {
  id("com.android.library")
  kotlin("android")
}

ext["PUBLISH_ARTIFACT_ID"] = "compose-settings-storage-datastore"

apply(from = "${rootProject.projectDir}/scripts/publish-module.gradle")

android {
  compileSdk = 34

  defaultConfig {
    minSdk = 21
    targetSdk = 34
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
      "-opt-in=kotlin.RequiresOptIn",
      "-opt-in=kotlin.Experimental",
      "-Xuse-experimental=kotlin.Experimental",
      "-XXLanguage:+NonParenthesizedAnnotationsOnFunctionalTypes",
    )
  }
  namespace = "com.alorma.compose.settings.storage.datastore"
}

dependencies {

  implementation(projects.composeSettingsStorageBase)

  implementation(libs.androidx.datastore.preference.core)
  implementation(libs.androidx.datastore.preference.android)

  implementation(platform(libs.compose.bom))
  implementation(libs.compose.foundation.foundation)
  implementation(libs.compose.ui.tooling)
}
