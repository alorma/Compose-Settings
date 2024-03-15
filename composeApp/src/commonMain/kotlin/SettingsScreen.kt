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
import com.alorma.compose.settings.storage.disk.rememberBooleanSettingState
import com.alorma.compose.settings.storage.disk.rememberIntSettingState
import com.alorma.compose.settings.storage.disk.rememberStringSettingState
import com.alorma.compose.settings.storage.disk.rememberTriStateSetting
import com.alorma.compose.settings.storage.memory.rememberMemoryBooleanSettingState
import com.alorma.compose.settings.storage.memory.rememberMemoryIntSettingState
import com.alorma.compose.settings.storage.memory.rememberMemoryTriStateSettingState
import com.alorma.compose.settings.ui.SettingsCheckbox
import com.alorma.compose.settings.ui.SettingsMenuLink
import com.alorma.compose.settings.ui.SettingsRadioButton
import com.alorma.compose.settings.ui.SettingsSlider
import com.alorma.compose.settings.ui.SettingsSwitch
import com.alorma.compose.settings.ui.SettingsTriStateCheckbox
import com.russhwolf.settings.Settings
import internal.MultiChoiceAlertDialog
import internal.SampleData
import internal.SampleSection
import internal.SingleChoiceAlertDialog
import internal.iconSampleOrNull
import kotlinx.collections.immutable.toImmutableList

@Composable
fun SettingsScreen(
  settings: Settings,
  modifier: Modifier = Modifier,
) {
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
      val iconState = rememberMemoryBooleanSettingState()
      SettingsSwitch(
        state = iconState.value,
        title = { Text(text = "Show icon") },
        onCheckedChange = { iconState.value = it },
      )
      SettingsSwitchSampleSection(settings, iconState.value)
      SettingsCheckboxSampleSection(settings, iconState.value)
      SettingsTriStateCheckboxSampleSection(settings, iconState.value)
      SettingsRadioButtonSampleSection(settings, iconState.value)
      SettingsMenuLinkSectionSample(iconState.value)
      SettingsSliderSectionSample(settings, iconState.value)
      SettingsSelectorsSample(settings, iconState.value)
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
      icon = iconSampleOrNull(showIcon),
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
      icon = iconSampleOrNull(showIcon),
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
private fun SettingsRadioButtonSampleSection(settings: Settings, showIcon: Boolean) {
  SampleSection(title = "SettingsRadioButton Tile") {
    val radioButtonDiskState = rememberStringSettingState(
      key = "radioButtonDiskState",
      settings = settings,
    )
    SampleData.items.forEach { sampleItem ->
      SettingsRadioButton(
        state = radioButtonDiskState.value == sampleItem.key,
        title = { Text(text = sampleItem.title) },
        subtitle = { Text(text = sampleItem.description) },
        icon = iconSampleOrNull(showIcon),
        onClick = { radioButtonDiskState.value = sampleItem.key },
      )
    }
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
private fun SettingsSliderSectionSample(
  settings: Settings,
  showIcon: Boolean,
) {
  SampleSection(title = "SettingsSlider Tile") {
    val sliderMemoryState = rememberMemoryIntSettingState(
      defaultValue = 5,
    )

    SettingsSlider(
      title = { Text(text = "Slider") },
      value = sliderMemoryState.value.toFloat(),
      onValueChange = { sliderMemoryState.value = it.toInt() },
      subtitle = { Text(text = "Selected value: ${sliderMemoryState.value}") },
      icon = iconSampleOrNull(showIcon),
      valueRange = 0f..20f,
      steps = 20,
    )

    val sliderDiskState = rememberIntSettingState(
      key = "sliderDiskState",
      defaultValue = 5,
      settings = settings,
    )

    SettingsSlider(
      title = { Text(text = "Slider") },
      value = sliderDiskState.value.toFloat(),
      onValueChange = { sliderDiskState.value = it.toInt() },
      subtitle = { Text(text = "Selected value: ${sliderDiskState.value}") },
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
  SampleSection(title = "SettingsMenuLink Tile") {
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

@Composable
private fun SettingsSelectorsSample(
  settings: Settings,
  showIcon: Boolean,
) {
  val items = SampleData.items

  SampleSection(title = "Selectors") {
    val selectSingleChoiceDisk = rememberStringSettingState(
      key = "singleChoice",
      settings = settings,
    )

    val showSingleChoiceDialog = remember { mutableStateOf(false) }

    SettingsMenuLink(
      title = { Text(text = "Single choice (Disk)") },
      subtitle = {
        val item = items.find { it.key == selectSingleChoiceDisk.value }
        if (item == null) {
          Text(text = "No item selected")
        } else {
          Text(text = "Item selected: ${item.title}")
        }
      },
      onClick = { showSingleChoiceDialog.value = true },
      icon = iconSampleOrNull(showIcon),
      action = if (selectSingleChoiceDisk.value == null) {
        null
      } else {
        {
          IconButton(
            onClick = { selectSingleChoiceDisk.value = null },
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
        selectedItemKey = selectSingleChoiceDisk.value,
        onItemSelected = { selectedItemKey ->
          selectSingleChoiceDisk.value = selectedItemKey
          showSingleChoiceDialog.value = false
        },
      )
    }

    val selectMultipleChoiceDisk = rememberStringSettingState(
      key = "multiChoice",
      settings = settings,
    )

    val showMultiChoiceDialog = remember { mutableStateOf(false) }

    SettingsMenuLink(
      title = { Text(text = "Multi choice (Disk)") },
      subtitle = {
        val selectedItems = items.filter { selectMultipleChoiceDisk.value?.contains(it.key) ?: false }
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
      action = if (selectMultipleChoiceDisk.value == null) {
        null
      } else {
        {
          IconButton(
            onClick = { selectMultipleChoiceDisk.value = null },
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
        selectedItemKeys = selectMultipleChoiceDisk.value?.split("|").orEmpty().toImmutableList(),
        onItemsSelected = { selectedItemKey ->
          selectMultipleChoiceDisk.value = selectedItemKey.joinToString("|")
          showMultiChoiceDialog.value = false
        },
      )
    }
  }
}
