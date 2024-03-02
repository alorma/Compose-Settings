package com.alorma.compose.settings.sample.shared

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.alorma.compose.settings.storage.disk.rememberBooleanSettingState
import com.alorma.compose.settings.storage.disk.rememberTriStateSetting
import com.alorma.compose.settings.storage.memory.rememberMemoryBooleanSettingState
import com.alorma.compose.settings.storage.memory.rememberMemoryTriStateSettingState
import com.alorma.compose.settings.ui.SettingsCheckbox
import com.alorma.compose.settings.ui.SettingsGroup
import com.alorma.compose.settings.ui.SettingsMenuLink
import com.alorma.compose.settings.ui.SettingsSwitch
import com.alorma.compose.settings.ui.SettingsTriStateCheckbox
import com.russhwolf.settings.Settings

@Composable
fun SettingsScreen(settings: Settings) {
  Scaffold { padding ->
    val scrollState = rememberScrollState()
    Column(
      modifier = Modifier
        .consumeWindowInsets(padding)
        .verticalScroll(scrollState)
        .padding(top = padding.calculateTopPadding()),
    ) {
      val iconState = rememberMemoryBooleanSettingState()
      SettingsSwitch(
        state = iconState.value,
        title = { Text(text = "Show icon") },
        onCheckedChange = { iconState.value = it },
      )

      SettingsSwitchSampleSection(settings, iconState.value)
      SettingsCheckboxSampleSection(settings, iconState.value)
      SettingsTriStateCheckboxSampleSection(settings, iconState.value)
      SettingsMenuLinkSectionSample(iconState.value)
    }
  }
}

@Composable
private fun SettingsSwitchSampleSection(settings: Settings, showIcon: Boolean) {
  SampleSection(title = "SettingsSwitch Tile") {
    val switchMemoryState = rememberMemoryBooleanSettingState()
    SettingsSwitch(
      state = switchMemoryState.value,
      title = { Text(text = "Switch") },
      subtitle = { Text(text = "Memory state") },
      onCheckedChange = { switchMemoryState.value = it },
    )
    val switchDiskState = rememberBooleanSettingState(
      key = "switchDiskState",
      settings = settings,
    )
    SettingsSwitch(
      state = switchDiskState.value,
      title = { Text(text = "Switch") },
      subtitle = { Text(text = "Disk state") },
      onCheckedChange = { switchDiskState.value = it },
    )
  }
}

@Composable
private fun SettingsCheckboxSampleSection(settings: Settings, showIcon: Boolean) {
  SampleSection(title = "SettingsCheckbox Tile") {
    val checkboxMemoryState = rememberMemoryBooleanSettingState()
    SettingsCheckbox(
      state = checkboxMemoryState.value,
      title = { Text(text = "Checkbox") },
      subtitle = { Text(text = "Memory state") },
      icon = iconSampleOrNull(showIcon),
      onCheckedChange = { checkboxMemoryState.value = it },
    )
    val checkboxDiskState = rememberBooleanSettingState(
      key = "checkboxDiskState",
      settings = settings,
    )
    SettingsCheckbox(
      state = checkboxDiskState.value,
      title = { Text(text = "Checkbox") },
      subtitle = { Text(text = "Disk state") },
      icon = iconSampleOrNull(showIcon),
      onCheckedChange = { checkboxDiskState.value = it },
    )
  }
}

