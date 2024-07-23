package com.example.podcastplayer.viewmodel

//import androidx.arch.core.executor.testing.InstantTaskExecutorRule
//import com.example.podcastplayer.Episode
//import com.example.podcastplayer.Podcast
//import com.example.podcastplayer.respository.PodcastRepository
//
//import io.mockk.coEvery
//import io.mockk.coVerify
//import io.mockk.mockk
//import kotlinx.coroutines.Dispatchers
//import kotlinx.coroutines.ExperimentalCoroutinesApi
//import kotlinx.coroutines.flow.first
//import kotlinx.coroutines.runBlocking
//import kotlinx.coroutines.test.*
//import org.junit.Assert.assertEquals
//
//import org.junit.Before
//import org.junit.Rule
//import org.junit.Test
//
//@ExperimentalCoroutinesApi
//class PodcastViewModelTest {
//
//    @get:Rule
//    val instantTaskExecutorRule = InstantTaskExecutorRule()
//
//    private val repository: PodcastRepository = mockk()
//    private lateinit var viewModel: PodcastViewModel
//
//    private val testDispatcher = UnconfinedTestDispatcher()
//
//    @Before
//    fun setUp() {
//        Dispatchers.setMain(testDispatcher)
//        viewModel = PodcastViewModel(repository)
//    }
//
//    @Test
//    fun `fetchPodcasts should update podcasts state when successful`() = runBlocking {
//        // Given
//        val mockPodcasts = listOf(
//            Podcast(id = "1", title = "Podcast 1", coverArt = "cover1.jpg"),
//            Podcast(id = "2", title = "Podcast 2", coverArt = "cover2.jpg")
//        )
//        coEvery { repository.getPodcasts() } returns mockPodcasts
//
//        // When
//        viewModel.fetchPodcasts()
//
//        // Then
//        val result = viewModel.podcasts.first()
//        assertEquals(Result.success(mockPodcasts), result)
//        coVerify { repository.getPodcasts() }
//    }
//
//    @Test
//    fun `fetchPodcasts should update podcasts state when failed`() = runBlocking {
//        // Given
//        val mockException = Exception("Network error")
//        coEvery { repository.getPodcasts() } throws mockException
//
//        // When
//        viewModel.fetchPodcasts()
//
//        // Then
//        val result = viewModel.podcasts.first()
//       // assert(result.isFailure)
//      //  assertEquals(mockException, result.exceptionOrNull())
//        coVerify { repository.getPodcasts() }
//    }
//
//    @Test
//    fun `fetchEpisodes should update episodes state when successful`() = runBlocking {
//        // Given
//        val mockPodcastId = "1"
//        val mockEpisodes = listOf(
//            Episode(id = "1", title = "Episode 1", description = "Description 1", audioUrl = "audio1.mp3"),
//            Episode(id = "2", title = "Episode 2", description = "Description 2", audioUrl = "audio2.mp3")
//        )
//        coEvery { repository.getEpisodes(mockPodcastId) } returns mockEpisodes
//
//        // When
//        viewModel.fetchEpisodes(mockPodcastId)
//
//        // Then
//        val result = viewModel.episodes.first()
//        assertEquals(Result.success(mockEpisodes), result)
//        coVerify { repository.getEpisodes(mockPodcastId) }
//    }
//
//    @Test
//    fun `fetchEpisodes should update episodes state when failed`() = runBlocking {
//        // Given
//        val mockPodcastId = "1"
//        val mockException = Exception("Network error")
//        coEvery { repository.getEpisodes(mockPodcastId) } throws mockException
//
//        // When
//        viewModel.fetchEpisodes(mockPodcastId)
//
//        // Then
//        val result = viewModel.episodes.first()
//    //    assert(result.isFailure)
//    //    assertEquals(mockException, result.exceptionOrNull())
//        coVerify { repository.getEpisodes(mockPodcastId) }
//    }
//}

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