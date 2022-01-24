package com.alorma.compose.settings

object Versions {
    const val compose = "1.0.5"
}

object Libs {
    object AndroidX {
        object Compose {
            const val foundation = "androidx.compose.foundation:foundation:${Versions.compose}"
            const val foundationLayout =
                "androidx.compose.foundation:foundation-layout:${Versions.compose}"
            const val material = "androidx.compose.material:material:${Versions.compose}"
            const val materialIconsExtended =
                "androidx.compose.material:material-icons-extended:${Versions.compose}"
            const val ui = "androidx.compose.ui:ui:${Versions.compose}"
            const val uiTooling = "androidx.compose.ui:ui-tooling:${Versions.compose}"
        }
    }
}
