package com.alorma.compose.settings.sample.shared.internal

internal object SampleData {
  val items =
    listOf(
      SampleItem(
        key = "item-0",
        title = "Item #0",
        description = "Subtitle of item #0",
      ),
      SampleItem(
        key = "item-1",
        title = "Item #1",
        description = "Subtitle of item #1",
      ),
      SampleItem(
        key = "item-2",
        title = "Item #2",
        description = "Subtitle of item #2",
      ),
    )
}

internal data class SampleItem(
  val key: String,
  val title: String,
  val description: String,
)
