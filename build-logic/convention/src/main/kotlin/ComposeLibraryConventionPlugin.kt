import com.android.build.api.dsl.androidLibrary
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.*
import org.jetbrains.compose.ComposeExtension
import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

class ComposeLibraryConventionPlugin : Plugin<Project> {
  override fun apply(target: Project) {
    with(target) {
      with(pluginManager) {
        apply("org.jetbrains.kotlin.multiplatform")
        apply("com.android.kotlin.multiplatform.library")
        apply("org.jetbrains.compose")
        apply("org.jetbrains.kotlin.plugin.compose")
        apply("org.jetbrains.dokka")
        apply("io.gitlab.arturbosch.detekt")
        apply("org.jlleitschuh.gradle.ktlint")
      }

      // Apply publish script
      apply(mapOf("from" to "${rootProject.projectDir}/scripts/publish-module.gradle"))

      val libs = extensions.getByType<org.gradle.api.artifacts.VersionCatalogsExtension>().named("libs")
      val compose = extensions.getByType<ComposeExtension>()

      extensions.configure<KotlinMultiplatformExtension> {
        applyDefaultHierarchyTemplate()
        withSourcesJar()

        androidLibrary {
          // Default namespace - modules should override this with their unique namespace
          namespace = libs.findVersion("namespace").get().toString()

          compileSdk = libs.findVersion("android-compileSdk").get().toString().toInt()
          minSdk = libs.findVersion("android-minSdk").get().toString().toInt()

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

        iosX64()
        iosArm64()
        iosSimulatorArm64()

        js(IR) {
          browser()
        }

        @OptIn(ExperimentalWasmDsl::class)
        wasmJs {
          browser()
        }

        sourceSets.apply {
          val androidMain by getting {
            dependencies {
              implementation(libs.findLibrary("androidx-preference-preference").get())
              implementation(libs.findLibrary("androidx-preference-ktx").get())
            }
          }

          val commonMain by getting {
            dependencies {
              implementation(compose.dependencies.runtime)
              implementation(compose.dependencies.foundation)
              api(compose.dependencies.material3)
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
