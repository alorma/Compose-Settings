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

  sourceSets {
    androidMain.dependencies {
      implementation(libs.androidx.preference.preference)
      implementation(libs.androidx.preference.ktx)
    }

    commonMain.dependencies {
      api(projects.storageBase)

      implementation(compose.runtime)
      implementation(compose.foundation)
    }
  }
}

android {
  namespace = libs.versions.namespace.get() + ".android.preferences"
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

dependencies {
  detektPlugins(libs.compose.detekt.rules)
}