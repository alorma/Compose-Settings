import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
  alias(libs.plugins.kotlinMultiplatform)
  alias(libs.plugins.jetbrainsCompose)
  alias(libs.plugins.composeCompiler)
  alias(libs.plugins.composeHotReload)
}

kotlin {
  jvm()

  sourceSets {
    jvmMain.dependencies {
      implementation(projects.samples.shared)
    }
  }
}

compose.desktop {
  application {
    mainClass = "com.alorma.compose.settings.sample.shared.MainKt"

    nativeDistributions {
      targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
      packageName = libs.versions.namespace.get() + ".sample.shared"
      packageVersion = "1.0.0"
    }
  }
}
