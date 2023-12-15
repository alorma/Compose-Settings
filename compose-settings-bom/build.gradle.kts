plugins {
  `java-platform`
  `maven-publish`
}

dependencies {
  constraints {
    api(projects.composeSettingsStorageBase)
    api(projects.composeSettingsStorageDisk)
    api(projects.composeSettingsStorageMemory)

    api(projects.composeSettingsUiM3)
  }
}
