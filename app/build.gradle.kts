import com.google.protobuf.gradle.generateProtoTasks
import com.google.protobuf.gradle.protoc

plugins {
    id("com.android.application")
    kotlin("android")
    id("com.google.protobuf") version "0.9.1"
}

android {
    compileSdk = 33

    defaultConfig {
        applicationId = "com.alorma.compose.settings.example"
        minSdk = 21
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"
    }

    compileOptions {
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
        kotlinCompilerExtensionVersion = libs.versions.composeCompiler.get()
    }

    lint {
        checkReleaseBuilds = false
        abortOnError = false
    }
  namespace = "com.alorma.compose.settings.example"
}

dependencies {
    //noinspection GradleDependency
    //implementation(projects.composeSettingsUi)
    implementation(projects.composeSettingsUiM3)

    implementation(projects.composeSettingsStorageBase)
    implementation(projects.composeSettingsStoragePreferences)
    implementation(projects.composeSettingsStorageDatastore)
    implementation(projects.composeSettingsStorageDatastoreProto)

    implementation(libs.androidx.datastore.preference.android)
    implementation(libs.androidx.datastore.proto.android)
    implementation(libs.protobuf.javalite)

    implementation(platform(libs.compose.bom))
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.compose.foundation.foundation)
    implementation(libs.compose.foundation.layout)
    implementation(libs.compose.material.material3)
    implementation(libs.compose.material.iconsextended)
    implementation(libs.compose.ui.ui)
    implementation(libs.compose.ui.tooling)
}

protobuf {
    protobuf.protoc {
        artifact = "com.google.protobuf:protoc:3.21.12"
    }

    // Generates the java Protobuf-lite code for the Protobufs in this project. See
    // https://github.com/google/protobuf-gradle-plugin#customizing-protobuf-compilation
    // for more information.
    protobuf.generateProtoTasks {
        all().forEach { task ->
            task.plugins{
                create("java") {
                    option("lite")
                }
            }
        }
    }
}
