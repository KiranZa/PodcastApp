package com.example.podcastplayer.respository


import com.example.podcastplayer.data.EpisodeListResponse
import com.example.podcastplayer.data.PodcastListResponse
import com.example.podcastplayer.network.PodcastApi
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PodcastRepository @Inject constructor(
    private val podcastApi: PodcastApi
) {
    suspend fun getTopPodcasts(): PodcastListResponse {
        return podcastApi.getTopPodcasts()
    }
    suspend fun getEpisodes(podcastId: String): EpisodeListResponse {
        return podcastApi.getEpisodes(podcastId)
    }
}