package com.example.podcastplayer.data

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class EpisodeResponse(
    val id: String,
    val title: String,
    val url: String,
    val duration: String,
)

@JsonClass(generateAdapter = true)
data class EpisodeListResponse(
    val results: List<EpisodeResponse>
)