package com.example.podcastplayer.viewmodel


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.podcastplayer.data.PodcastResponse
import com.example.podcastplayer.respository.PodcastRepository
import com.google.firebase.crashlytics.FirebaseCrashlytics
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PodcastViewModel @Inject constructor(
    private val podcastRepository: PodcastRepository,
    private val crashlytics: FirebaseCrashlytics
) : ViewModel() {

    private val _podcasts = MutableStateFlow<List<PodcastResponse>>(emptyList())
    val podcasts: StateFlow<List<PodcastResponse>> get() = _podcasts

    init {
        loadTopPodcasts()
    }

    fun loadTopPodcasts() {
        viewModelScope.launch {
            try {
                val response = podcastRepository.getTopPodcasts()
                _podcasts.value = response.results

            } catch (e: Exception) {
                // Handling error
               crashlytics.recordException(e)

            }
        }
    }
}