@Composable
private fun SettingsTriStateCheckboxSampleSection(settings: Settings, showIcon: Boolean) {
  SampleSection(title = "SettingsTriStateCheckbox Tile") {
    val triStateCheckboxMemoryState = rememberMemoryTriStateSettingState()
    SettingsTriStateCheckbox(
      state = triStateCheckboxMemoryState.value,
      title = { Text(text = "TriStateCheckbox") },
      subtitle = { Text(text = "Memory") },
      icon = iconSampleOrNull(showIcon),
      onCheckedChange = { newState -> triStateCheckboxMemoryState.value = newState },
    )
    val triStateCheckboxDiskState = rememberTriStateSetting(
      key = "triStateCheckbox",
      settings = settings,
    )
    SettingsTriStateCheckbox(
      state = triStateCheckboxDiskState.value,
      title = { Text(text = "TriStateCheckbox") },
      subtitle = { Text(text = "Disk") },
      icon = iconSampleOrNull(showIcon),
      onCheckedChange = { newState -> triStateCheckboxDiskState.value = newState },
    )

    val child1State = rememberMemoryBooleanSettingState(defaultValue = false)
    val child2State = rememberMemoryBooleanSettingState(defaultValue = true)
    val child3State = rememberMemoryBooleanSettingState(defaultValue = false)

    val allChildStates = derivedStateOf {
      listOf(
        child1State.value,
        child2State.value,
        child3State.value,
      )
    }

    val areAllChildEnabled = allChildStates.value.all { it }
    val areNoneChildEnabled = allChildStates.value.none { it }

    val areSomeChildEnabled = !areAllChildEnabled && !areNoneChildEnabled

    val triStateWithChildState = if (areSomeChildEnabled) {
      null
    } else {
      areAllChildEnabled || !areNoneChildEnabled
    }

    SettingsTriStateCheckbox(
      state = triStateWithChildState,
      title = { Text(text = "TriStateCheckbox") },
      subtitle = { Text(text = "With child checkboxes") },
      icon = iconSampleOrNull(showIcon),
      onCheckedChange = { newState ->
        child1State.value = newState
        child2State.value = newState
        child3State.value = newState
      },
    )
    ElevatedCard(
      elevation = CardDefaults.elevatedCardElevation(defaultElevation = 4.dp),
    ) {
      SettingsCheckbox(
        modifier = Modifier.padding(start = 16.dp, end = 32.dp),
        state = child1State.value,
        title = { Text(text = "Child #1") },
        icon = iconSampleOrNull(showIcon),
        onCheckedChange = { child1State.value = it },
      )
      SettingsCheckbox(
        modifier = Modifier.padding(start = 16.dp, end = 32.dp),
        state = child2State.value,
        title = { Text(text = "Child #2") },
        icon = iconSampleOrNull(showIcon),
        onCheckedChange = { child2State.value = it },
      )
      SettingsCheckbox(
        modifier = Modifier.padding(start = 16.dp, end = 32.dp),
        state = child3State.value,
        title = { Text(text = "Child #3") },
        icon = iconSampleOrNull(showIcon),
        onCheckedChange = { child3State.value = it },
      )
    }
  }
}

@Composable
private fun SettingsMenuLinkSectionSample(
  showIcon: Boolean,
) {
  SampleSection(title = "SettingsSwitch Tile") {
    val actionState = rememberMemoryBooleanSettingState()

    SettingsSwitch(
      state = actionState.value,
      title = { Text(text = "Show action") },
      onCheckedChange = { actionState.value = it },
    )

    SettingsMenuLink(
      title = { Text(text = "Menu") },
      onClick = { },
      icon = iconSampleOrNull(showIcon),
      action = if (!actionState.value) {
        null
      } else {
        {
          IconButton(
            onClick = {},
          ) {
            Icon(
              imageVector = Icons.Default.Build,
              contentDescription = null,
            )
          }
        }
      },
    )
    SettingsMenuLink(
      title = { Text(text = "Menu") },
      subtitle = { Text(text = "With subtitle") },
      onClick = { },
      icon = iconSampleOrNull(showIcon),
      action = if (!actionState.value) {
        null
      } else {
        {
          IconButton(
            onClick = {},
          ) {
            Icon(
              imageVector = Icons.Default.Build,
              contentDescription = null,
            )
          }
        }
      },
    )
  }
}

private fun iconSampleOrNull(iconState: Boolean): (@Composable () -> Unit)? = if (!iconState) {
  null
} else {
  {
    Icon(
      imageVector = Icons.Default.ThumbUp,
      contentDescription = null,
    )
  }
}

@Composable
private fun SampleSection(
  title: String,
  content: @Composable ColumnScope.() -> Unit,
) {
  SettingsGroup(
    contentPadding = PaddingValues(16.dp),
    title = { Text(text = title) },
  ) {
    ElevatedCard { content() }
  }
}
