plugins {
    id("com.android.application")
    kotlin("android")
}

android {
    compileSdk = 30

    defaultConfig {
        applicationId = "com.alorma.composedrawer"
        minSdk = 21
        targetSdk = 30
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
        kotlinCompilerExtensionVersion = "1.0.2"
    }

    lint {
        isCheckReleaseBuilds = false
        isAbortOnError = false
    }
}

dependencies {
    coreLibraryDesugaring("com.android.tools:desugar_jdk_libs:1.1.5")

    implementation(project(":compose-settings"))

    implementation("androidx.activity:activity-compose:1.3.0")

    implementation("androidx.compose.foundation:foundation:1.0.2")
    implementation("androidx.compose.foundation:foundation-layout:1.0.2")
    implementation("androidx.compose.ui:ui:1.0.2")
    implementation("androidx.compose.material:material:1.0.2")
    implementation("androidx.compose.material:material-icons-extended:1.0.2")
    implementation("androidx.compose.ui:ui-tooling:1.0.2")
    implementation("androidx.navigation:navigation-compose:2.4.0-alpha05")
}