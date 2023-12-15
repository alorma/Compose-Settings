plugins {
  alias(libs.plugins.jetbrainsCompose) apply false
  alias(libs.plugins.androidApplication) apply false
  alias(libs.plugins.androidLibrary) apply false
  alias(libs.plugins.kotlinMultiplatform) apply false
  alias(libs.plugins.kotlinAndroid) apply false
}

buildscript {
  repositories {
    dependencies {
      classpath(libs.plugin.gradle.publish)
      classpath(libs.plugin.gradle.nexus.staging)
    }
  }
}

apply(plugin = "io.codearte.nexus-staging")
apply(plugin = "io.github.gradle-nexus.publish-plugin")

allprojects {
  repositories {
    google()
    mavenCentral()
  }
}

apply(from = "${rootDir}/scripts/publish-root.gradle")