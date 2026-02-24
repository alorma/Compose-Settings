package com.alorma.compose.settings.sample.shared

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.ListItemColors
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.state.ToggleableState
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
    val colors = ListItemDefaults.segmentedColors(
      containerColor = MaterialTheme.colorScheme.surfaceContainer,
    )

    SettingsMenuLinkSectionSampleExpressive(
      colors = colors,
    )
    SettingsSwitchSampleSectionExpressive(
      colors = colors,
    )
    SettingsCheckboxSampleSectionExpressive(
      colors = colors,
    )
    SettingsTriStateCheckboxSampleSectionExpressive(
      colors = colors,
    )
    SettingsRadioButtonSampleSectionExpressive(
      colors = colors,
    )
    SettingsButtonGroupSample(
      colors = colors,
    )
  }
}

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
private fun SettingsSwitchSampleSectionExpressive(
  colors: ListItemColors,
) {
  SampleSection(
    title = "SettingsSwitch (Expressive)",
    verticalArrangement = Arrangement.spacedBy(ListItemDefaults.SegmentedGap),
  ) {
    val state = remember { mutableStateOf(false) }
    SettingsSwitch(
      state = state.value,
      colors = colors,
      title = { Text(text = "Switch") },
      subtitle = { Text(text = "Switch subtitle") },
      onCheckedChange = { state.value = it },
    )
  }
}

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
private fun SettingsCheckboxSampleSectionExpressive(
  colors: ListItemColors,
) {
  SampleSection(
    title = "SettingsCheckbox (Expressive)",
    verticalArrangement = Arrangement.spacedBy(ListItemDefaults.SegmentedGap),
  ) {
    val state = remember { mutableStateOf(false) }
    SettingsCheckbox(
      state = state.value,
      colors = colors,
      title = { Text(text = "Checkbox") },
      subtitle = { Text(text = "Checkbox subtitle") },
      onCheckedChange = { state.value = it },
    )
  }
}

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
private fun SettingsRadioButtonSampleSectionExpressive(
  colors: ListItemColors,
) {
  SampleSection(
    title = "SettingsRadioButton (Expressive)",
    verticalArrangement = Arrangement.spacedBy(ListItemDefaults.SegmentedGap),
  ) {
    val state = remember { mutableStateOf<String?>(null) }

    SampleData.items.forEach { sampleItem ->
      SettingsRadioButton(
        state = state.value == sampleItem.key,
        colors = colors,
        title = { Text(text = sampleItem.title) },
        subtitle = { Text(text = sampleItem.description) },
        onClick = { state.value = sampleItem.key },
      )
    }
  }
}

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
private fun SettingsTriStateCheckboxSampleSectionExpressive(
  colors: ListItemColors,
) {
  SampleSection(
    title = "SettingsTriStateCheckbox (Expressive)",
    verticalArrangement = Arrangement.spacedBy(ListItemDefaults.SegmentedGap),
  ) {
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

    val triStateWithChildState = if (areAllChildEnabled) {
      ToggleableState.On
    } else if (areNoneChildEnabled) {
      ToggleableState.Off
    } else {
      ToggleableState.Indeterminate
    }

    SettingsTriStateCheckbox(
      state = triStateWithChildState,
      colors = colors,
      title = { Text(text = "TriStateCheckbox") },
      subtitle = { Text(text = "With child checkboxes") },
      onCheckedChange = { newState ->
        child1State.value = newState == ToggleableState.On
        child2State.value = newState == ToggleableState.On
        child3State.value = newState == ToggleableState.On
      },
    )
    Column(
      verticalArrangement = Arrangement.spacedBy(ListItemDefaults.SegmentedGap),
    ) {
      SettingsCheckbox(
        state = child1State.value,
        colors = colors,
        title = { Text(text = "Child #1") },
        onCheckedChange = { child1State.value = it },
      )
      SettingsCheckbox(
        state = child2State.value,
        colors = colors,
        title = { Text(text = "Child #2") },
        onCheckedChange = { child2State.value = it },
      )
      SettingsCheckbox(
        state = child3State.value,
        colors = colors,
        title = { Text(text = "Child #3") },
        onCheckedChange = { child3State.value = it },
      )
    }
  }
}

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
private fun SettingsMenuLinkSectionSampleExpressive(
  colors: ListItemColors,
) {
  SampleSection(
    title = "SettingsMenuLink (Expressive)",
    verticalArrangement = Arrangement.spacedBy(ListItemDefaults.SegmentedGap),
  ) {
    SettingsMenuLink(
      colors = colors,
      shapes = ListItemDefaults.segmentedShapes(
        index = 0,
        count = 3,
      ),
      title = { Text(text = "Menu 1") },
      onClick = { },
    )

    SettingsMenuLink(
      colors = colors,
      shapes = ListItemDefaults.segmentedShapes(
        index = 1,
        count = 3,
      ),
      title = { Text(text = "Menu 2") },
      onClick = { },
    )

    SettingsMenuLink(
      colors = colors,
      shapes = ListItemDefaults.segmentedShapes(
        index = 2,
        count = 3,
      ),
      title = { Text(text = "Menu 3") },
      subtitle = { Text(text = "With subtitle") },
      onClick = { },
    )
  }
}

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
private fun SettingsButtonGroupSample(
  colors: ListItemColors,
) {
  SampleSection(
    title = "SettingsButtonGroup (Expressive)",
    verticalArrangement = Arrangement.spacedBy(ListItemDefaults.SegmentedGap),
  ) {
    val buttonGroupItems = listOf(1, 2, 3)
    val buttonGroupState = remember { mutableStateOf(3) }
    SettingsButtonGroup(
      colors = colors,
      shapes = ListItemDefaults.shapes(),
      title = { Text(text = "Button group") },
      items = buttonGroupItems,
      itemTitleMap = { item -> "#$item" },
      selectedItem = buttonGroupState.value,
      onItemSelected = { buttonGroupState.value = it },
      subtitle = { Text(text = "Selected value: ${buttonGroupState.value}") },
    )
  }
}

