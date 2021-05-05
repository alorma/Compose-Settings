plugins {
    id("com.android.library")
    kotlin("android")
}

ext["PUBLISH_ARTIFACT_ID"] = "compose-settings"

apply(from = "${rootProject.projectDir}/scripts/publish-module.gradle")

android {
    compileSdk = 30

    defaultConfig {
        minSdk = 23
        targetSdk = 30
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
        isCheckReleaseBuilds = false
        isAbortOnError = false
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.0.0-beta06"
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
        useIR = true
        freeCompilerArgs = freeCompilerArgs + listOf(
            "-Xopt-in=kotlin.RequiresOptIn",
            "-Xopt-in=kotlin.Experimental",
            "-Xuse-experimental=kotlin.Experimental"
        )
    }
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib:1.4.32")

    implementation("androidx.core:core-ktx:1.3.2")
    implementation("androidx.appcompat:appcompat:1.2.0")
    implementation("com.google.android.material:material:1.3.0")

    implementation("androidx.compose.foundation:foundation:1.0.0-beta06")
    implementation("androidx.compose.foundation:foundation-layout:1.0.0-beta06")
    implementation("androidx.compose.ui:ui:1.0.0-beta06")
    implementation("androidx.compose.material:material:1.0.0-beta06")
    implementation("androidx.compose.material:material-icons-extended:1.0.0-beta06")
    implementation("androidx.compose.ui:ui-tooling:1.0.0-beta06")

    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.3.1")
}
