import com.alorma.compose.settings.Libs
import com.alorma.compose.settings.Versions

plugins {
    id("com.android.application")
    kotlin("android")
}

android {
    compileSdk = 31

    defaultConfig {
        applicationId = "com.alorma.compose.settings.example"
        minSdk = 21
        targetSdk = 31
        versionCode = 1
        versionName = "1.0"
    }

    compileOptions {
        isCoreLibraryDesugaringEnabled = true
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
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
    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = Versions.compose
    }

    lint {
        isCheckReleaseBuilds = false
        isAbortOnError = false
    }
}

dependencies {
    coreLibraryDesugaring("com.android.tools:desugar_jdk_libs:1.1.5")
    implementation(project(":compose-settings-ui"))
    implementation(project(":compose-settings-storage-base"))
    implementation(project(":compose-settings-storage-preferences"))

    implementation("androidx.activity:activity-compose:1.4.0")

    with(Libs.AndroidX.Compose) {
        implementation(foundation)
        implementation(foundationLayout)
        implementation(material)
        implementation(materialIconsExtended)
        implementation(ui)
        implementation(uiTooling)
    }

    implementation("androidx.navigation:navigation-compose:2.4.0-rc01")
}
