package com.alorma.compose.settings.storage.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore

val Context.composeSettingsDataStore: DataStore<Preferences> by preferencesDataStore(name = "compose_settings_datastore")