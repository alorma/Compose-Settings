package internal

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList

@Composable
@Suppress("SpreadOperator")
internal fun MultiChoiceAlertDialog(
  items: ImmutableList<SampleItem>,
  selectedItemKeys: ImmutableList<String> = emptyList<String>().toImmutableList(),
  onItemsSelected: (ImmutableList<String>) -> Unit,
) {
  val userSelectedItems = remember { mutableStateListOf(*selectedItemKeys.toTypedArray()) }

  AlertDialog(
    onDismissRequest = { onItemsSelected(selectedItemKeys.toSet().toImmutableList()) },
    title = { Text(text = "Multi choice") },
    text = {
      Column {
        items.forEach { sampleItem ->
          val isSelected = sampleItem.key in userSelectedItems
          LabelCheckbox(
            item = sampleItem,
            isSelected = isSelected,
            onSelected = {
              if (userSelectedItems.contains(sampleItem.key)) {
                userSelectedItems.remove(sampleItem.key)
              } else {
                userSelectedItems.add(sampleItem.key)
              }
            },
          )
        }
      }
    },
    confirmButton = if (userSelectedItems.isEmpty()) {
      {
        TextButton(
          onClick = { onItemsSelected(emptyList<String>().toImmutableList()) },
        ) {
          Text(text = "Cancel")
        }
      }
    } else {
      {
        TextButton(
          onClick = { onItemsSelected(userSelectedItems.toSet().toImmutableList()) },
        ) {
          Text(text = "Select")
        }
      }
    },
    dismissButton = if (userSelectedItems.isEmpty()) {
      null
    } else {
      {
        TextButton(
          onClick = { onItemsSelected(emptyList<String>().toImmutableList()) },
        ) {
          Text(text = "Clear")
        }
      }
    },
  )
}
