package com.alorma.compose.settings.sample.shared

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.ListItemColors
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.Text
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

@Composable
fun StandardMaterial3Samples() {
  Column(
    modifier = Modifier
      .verticalScroll(rememberScrollState()),
    verticalArrangement = Arrangement.spacedBy(8.dp),
  ) {
    val colors = ListItemDefaults.colors()

    SettingsSwitchSampleSection(
      colors = colors,
    )
    SettingsCheckboxSampleSection(
      colors = colors,
    )
    SettingsTriStateCheckboxSampleSection(
      colors = colors,
    )
    SettingsRadioButtonSampleSection(
      colors = colors,
    )
    SettingsMenuLinkSectionSample(
      colors = colors,
    )
    SettingsSliderSectionSample(
      colors = colors,
    )
    SettingsSelectorsSample(
      colors = colors,
    )
    SettingsShapeSampleSection(
      colors = colors,
    )
    SettingsGroupSectionSample(
      colors = colors,
    )
    SettingsGroupSpacingSample(
      colors = colors,
    )
  }
}

@Composable
private fun SettingsSwitchSampleSection(
  colors: ListItemColors,
) {
  SampleSection(title = "SettingsSwitch") {
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

@Composable
private fun SettingsCheckboxSampleSection(
  colors: ListItemColors,
) {
  SampleSection(title = "SettingsCheckbox") {
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

@Composable
private fun SettingsRadioButtonSampleSection(
  colors: ListItemColors,
) {
  SampleSection(title = "SettingsRadioButton") {
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

@Composable
private fun SettingsTriStateCheckboxSampleSection(
  colors: ListItemColors,
) {
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
      colors = colors,
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
        colors = colors,
        title = { Text(text = "Child #1") },
        onCheckedChange = { child1State.value = it },
      )
      SettingsCheckbox(
        modifier = Modifier.padding(start = 16.dp, end = 32.dp),
        state = child2State.value,
        colors = colors,
        title = { Text(text = "Child #2") },
        onCheckedChange = { child2State.value = it },
      )
      SettingsCheckbox(
        modifier = Modifier.padding(start = 16.dp, end = 32.dp),
        state = child3State.value,
        colors = colors,
        title = { Text(text = "Child #3") },
        onCheckedChange = { child3State.value = it },
      )
    }
  }
}

@Composable
private fun SettingsSliderSectionSample(
  colors: ListItemColors,
) {
  SampleSection(title = "SettingsSlider") {
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
}

@Composable
private fun SettingsMenuLinkSectionSample(
  colors: ListItemColors,
) {
  SampleSection(title = "SettingsMenuLink") {
    val actionState = remember { mutableStateOf(false) }

    SettingsSwitch(
      state = actionState.value,
      colors = colors,
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
private fun SettingsSelectorsSample(
  colors: ListItemColors,
) {
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

    val segmentedItems = listOf(1, 2, 3)
    val segmentedState = remember { mutableStateOf(3) }
    SettingsSegmented(
      title = { Text(text = "Segmented") },
      items = segmentedItems,
      itemTitleMap = { item -> "#$item" },
      selectedItem = segmentedState.value,
      onItemSelected = { segmentedState.value = it },
      subtitle = { Text(text = "Selected value: ${segmentedState.value}") },
    )
  }
}

@Composable
private fun SettingsShapeSampleSection(
  colors: ListItemColors,
) {
  SampleSection(title = "Custom Shapes") {
    val roundedState = remember { mutableStateOf(false) }
    SettingsSwitch(
      state = roundedState.value,
      colors = colors,
      title = { Text(text = "Rounded corners") },
      subtitle = { Text(text = "Using RoundedCornerShape(16.dp)") },
      shape = RoundedCornerShape(16.dp),
      onCheckedChange = { roundedState.value = it },
    )

    val cutCornerState = remember { mutableStateOf(false) }
    SettingsCheckbox(
      state = cutCornerState.value,
      colors = colors,
      title = { Text(text = "Cut corners") },
      subtitle = { Text(text = "Using CutCornerShape(8.dp)") },
      shape = CutCornerShape(8.dp),
      onCheckedChange = { cutCornerState.value = it },
    )

    val circleState = remember { mutableStateOf<String?>(null) }
    SettingsRadioButton(
      state = circleState.value == "circle",
      colors = colors,
      title = { Text(text = "Circle shape") },
      subtitle = { Text(text = "Using CircleShape") },
      shape = CircleShape,
      onClick = { circleState.value = "circle" },
    )

    SettingsMenuLink(
      colors = colors,
      title = { Text(text = "Menu with rounded shape") },
      subtitle = { Text(text = "Using RoundedCornerShape(24.dp)") },
      shape = RoundedCornerShape(24.dp),
      onClick = { },
    )

    val sliderValue = remember { mutableStateOf(3f) }
    SettingsSlider(
      title = { Text(text = "Slider with shape") },
      colors = colors,
      value = sliderValue.value,
      onValueChange = { sliderValue.value = it },
      subtitle = { Text(text = "Using RoundedCornerShape(12.dp)") },
      shape = RoundedCornerShape(12.dp),
      valueRange = 0f..6f,
      steps = 5,
    )
  }
}

@Composable
private fun SettingsGroupSectionSample(
  colors: ListItemColors,
) {
  val groupEnabled = remember { mutableStateOf(true) }
  SampleSection(
    title = "SettingsGroup",
    enabled = groupEnabled.value,
  ) {
    SettingsSwitch(
      state = groupEnabled.value,
      colors = colors,
      title = { Text(text = "Group Enabled") },
      subtitle = { Text(text = "This Switch is always enabled") },
      enabled = true,
      onCheckedChange = { groupEnabled.value = it },
    )

    HorizontalDivider()

    SettingsMenuLink(
      colors = colors,
      title = { Text(text = "Menu") },
      onClick = { },
    )

    val switchState = remember { mutableStateOf(false) }
    SettingsSwitch(
      state = switchState.value,
      colors = colors,
      title = { Text(text = "Switch") },
      subtitle = { Text(text = "Switch subtitle") },
      onCheckedChange = { switchState.value = it },
    )

    val checkboxState = remember { mutableStateOf(false) }
    SettingsCheckbox(
      state = checkboxState.value,
      colors = colors,
      title = { Text(text = "Checkbox") },
      subtitle = { Text(text = "Checkbox subtitle") },
      onCheckedChange = { checkboxState.value = it },
    )

    val triSateCheckboxState = remember { mutableStateOf<Boolean?>(null) }
    SettingsTriStateCheckbox(
      state = triSateCheckboxState.value,
      colors = colors,
      title = { Text(text = "TriStateCheckbox") },
      subtitle = { Text(text = "With child checkboxes") },
      onCheckedChange = { newState -> triSateCheckboxState.value = newState },
    )

    val state = remember { mutableStateOf(false) }
    SettingsRadioButton(
      state = state.value,
      colors = colors,
      title = { Text(text = "RadioButton") },
      subtitle = { Text(text = "RadioButton subtitle") },
      onClick = { state.value = !state.value },
    )
  }
}

@Composable
private fun SettingsGroupSpacingSample(
  colors: ListItemColors,
) {
  SampleSection(
    title = "SettingsGroup - Custom Spacing",
    verticalArrangement = Arrangement.spacedBy(4.dp),
  ) {
    val switch1State = remember { mutableStateOf(false) }
    SettingsSwitch(
      state = switch1State.value,
      colors = colors,
      title = { Text(text = "Compact spacing") },
      subtitle = { Text(text = "Using 4.dp spacing between items") },
      onCheckedChange = { switch1State.value = it },
    )

    val switch2State = remember { mutableStateOf(true) }
    SettingsSwitch(
      state = switch2State.value,
      colors = colors,
      title = { Text(text = "Another switch") },
      subtitle = { Text(text = "Notice the reduced spacing") },
      onCheckedChange = { switch2State.value = it },
    )

    val checkboxState = remember { mutableStateOf(false) }
    SettingsCheckbox(
      state = checkboxState.value,
      colors = colors,
      title = { Text(text = "Checkbox item") },
      subtitle = { Text(text = "All items have consistent 4.dp spacing") },
      onCheckedChange = { checkboxState.value = it },
    )
  }

  SampleSection(
    title = "SettingsGroup - No Spacing",
    verticalArrangement = Arrangement.Top,
  ) {
    val switch1State = remember { mutableStateOf(false) }
    SettingsSwitch(
      state = switch1State.value,
      colors = colors,
      title = { Text(text = "No spacing") },
      subtitle = { Text(text = "Using Arrangement.Top for no spacing") },
      onCheckedChange = { switch1State.value = it },
    )

    val switch2State = remember { mutableStateOf(true) }
    SettingsSwitch(
      state = switch2State.value,
      colors = colors,
      title = { Text(text = "Tightly packed") },
      subtitle = { Text(text = "Items are directly adjacent") },
      onCheckedChange = { switch2State.value = it },
    )
  }
}
