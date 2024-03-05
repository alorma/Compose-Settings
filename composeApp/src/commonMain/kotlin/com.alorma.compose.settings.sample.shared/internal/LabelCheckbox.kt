package com.alorma.compose.settings.sample.shared.internal

import androidx.compose.foundation.clickable
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role

@Composable
internal fun LabelCheckbox(
  item: SampleItem,
  isSelected: Boolean,
  onSelected: () -> Unit,
) {
  ListItem(
    modifier = Modifier.clickable(
      role = Role.RadioButton,
      onClick = onSelected,
      onClickLabel = item.title,
    ),
    headlineContent = { Text(text = item.title) },
    supportingContent = { Text(text = item.description) },
    trailingContent = { Checkbox(checked = isSelected, onCheckedChange = null) },
  )
}