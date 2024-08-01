package com.example.podcastplayer.viewmodel


import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.podcastplayer.data.PodcastListResponse
import com.example.podcastplayer.data.PodcastResponse
import com.example.podcastplayer.network.PodcastApi
import com.example.podcastplayer.respository.PodcastRepository
import com.google.firebase.crashlytics.FirebaseCrashlytics
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.*
import org.junit.After
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
    private val crashlytics: FirebaseCrashlytics = mock(FirebaseCrashlytics::class.java)

    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        MockitoAnnotations.openMocks(this)
        viewModel = PodcastViewModel(repository, crashlytics)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain() // reset the main dispatcher to the original Main dispatcher
    }

    @Test
    fun loadPodcasts_updatesPodcastsState() = runTest {
        // Arrange
        val mockPodcasts = listOf(
            PodcastResponse(1, "Podcast 1", "Author 1", "image1.jpg", "Description"),
            PodcastResponse(2, "Podcast 2", "Author 2", "image2.jpg", "Description")
        )

        val mockResponse = PodcastListResponse(mockPodcasts)
        `when`(repository.getTopPodcasts()).thenReturn(mockResponse)

        // Act
        viewModel.loadTopPodcasts()

        // Advance time to ensure the coroutine completes
        advanceUntilIdle()

        // Assert
        assert(viewModel.podcasts.value == mockPodcasts)
    }
}
