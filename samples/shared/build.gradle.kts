plugins {
  id("compose.sample")
}

val libVersion: String by project

val generateVersionFile by tasks.registering {
  val outputDir = layout.buildDirectory.dir("generated/kotlin")
  val outputFile = outputDir.get().file("com/alorma/compose/settings/sample/shared/Version.kt").asFile

  outputs.file(outputFile)
  outputs.upToDateWhen { false }

  doLast {
    outputFile.parentFile.mkdirs()
    outputFile.writeText(
      """
      |package com.alorma.compose.settings.sample.shared
      |
      |object Version {
      |    const val LIB_VERSION = "$libVersion"
      |}
      |""".trimMargin()
    )
  }
}

kotlin {
  sourceSets {
    commonMain {
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

afterEvaluate {
  tasks.matching { it.name.startsWith("compileKotlin") }.configureEach {
    dependsOn(generateVersionFile)
  }
}
