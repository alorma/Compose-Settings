plugins {
  `java-platform`
  `maven-publish`
}

dependencies {
  constraints {
    api(projects.composeSettingsStorageBase)
    api(projects.composeSettingsStoragePreferences)

    api(projects.composeSettingsUiM3)
  }
}
