plugins {
  id("com.android.library")
  kotlin("android")
}

apply(from = "${rootProject.projectDir}/scripts/publish-module.gradle")

android {
  compileSdk = 34

  defaultConfig {
    minSdk = 21
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
    kotlinCompilerExtensionVersion = libs.versions.compose.compiler.get()
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
  namespace = "com.alorma.compose.settings.storage.preferences"
}

dependencies {

  implementation(projects.composeSettingsStorageBase)

  implementation(libs.androidx.preference.preference)
  implementation(libs.androidx.preference.ktx)

  implementation(platform(libs.compose.bom))
  implementation(libs.compose.foundation.foundation)
  implementation(libs.compose.ui.tooling)
}
