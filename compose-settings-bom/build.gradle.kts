plugins {
  `java-platform`
  id("com.vanniktech.maven.publish")
}

dependencies {
  constraints {
    api(projects.composeSettingsStorageBase)
    api(projects.composeSettingsStorageDisk)
    api(projects.composeSettingsStorageMemory)

    api(projects.composeSettingsUi)
  }
}
