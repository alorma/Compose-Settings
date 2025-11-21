import com.android.build.api.dsl.androidLibrary
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.*
import org.jetbrains.compose.ComposeExtension
import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

class ComposeSampleConventionPlugin : Plugin<Project> {
  override fun apply(target: Project) {
    with(target) {
      with(pluginManager) {
        apply("org.jetbrains.kotlin.multiplatform")
        apply("com.android.kotlin.multiplatform.library")
        apply("org.jetbrains.compose")
        apply("org.jetbrains.kotlin.plugin.compose")
        apply("io.gitlab.arturbosch.detekt")
        apply("org.jlleitschuh.gradle.ktlint")
      }

      version = "1.0-SNAPSHOT"

      val libs = extensions.getByType<org.gradle.api.artifacts.VersionCatalogsExtension>().named("libs")
      val compose = extensions.getByType<ComposeExtension>()

      extensions.configure<KotlinMultiplatformExtension> {
        androidLibrary {
          namespace = libs.findVersion("namespace").get().toString() + ".sample.shared"
          compileSdk = libs.findVersion("android-compileSdk").get().toString().toInt()
          minSdk = libs.findVersion("android-minSdkSample").get().toString().toInt()

          packaging {
            resources {
              excludes += "/META-INF/{AL2.0,LGPL2.1}"
            }
          }

          lint {
            checkReleaseBuilds = false
            abortOnError = false
          }

          compilations.configureEach {
            compileTaskProvider.configure {
              compilerOptions {
                jvmTarget.set(JvmTarget.JVM_17)
              }
            }
          }
        }

        jvm("desktop")

        js(IR) {
          browser()
          useEsModules()
        }

        @OptIn(ExperimentalWasmDsl::class)
        wasmJs { browser() }

        listOf(
          iosX64(),
          iosArm64(),
          iosSimulatorArm64(),
        ).forEach { iosTarget ->
          iosTarget.binaries.framework {
            baseName = "shared"
            isStatic = true
            binaryOption("bundleId", libs.findVersion("namespace").get().toString() + ".sample")
          }
        }

        applyDefaultHierarchyTemplate()

        sourceSets.apply {
          all {
            languageSettings {
              optIn("org.jetbrains.compose.resources.ExperimentalResourceApi")
            }
          }

          val androidMain by getting {
            dependencies {
              implementation(compose.dependencies.uiTooling)
            }
          }

          val commonMain by getting {
            dependencies {
              api(compose.dependencies.ui)
              api(compose.dependencies.runtime)
              api(compose.dependencies.foundation)
              api(libs.findLibrary("compose-material3-expressive").get())
              api(compose.dependencies.components.resources)
            }
          }

          val jsMain by getting {
            dependencies {
              implementation(npm("uuid", "^9.0.1"))
            }
          }

          val wasmJsMain by getting {
            dependencies {
              implementation(npm("uuid", "^9.0.1"))
            }
          }

          val desktopMain by getting {
            dependencies {
              api(compose.dependencies.desktop.currentOs)
              implementation(compose.dependencies.desktop.common)
            }
          }
        }
      }

      // Configure detekt
      dependencies {
        add("detektPlugins", libs.findLibrary("compose-detekt-rules").get())
      }
    }
  }
}
