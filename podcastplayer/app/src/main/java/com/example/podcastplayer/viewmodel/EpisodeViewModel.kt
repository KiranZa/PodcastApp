package com.example.podcastplayer.viewmodel


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.podcastplayer.data.EpisodeResponse
import com.example.podcastplayer.respository.PodcastRepository
import com.google.firebase.crashlytics.FirebaseCrashlytics
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject



@HiltViewModel
class EpisodeViewModel @Inject constructor(
    private val podcastRepository: PodcastRepository
) : ViewModel() {

    private val _episodes = MutableStateFlow<List<EpisodeResponse>>(emptyList())
    val episodes: StateFlow<List<EpisodeResponse>> get() = _episodes

    fun loadEpisodes(podcastId: String) {
        viewModelScope.launch {
            try {
                val response = podcastRepository.getEpisodes(podcastId)
                _episodes.value = response.results
            } catch (e: Exception) {
                // Handling error
                FirebaseCrashlytics.getInstance().recordException(e)
            }
        }
    }


}
