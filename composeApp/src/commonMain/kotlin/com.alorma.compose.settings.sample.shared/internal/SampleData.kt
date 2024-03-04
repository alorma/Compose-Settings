package com.alorma.compose.settings.sample.shared.internal

internal object SampleData {
  val items = listOf(
    SampleItem(
      key = "hendrerit",
      title = "petentium",
      description = "verear",
    ),
    SampleItem(
      key = "per",
      title = "vel",
      description = "utroque",
    ),
    SampleItem(
      key = "appetere",
      title = "honestatis",
      description = "ornare",
    )
  )
}

internal data class SampleItem(
  val key: String,
  val title: String,
  val description: String,
)