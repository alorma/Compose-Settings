import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
  `kotlin-dsl`
}

group = "com.alorma.compose.settings.buildlogic"

java {
  sourceCompatibility = JavaVersion.VERSION_17
  targetCompatibility = JavaVersion.VERSION_17
}

kotlin {
  compilerOptions {
    jvmTarget = JvmTarget.JVM_17
  }
}

dependencies {
  compileOnly(libs.android.gradlePlugin)
  compileOnly(libs.kotlin.gradlePlugin)
  compileOnly(libs.compose.gradlePlugin)
  compileOnly(libs.detekt.gradlePlugin)
  compileOnly(libs.ktlint.gradlePlugin)
  compileOnly(libs.dokka.gradlePlugin)
  compileOnly(libs.plugin.gradle.maven.publish)
}

gradlePlugin {
  plugins {
    register("composeLibrary") {
      id = "compose.library"
      implementationClass = "ComposeLibraryConventionPlugin"
    }
    register("composeSample") {
      id = "compose.sample"
      implementationClass = "ComposeSampleConventionPlugin"
    }
  }
}
