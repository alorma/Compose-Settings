plugins {
  alias(libs.plugins.kotlinMultiplatform)
  alias(libs.plugins.androidApplication)
  alias(libs.plugins.jetbrainsCompose)
  alias(libs.plugins.composeCompiler)
  alias(libs.plugins.detekt)
}

kotlin {

  androidTarget()

  applyDefaultHierarchyTemplate()

  sourceSets {
    androidMain.dependencies {
      implementation(libs.androidx.activity.compose)

      implementation(compose.ui)
      implementation(compose.foundation)
      implementation(compose.material3)
      implementation(compose.uiTooling)

      implementation(projects.samples.shared)
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
    targetSdk = libs.versions.android.targetSdk.get().toInt()

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
  composeOptions {
    experimentalProperties["android.experimental.enableScreenshotTest"] = true
  }
}

composeCompiler {
  includeSourceInformation = true
}

dependencies {
  detektPlugins(libs.compose.detekt.rules)
}