package com.example.podcastplayer.ui


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.navigation.compose.rememberNavController

import dagger.hilt.android.AndroidEntryPoint
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.podcastplayer.ui.screens.NavGraph
import com.example.podcastplayer.ui.theme.PodcastplayerTheme
import com.example.podcastplayer.viewmodel.EpisodeViewModel
import com.example.podcastplayer.viewmodel.PodcastViewModel

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PodcastplayerTheme {
                Surface(color = MaterialTheme.colorScheme.background) {
                    val navController = rememberNavController()
                    val podcastViewModel: PodcastViewModel = hiltViewModel()
                    val episodeViewModel: EpisodeViewModel = hiltViewModel()
                    NavGraph(
                        navController = navController,
                        podcastViewModel = podcastViewModel,
                        episodeViewModel = episodeViewModel
                    )
                }
            }
        }
    }
}