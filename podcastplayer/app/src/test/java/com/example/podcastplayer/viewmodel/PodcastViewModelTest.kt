package com.example.podcastplayer.viewmodel


import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.podcastplayer.data.PodcastListResponse
import com.example.podcastplayer.data.PodcastResponse
import com.example.podcastplayer.network.PodcastApi

import com.example.podcastplayer.respository.PodcastRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations

@OptIn(ExperimentalCoroutinesApi::class)
class PodcastViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: PodcastViewModel
    private val repository: PodcastRepository = mock(PodcastRepository::class.java)
    private val api: PodcastApi = mock(PodcastApi::class.java)


    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        viewModel = PodcastViewModel(repository)
    }

    @Test
    fun `loadPodcasts updates podcasts state`() = runTest {
        // Arrange
        val mockPodcasts = listOf(
            PodcastResponse(1, "Podcast 1", "Author 1", "image1.jpg", "Description"),
            PodcastResponse(2, "Podcast 2", "Author 2", "image2.jpg","Description")
        )



        val mockResponse = PodcastListResponse(mockPodcasts)
        `when`(api.getTopPodcasts()).thenReturn(mockResponse)
        // Act
        viewModel.loadTopPodcasts()

        // Assert
        assert(viewModel.podcasts.value == mockPodcasts)
    }
}
