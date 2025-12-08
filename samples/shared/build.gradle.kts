plugins {
  id("compose.sample")
}

kotlin {
  sourceSets {
    commonMain.dependencies {
      implementation(projects.uiBase)
      implementation(projects.uiTiles)
      implementation(projects.uiTilesExtended)
      implementation(projects.uiTilesExpressive)
    }
  }
}
