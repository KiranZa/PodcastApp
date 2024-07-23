package com.example.podcastplayer.data

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PodcastResponse(
    val id: Int,
    val title: String,
    val author: String,
    val image: String,
    val description: String
)

@JsonClass(generateAdapter = true)
data class PodcastListResponse(
    val results: List<PodcastResponse>
)