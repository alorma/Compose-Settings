import org.jetbrains.kotlin.gradle.tasks.AbstractKotlinCompile

plugins {
  id("compose.sample")
}

// Read the library version from gradle.properties
val libVersion: String by project

// Task to generate a Version.kt file containing the library version
// This allows the sample app to display the current version in the UI
val generateVersionFile by tasks.registering {
  val outputDir = layout.buildDirectory.dir("generated/kotlin")
  val outputFile = outputDir.get().file("com/alorma/compose/settings/sample/shared/Version.kt").asFile

  // Track the version as an input so the task only runs when the version changes
  inputs.property("libVersion", libVersion)
  outputs.file(outputFile)

  doLast {
    outputFile.parentFile.mkdirs()
    outputFile.writeText(
      """
      |package com.alorma.compose.settings.sample.shared
      |
      |object Version {
      |    const val LIB_VERSION = "$libVersion"
      |}
      |
      """.trimMargin(),
    )
  }
}

kotlin {
  sourceSets {
    commonMain {
      // Add the generated source directory to the source sets
      kotlin.srcDir(layout.buildDirectory.dir("generated/kotlin"))
      dependencies {
        implementation(projects.uiBase)
        implementation(projects.uiTiles)
        implementation(projects.uiTilesExtended)
        implementation(projects.uiTilesExpressive)
      }
    }
  }
}

// Ensure the version file is generated before any Kotlin compilation tasks
tasks.withType<AbstractKotlinCompile<*>>().configureEach {
  dependsOn(generateVersionFile)
}
