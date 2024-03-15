import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.alorma.compose.settings.ui.SettingsCheckbox
import com.alorma.compose.settings.ui.SettingsMenuLink
import com.alorma.compose.settings.ui.SettingsRadioButton
import com.alorma.compose.settings.ui.SettingsSlider
import com.alorma.compose.settings.ui.SettingsSwitch
import com.alorma.compose.settings.ui.SettingsTriStateCheckbox
import internal.MultiChoiceAlertDialog
import internal.SampleData
import internal.SampleSection
import internal.SingleChoiceAlertDialog
import internal.iconSampleOrNull
import kotlinx.collections.immutable.toImmutableList

@Composable
fun SettingsScreen(modifier: Modifier = Modifier) {
  Scaffold(
    modifier = modifier,
  ) { padding ->
    val scrollState = rememberScrollState()
    Column(
      modifier = Modifier
        .consumeWindowInsets(padding)
        .verticalScroll(scrollState)
        .padding(top = padding.calculateTopPadding()),
    ) {
      val iconState = remember { mutableStateOf(false) }
      SettingsSwitch(
        state = iconState.value,
        title = { Text(text = "Show icon") },
        onCheckedChange = { iconState.value = it },
      )
      SettingsSwitchSampleSection(iconState.value)
      SettingsCheckboxSampleSection(iconState.value)
      SettingsTriStateCheckboxSampleSection(iconState.value)
      SettingsRadioButtonSampleSection(iconState.value)
      SettingsMenuLinkSectionSample(iconState.value)
      SettingsSliderSectionSample(iconState.value)
      SettingsSelectorsSample(iconState.value)
    }
  }
}

@Composable
private fun SettingsSwitchSampleSection(showIcon: Boolean) {
  SampleSection(title = "SettingsSwitch") {
    val state = remember { mutableStateOf(false) }
    SettingsSwitch(
      state = state.value,
      title = { Text(text = "Switch") },
      subtitle = { Text(text = "Switch subtitle") },
      icon = iconSampleOrNull(showIcon),
      onCheckedChange = { state.value = it },
    )
  }
}

@Composable
private fun SettingsCheckboxSampleSection(showIcon: Boolean) {
  SampleSection(title = "SettingsCheckbox") {
    val state = remember { mutableStateOf(false) }
    SettingsCheckbox(
      state = state.value,
      title = { Text(text = "Checkbox") },
      subtitle = { Text(text = "Checkbox subtitle") },
      icon = iconSampleOrNull(showIcon),
      onCheckedChange = { state.value = it },
    )
  }
}

@Composable
private fun SettingsRadioButtonSampleSection(showIcon: Boolean) {
  SampleSection(title = "SettingsRadioButton") {
    val state = remember { mutableStateOf<String?>(null) }

    SampleData.items.forEach { sampleItem ->
      SettingsRadioButton(
        state = state.value == sampleItem.key,
        title = { Text(text = sampleItem.title) },
        subtitle = { Text(text = sampleItem.description) },
        icon = iconSampleOrNull(showIcon),
        onClick = { state.value = sampleItem.key },
      )
    }
  }
}

@Composable
private fun SettingsTriStateCheckboxSampleSection(showIcon: Boolean) {
  SampleSection(title = "SettingsTriStateCheckbox") {
    val child1State = remember { mutableStateOf(false) }
    val child2State = remember { mutableStateOf(true) }
    val child3State = remember { mutableStateOf(false) }

    val allChildStates = remember {
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
private fun SettingsSliderSectionSample(showIcon: Boolean) {
  SampleSection(title = "SettingsSlider") {
    val state = remember { mutableStateOf(5) }

    SettingsSlider(
      title = { Text(text = "Slider") },
      value = state.value.toFloat(),
      onValueChange = { state.value = it.toInt() },
      subtitle = { Text(text = "Selected value: ${state.value}") },
      icon = iconSampleOrNull(showIcon),
      valueRange = 0f..20f,
      steps = 20,
    )
  }
}

@Composable
private fun SettingsMenuLinkSectionSample(
  showIcon: Boolean,
) {

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

@Composable
private fun SettingsSelectorsSample(showIcon: Boolean) {
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
      icon = iconSampleOrNull(showIcon),
      action = if (singleSelectionState.value == null) {
        null
      } else {
        {
          IconButton(
            onClick = { singleSelectionState.value = null },
          ) {
            Icon(
              imageVector = Icons.Default.Delete,
              contentDescription = null,
            )
          }
        }
      },
    )

    if (showSingleChoiceDialog.value) {
      SingleChoiceAlertDialog(
        items = items.toImmutableList(),
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
      icon = iconSampleOrNull(showIcon),
      action = if (multipleSelectionState.value == null) {
        null
      } else {
        {
          IconButton(
            onClick = { multipleSelectionState.value = null },
          ) {
            Icon(
              imageVector = Icons.Default.Delete,
              contentDescription = null,
            )
          }
        }
      },
    )

    if (showMultiChoiceDialog.value) {
      MultiChoiceAlertDialog(
        items = items.toImmutableList(),
        selectedItemKeys = multipleSelectionState.value?.split("|").orEmpty().toImmutableList(),
        onItemsSelected = { selectedItemKey ->
          multipleSelectionState.value = selectedItemKey.joinToString("|")
          showMultiChoiceDialog.value = false
        },
      )
    }
  }
}
