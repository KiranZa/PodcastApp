package com.example.podcastplayer.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.exoplayer.ExoPlayer
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

@HiltViewModel
class PlayerViewModel @Inject constructor(
    @ApplicationContext context: Context,
) : ViewModel() {

    private val exoPlayer: ExoPlayer = ExoPlayer.Builder(context).build()

    val player: Player
        get() = exoPlayer

    fun play(url: String) {
        val mediaItem = MediaItem.fromUri(url)
        exoPlayer.setMediaItem(mediaItem)
        exoPlayer.prepare()
        exoPlayer.playWhenReady = true
    }

    fun pause() {
        exoPlayer.pause()
    }

    fun play() {
        exoPlayer.play()
    }

    fun seekTo(position: Long) {
        exoPlayer.seekTo(position)
    }

    fun rewind() {
        exoPlayer.seekTo(exoPlayer.currentPosition - 10000) // Rewind 10 seconds
    }

    fun forward() {
        exoPlayer.seekTo(exoPlayer.currentPosition + 10000) // Forward 10 seconds
    }

    override fun onCleared() {
        super.onCleared()
        exoPlayer.release()

    }
}