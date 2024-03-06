package internal

import androidx.compose.foundation.clickable
import androidx.compose.material3.ListItem
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role

@Composable
internal fun LabelRadioButton(
  item: SampleItem,
  isSelected: Boolean,
  onClick: () -> Unit,
) {
  ListItem(
    modifier = Modifier.clickable(
      role = Role.RadioButton,
      onClick = onClick,
      onClickLabel = item.title,
    ),
    headlineContent = { Text(text = item.title) },
    supportingContent = { Text(text = item.description) },
    trailingContent = { RadioButton(selected = isSelected, onClick = null) },
  )
}
