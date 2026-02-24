package com.alorma.compose.settings.sample.shared

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.alorma.compose.settings.sample.shared.internal.SampleData
import com.alorma.compose.settings.sample.shared.internal.SampleSection
import com.alorma.compose.settings.ui.expressive.SettingsButtonGroup
import com.alorma.compose.settings.ui.expressive.SettingsCheckbox
import com.alorma.compose.settings.ui.expressive.SettingsMenuLink
import com.alorma.compose.settings.ui.expressive.SettingsRadioButton
import com.alorma.compose.settings.ui.expressive.SettingsSwitch
import com.alorma.compose.settings.ui.expressive.SettingsTriStateCheckbox

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun ExpressiveMaterial3Samples() {
  Column(
    modifier = Modifier
      .verticalScroll(rememberScrollState()),
    verticalArrangement = Arrangement.spacedBy(8.dp),
  ) {
    val colors = ListItemDefaults.segmentedColors()

    SettingsSwitchSampleSectionExpressive()
    SettingsCheckboxSampleSectionExpressive()
    SettingsTriStateCheckboxSampleSectionExpressive()
    SettingsRadioButtonSampleSectionExpressive()
    SettingsMenuLinkSectionSampleExpressive()
    SettingsButtonGroupSample()
    SettingsGroupSectionSampleExpressive()
  }
}

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
private fun SettingsSwitchSampleSectionExpressive() {
  SampleSection(title = "SettingsSwitch (Expressive)") {
    val state = remember { mutableStateOf(false) }
    SettingsSwitch(
      state = state.value,
      title = { Text(text = "Switch") },
      subtitle = { Text(text = "Switch subtitle") },
      onCheckedChange = { state.value = it },
    )
  }
}

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
private fun SettingsCheckboxSampleSectionExpressive() {
  SampleSection(title = "SettingsCheckbox (Expressive)") {
    val state = remember { mutableStateOf(false) }
    SettingsCheckbox(
      state = state.value,
      title = { Text(text = "Checkbox") },
      subtitle = { Text(text = "Checkbox subtitle") },
      onCheckedChange = { state.value = it },
    )
  }
}

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
private fun SettingsRadioButtonSampleSectionExpressive() {
  SampleSection(title = "SettingsRadioButton (Expressive)") {
    val state = remember { mutableStateOf<String?>(null) }

    SampleData.items.forEach { sampleItem ->
      SettingsRadioButton(
        state = state.value == sampleItem.key,
        title = { Text(text = sampleItem.title) },
        subtitle = { Text(text = sampleItem.description) },
        onClick = { state.value = sampleItem.key },
      )
    }
  }
}

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
private fun SettingsTriStateCheckboxSampleSectionExpressive() {
  SampleSection(title = "SettingsTriStateCheckbox (Expressive)") {
    val child1State = remember { mutableStateOf(false) }
    val child2State = remember { mutableStateOf(true) }
    val child3State = remember { mutableStateOf(false) }

    val allChildStates =
      remember {
        derivedStateOf {
          listOf(
            child1State.value,
            child2State.value,
            child3State.value,
          )
        }
      }

    val areAllChildEnabled = allChildStates.value.all { it }
    val areNoneChildEnabled = allChildStates.value.none { it }

    val areSomeChildEnabled = !areAllChildEnabled && !areNoneChildEnabled

    val triStateWithChildState =
      if (areSomeChildEnabled) {
        null
      } else {
        areAllChildEnabled || !areNoneChildEnabled
      }

    SettingsTriStateCheckbox(
      state = triStateWithChildState,
      title = { Text(text = "TriStateCheckbox") },
      subtitle = { Text(text = "With child checkboxes") },
      onCheckedChange = { newState ->
        child1State.value = newState
        child2State.value = newState
        child3State.value = newState
      },
    )
    Column {
      SettingsCheckbox(
        modifier = Modifier.padding(start = 16.dp, end = 32.dp),
        state = child1State.value,
        title = { Text(text = "Child #1") },
        onCheckedChange = { child1State.value = it },
      )
      SettingsCheckbox(
        modifier = Modifier.padding(start = 16.dp, end = 32.dp),
        state = child2State.value,
        title = { Text(text = "Child #2") },
        onCheckedChange = { child2State.value = it },
      )
      SettingsCheckbox(
        modifier = Modifier.padding(start = 16.dp, end = 32.dp),
        state = child3State.value,
        title = { Text(text = "Child #3") },
        onCheckedChange = { child3State.value = it },
      )
    }
  }
}

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
private fun SettingsMenuLinkSectionSampleExpressive() {
  SampleSection(title = "SettingsMenuLink (Expressive)") {
    val actionState = remember { mutableStateOf(false) }

    SettingsSwitch(
      state = actionState.value,
      title = { Text(text = "Show action") },
      onCheckedChange = { actionState.value = it },
    )

    SettingsMenuLink(
      title = { Text(text = "Menu") },
      onClick = { },
    )
    SettingsMenuLink(
      title = { Text(text = "Menu") },
      subtitle = { Text(text = "With subtitle") },
      onClick = { },
    )
  }
}

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
private fun SettingsButtonGroupSample() {
  SampleSection(title = "SettingsButtonGroup (Expressive)") {
    val buttonGroupItems = listOf(1, 2, 3)
    val buttonGroupState = remember { mutableStateOf(3) }
    SettingsButtonGroup(
      title = { Text(text = "Button group") },
      items = buttonGroupItems,
      itemTitleMap = { item -> "#$item" },
      selectedItem = buttonGroupState.value,
      onItemSelected = { buttonGroupState.value = it },
      subtitle = { Text(text = "Selected value: ${buttonGroupState.value}") },
    )
  }
}

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
private fun SettingsGroupSectionSampleExpressive() {
  val groupEnabled = remember { mutableStateOf(true) }
  SampleSection(
    title = "SettingsGroup (Expressive)",
    enabled = groupEnabled.value,
  ) {
    SettingsSwitch(
      state = groupEnabled.value,
      title = { Text(text = "Group Enabled") },
      subtitle = { Text(text = "This Switch is always enabled") },
      enabled = true,
      onCheckedChange = { groupEnabled.value = it },
    )

    HorizontalDivider()

    SettingsMenuLink(
      title = { Text(text = "Menu") },
      onClick = { },
    )

    val switchState = remember { mutableStateOf(false) }
    SettingsSwitch(
      state = switchState.value,
      title = { Text(text = "Switch") },
      subtitle = { Text(text = "Switch subtitle") },
      onCheckedChange = { switchState.value = it },
    )

    val checkboxState = remember { mutableStateOf(false) }
    SettingsCheckbox(
      state = checkboxState.value,
      title = { Text(text = "Checkbox") },
      subtitle = { Text(text = "Checkbox subtitle") },
      onCheckedChange = { checkboxState.value = it },
    )

    val triSateCheckboxState = remember { mutableStateOf<Boolean?>(null) }
    SettingsTriStateCheckbox(
      state = triSateCheckboxState.value,
      title = { Text(text = "TriStateCheckbox") },
      subtitle = { Text(text = "With child checkboxes") },
      onCheckedChange = { newState -> triSateCheckboxState.value = newState },
    )

    val state = remember { mutableStateOf(false) }
    SettingsRadioButton(
      state = state.value,
      title = { Text(text = "RadioButton") },
      subtitle = { Text(text = "RadioButton subtitle") },
      onClick = { state.value = !state.value },
    )
  }
}
