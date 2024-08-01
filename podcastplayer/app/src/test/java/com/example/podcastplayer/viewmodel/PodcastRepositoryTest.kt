package com.example.podcastplayer.viewmodel

import com.example.podcastplayer.data.PodcastListResponse
import com.example.podcastplayer.data.PodcastResponse
import com.example.podcastplayer.network.PodcastApi
import com.example.podcastplayer.respository.PodcastRepository
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations

class PodcastRepositoryTest {

    private lateinit var repository: PodcastRepository
    private val api: PodcastApi = mock(PodcastApi::class.java)

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        repository = PodcastRepository(api)
    }

    @Test
    fun `getPodcasts returns list of podcasts`() = runBlocking {
        // Arrange
        val mockPodcasts = listOf(
            PodcastResponse(1, "Podcast 1", "Author 1", "image1.jpg", "description"),
            PodcastResponse(2, "Podcast 2", "Author 2", "image2.jpg","description")
        )
        val mockResponse = PodcastListResponse(mockPodcasts)
        `when`(api.getTopPodcasts()).thenReturn(mockResponse)

        // Act
        val result = repository.getTopPodcasts()

        // Assert
        assertEquals(mockPodcasts, result.results)
    }
}
