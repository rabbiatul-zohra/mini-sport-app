package com.example.mini_sport_app

data class Stories (
    val data: StoriesData
)

data class StoriesData (
    val topic: Topic,
    val items: List<Items>
)

data class Topic (
    val title: String?,
    val url: String?
)

data class Items (
    val type: String?,
    val title: String?,
    val url: String?,
    val isLive: Boolean?,
    val sectionLabel: String?,
    val lastUpdatedText: String?,
    val image: Image?

)

data class Image (
    val large: String?
)