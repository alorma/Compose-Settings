import org.jetbrains.kotlin.gradle.targets.js.yarn.YarnLockMismatchReport
import org.jetbrains.kotlin.gradle.targets.js.yarn.YarnRootExtension

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
      classpath(libs.plugin.gradle.maven.publish)
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


rootProject.plugins.withType(org.jetbrains.kotlin.gradle.targets.js.yarn.YarnPlugin::class.java) {
  rootProject.the<YarnRootExtension>().yarnLockMismatchReport = YarnLockMismatchReport.NONE
  rootProject.the<YarnRootExtension>().reportNewYarnLock = false // true
  rootProject.the<YarnRootExtension>().yarnLockAutoReplace = false // true
}

apply(from = "${rootDir}/scripts/publish-root.gradle")