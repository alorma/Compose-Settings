import com.android.build.api.dsl.KotlinMultiplatformAndroidLibraryExtension
import com.vanniktech.maven.publish.MavenPublishBaseExtension
import org.gradle.api.GradleException
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.publish.maven.MavenPom
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.findByType
import org.gradle.kotlin.dsl.getByType
import org.gradle.kotlin.dsl.getValue
import org.gradle.kotlin.dsl.getting
import org.jetbrains.compose.ComposeExtension
import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl
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
        apply("com.vanniktech.maven.publish")
        apply("signing")
      }

      val libs = extensions.getByType<org.gradle.api.artifacts.VersionCatalogsExtension>().named("libs")
      val compose = extensions.getByType<ComposeExtension>()

      val defaultNamespace = libs.findVersion("namespace").get().toString()

      // Configure Maven Publishing
      extensions.configure<MavenPublishBaseExtension> {
        publishToMavenCentral()
        signAllPublications()

        pom { configurePom(this) }
      }

      // Set group and version from properties
      group = findProperty("libGroup")?.toString() ?: "com.alorma.compose.settings"
      version = findProperty("libVersion")?.toString() ?: "0.0.1"

      extensions.configure<KotlinMultiplatformExtension> {
        applyDefaultHierarchyTemplate()
        withSourcesJar()

        extensions.configure<KotlinMultiplatformAndroidLibraryExtension> {
          // Default namespace - modules MUST override this with their unique namespace
          namespace = defaultNamespace

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

      // Validate that module has overridden the namespace
      afterEvaluate {
        extensions.findByType<KotlinMultiplatformAndroidLibraryExtension>()?.let { kotlin ->
          val currentNamespace = kotlin.namespace
          if (currentNamespace == defaultNamespace) {
            throw GradleException(
              """
                |Module '${project.path}' must override the namespace!
                |
                |Each library module must set a unique namespace in its build.gradle.kts:
                |
                |  kotlin {
                |    androidLibrary {
                |      namespace = libs.versions.namespace.get() + ".your.unique.suffix"
                |    }
                |  }
                |
                |Current namespace: $currentNamespace (this is the default and will cause conflicts)
                """.trimMargin()
            )
          }
        }
      }
    }
  }

  private fun configurePom(pom: MavenPom) {
    pom.apply {
      name.set("Compose Settings")
      description.set("Compose settings UI components for Kotlin Multiplatform")
      url.set("https://github.com/alorma/Compose-Settings")

      licenses {
        license {
          name.set("MIT License")
          url.set("https://github.com/alorma/Compose-Settings/blob/main/LICENSE")
        }
      }

      developers {
        developer {
          id.set("alorma")
          name.set("Bernat Borrás")
          email.set("bernatbor15@gmail.com")
        }
      }

      scm {
        connection.set("scm:git:github.com/alorma/Compose-Settings.git")
        developerConnection.set("scm:git:ssh://github.com/alorma/Compose-Settings.git")
        url.set("https://github.com/alorma/Compose-Settings/tree/main")
      }
    }
  }
}
