package internal

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember

@Composable
internal fun SingleChoiceAlertDialog(
  selectedItemKey: String?,
  onItemSelected: (String?) -> Unit,
  items: List<SampleItem>
) {
  val userSelectedItem = remember { mutableStateOf(selectedItemKey) }

  AlertDialog(
    onDismissRequest = { onItemSelected(selectedItemKey) },
    title = { Text(text = "Single choice") },
    text = {
      Column {
        items.forEach { sampleItem ->
          val isSelected = sampleItem.key == userSelectedItem.value
          LabelRadioButton(
            item = sampleItem,
            isSelected = isSelected,
            onClick = { userSelectedItem.value = sampleItem.key },
          )
        }
      }
    },
    confirmButton = if (userSelectedItem.value == null) {
      {
        TextButton(
          onClick = { onItemSelected(null) },
        ) {
          Text(text = "Cancel")
        }
      }
    } else {
      {
        TextButton(
          onClick = { onItemSelected(userSelectedItem.value) },
        ) {
          Text(text = "Select")
        }
      }
    },
    dismissButton = if (userSelectedItem.value == null) {
      null
    } else {
      {
        TextButton(
          onClick = { onItemSelected(null) },
        ) {
          Text(text = "Clear")
        }
      }
    },
  )
}
