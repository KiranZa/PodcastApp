package com.example.podcastplayer.network


import com.example.podcastplayer.data.EpisodeListResponse
import com.example.podcastplayer.data.PodcastListResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface PodcastApi {
    @GET("v1/toplist") //v1/podcast was not working hence I used /toplist as the list of podcasts
    suspend fun getTopPodcasts(): PodcastListResponse

    @GET("v1/podcasts/{id}/episodes")
    suspend fun getEpisodes(@Path("id") podcastId: String): EpisodeListResponse
}
