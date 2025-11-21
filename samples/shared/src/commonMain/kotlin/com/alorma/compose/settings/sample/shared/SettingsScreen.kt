package com.alorma.compose.settings.sample.shared

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.alorma.compose.settings.sample.shared.internal.MultiChoiceAlertDialog
import com.alorma.compose.settings.sample.shared.internal.SampleData
import com.alorma.compose.settings.sample.shared.internal.SampleSection
import com.alorma.compose.settings.sample.shared.internal.SingleChoiceAlertDialog
import com.alorma.compose.settings.ui.SettingsCheckbox
import com.alorma.compose.settings.ui.SettingsMenuLink
import com.alorma.compose.settings.ui.SettingsRadioButton
import com.alorma.compose.settings.ui.SettingsSegmented
import com.alorma.compose.settings.ui.SettingsSlider
import com.alorma.compose.settings.ui.SettingsSwitch
import com.alorma.compose.settings.ui.SettingsTriStateCheckbox

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
  modifier: Modifier = Modifier,
) {
  Scaffold(
    modifier = modifier,
    topBar = { TopAppBar(title = { Text(text = "Compose Settings") }) },
  ) { padding ->
    val scrollState = rememberScrollState()
    Column(
      modifier = Modifier
        .consumeWindowInsets(padding)
        .verticalScroll(scrollState)
        .padding(top = padding.calculateTopPadding()),
    ) {
      VersionSection()
      SettingsSwitchSampleSection()
      SettingsCheckboxSampleSection()
      SettingsTriStateCheckboxSampleSection()
      SettingsRadioButtonSampleSection()
      SettingsMenuLinkSectionSample()
      SettingsSliderSectionSample()
      SettingsSelectorsSample()
      SettingsGroupSectionSample()
    }
  }
}

@Composable
private fun SettingsSwitchSampleSection() {
  SampleSection(title = "SettingsSwitch") {
    val state = remember { mutableStateOf(false) }
    SettingsSwitch(
      state = state.value,
      title = { Text(text = "Switch") },
      subtitle = { Text(text = "Switch subtitle") },
      onCheckedChange = { state.value = it },
    )
  }
}

@Composable
private fun SettingsCheckboxSampleSection() {
  SampleSection(title = "SettingsCheckbox") {
    val state = remember { mutableStateOf(false) }
    SettingsCheckbox(
      state = state.value,
      title = { Text(text = "Checkbox") },
      subtitle = { Text(text = "Checkbox subtitle") },
      onCheckedChange = { state.value = it },
    )
  }
}

@Composable
private fun SettingsRadioButtonSampleSection() {
  SampleSection(title = "SettingsRadioButton") {
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

@Composable
private fun SettingsTriStateCheckboxSampleSection() {
  SampleSection(title = "SettingsTriStateCheckbox") {
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

@Composable
private fun SettingsSliderSectionSample() {
  SampleSection(title = "SettingsSlider") {
    DemoSlider()
  }
}

@Composable
private fun DemoSlider() {
  val maxSteps = 6
  val state = remember { mutableStateOf(maxSteps / 2) }

  SettingsSlider(
    title = { Text(text = "Slider") },
    value = state.value.toFloat(),
    onValueChange = { state.value = it.toInt() },
    subtitle = { Text(text = "Selected value: ${state.value}") },
    valueRange = 0f..maxSteps.toFloat(),
    steps = maxSteps - 1,
  )
}

@Composable
private fun SettingsMenuLinkSectionSample() {
  SampleSection(title = "SettingsMenuLink") {
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

@Composable
private fun SettingsSelectorsSample() {
  val items = SampleData.items

  SampleSection(title = "Selectors") {
    val singleSelectionState = remember { mutableStateOf<String?>(null) }

    val showSingleChoiceDialog = remember { mutableStateOf(false) }

    SettingsMenuLink(
      title = { Text(text = "Single choice") },
      subtitle = {
        val item = items.find { it.key == singleSelectionState.value }
        if (item == null) {
          Text(text = "No item selected")
        } else {
          Text(text = "Item selected: ${item.title}")
        }
      },
      onClick = { showSingleChoiceDialog.value = true },
    )

    if (showSingleChoiceDialog.value) {
      SingleChoiceAlertDialog(
        items = items,
        selectedItemKey = singleSelectionState.value,
        onItemSelected = { selectedItemKey ->
          singleSelectionState.value = selectedItemKey
          showSingleChoiceDialog.value = false
        },
      )
    }

    val multipleSelectionState = remember { mutableStateOf<String?>(null) }

    val showMultiChoiceDialog = remember { mutableStateOf(false) }

    SettingsMenuLink(
      title = { Text(text = "Multi choice") },
      subtitle = {
        val selectedItems = items.filter { multipleSelectionState.value?.contains(it.key) ?: false }
        if (selectedItems.isEmpty()) {
          Text(text = "No item selected")
        } else if (selectedItems.size == 1) {
          Text(text = "Item selected: ${selectedItems.first().title}")
        } else {
          Text(text = "Items selected: ${selectedItems.joinToString(", ") { it.title }}")
        }
      },
      onClick = { showMultiChoiceDialog.value = true },
    )

    if (showMultiChoiceDialog.value) {
      MultiChoiceAlertDialog(
        items = items,
        selectedItemKeys = multipleSelectionState.value?.split("|").orEmpty(),
        onItemsSelected = { selectedItemKey ->
          if (selectedItemKey.isNotEmpty()) {
            multipleSelectionState.value = selectedItemKey.joinToString("|")
          }
          showMultiChoiceDialog.value = false
        },
      )
    }
  }
}

@Composable
private fun VersionSection() {
  SampleSection(title = "App Version") {
    SettingsMenuLink(
      title = { Text(text = "Version") },
      subtitle = { Text(text = BuildConfig.VERSION) },
      onClick = { },
    )
  }
}

@Composable
private fun SettingsGroupSectionSample() {
  val groupEnabled = remember { mutableStateOf(true) }
  SampleSection(
    title = "SettingsGroup",
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

    DemoSlider()

    val items = listOf(1, 2, 3)

    val segmentedState = remember { mutableStateOf(3) }
    SettingsSegmented(
      title = { Text(text = "Segmented") },
      items = items,
      itemTitleMap = { item -> "#$item" },
      selectedItem = segmentedState.value,
      onItemSelected = { segmentedState.value = it },
      subtitle = { Text(text = "Selected value: ${segmentedState.value}") },
    )
  }
}
