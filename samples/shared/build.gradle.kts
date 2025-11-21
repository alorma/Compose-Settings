plugins {
  id("compose.sample")
}

val libVersion = project.findProperty("libVersion")?.toString() ?: "1.0.0"

kotlin {
  sourceSets {
    commonMain {
      dependencies {
        implementation(projects.uiBase)
        implementation(projects.uiTiles)
        implementation(projects.uiTilesExtended)
      }
    }
  }

  // Set version as a compilation argument
  targets.all {
    compilations.all {
      compileTaskProvider.configure {
        compilerOptions {
          freeCompilerArgs.add("-Xopt-in=kotlin.RequiresOptIn")
        }
      }
    }
  }
}

// Generate BuildConfig file with version
val generateBuildConfig by tasks.registering {
  val outputDir = layout.buildDirectory.dir("generated/buildconfig")
  outputs.dir(outputDir)

  inputs.property("version", libVersion)

  doLast {
    val file = outputDir.get().asFile.resolve("BuildConfig.kt")
    file.parentFile.mkdirs()
    file.writeText(
      """
      package com.alorma.compose.settings.sample.shared

      internal object BuildConfig {
        const val VERSION = "$libVersion"
      }
      """.trimIndent(),
    )
  }
}

kotlin.sourceSets.commonMain {
  kotlin.srcDir(generateBuildConfig.map { it.outputs.files.singleFile })
}
