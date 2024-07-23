package com.example.podcastplayer.ui.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.podcastplayer.viewmodel.EpisodeViewModel
import com.example.podcastplayer.viewmodel.PodcastViewModel

@Composable
fun NavGraph(
    navController: NavHostController,
    podcastViewModel: PodcastViewModel,
    episodeViewModel: EpisodeViewModel
) {
    NavHost(
        navController = navController,
        startDestination = "podcasts",
        modifier = Modifier.fillMaxSize()
    ) {
        composable("podcasts") {
            PodcastListScreen(
                viewModel = podcastViewModel,
                navController = navController
            )
        }
        composable("episodes/{podcastId}/{imageUrl}/{description}") { backStackEntry ->
            val podcastId = backStackEntry.arguments?.getString("podcastId")
            val imageUrl = backStackEntry.arguments?.getString("imageUrl")
            val description = backStackEntry.arguments?.getString("description")
            if (podcastId != null && imageUrl!=null && description != null) {
                EpisodeListScreen(
                    viewModel = episodeViewModel,
                    navController = navController,
                    podcastId = podcastId,
                    imageUrl = imageUrl,
                    description = description
                )
            }
        }

        composable("player/{audioUrl}/{imageUrl}/{episode_duration}/{episode_title}") { backStackEntry ->
            val audioUrl = backStackEntry.arguments?.getString("audioUrl")
            val imageUrl = backStackEntry.arguments?.getString("imageUrl")
            val episode_duration = backStackEntry.arguments?.getString("episode_duration")
            val episode_title = backStackEntry.arguments?.getString("episode_title")

            if (audioUrl != null && imageUrl!=null && episode_duration!=null && episode_title!=null ) {
                PlayerScreen(
                    navController = navController,
                    episodeUrl = audioUrl,
                    imageUrl = imageUrl,
                    episode_duration = episode_duration,
                    episode_title = episode_title
                )
            }
        }
    }
}
