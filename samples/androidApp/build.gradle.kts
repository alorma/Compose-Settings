plugins {
  alias(libs.plugins.androidApplication)
  alias(libs.plugins.jetbrainsCompose)
  alias(libs.plugins.composeCompiler)
}

android {
  namespace = libs.versions.namespace.get() + ".sample.shared.android"
  compileSdk =
    libs.versions.android.compileSdk
      .get()
      .toInt()

  defaultConfig {
    minSdk = libs.versions.android.minSdkSample
      .get()
      .toInt()

    targetSdk = libs.versions.android.targetSdk
      .get()
      .toInt()

    versionCode = 1
    versionName = "1.0"
  }

  packaging {
    resources {
      excludes += "/META-INF/{AL2.0,LGPL2.1}"
    }
  }

  buildFeatures {
    compose = true
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
}

composeCompiler {
  includeSourceInformation = true
}

dependencies {
  implementation(libs.androidx.activity.compose)

  implementation(libs.compose.ui)
  implementation(libs.compose.foundation)
  implementation(libs.compose.ui.tooling)

  implementation(projects.samples.shared)
}
