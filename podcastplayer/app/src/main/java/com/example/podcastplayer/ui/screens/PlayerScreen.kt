package com.example.podcastplayer.ui.screens

import android.text.format.DateUtils
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.compose.material.*
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.media3.common.Player
import coil.compose.rememberAsyncImagePainter
import com.example.podcastplayer.viewmodel.PlayerViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@Composable
fun PlayerScreen(navController: NavController, episodeUrl: String ,imageUrl:String, episode_duration:String, episode_title:String ) {
    val viewModel: PlayerViewModel = hiltViewModel()
    val player = viewModel.player

    LaunchedEffect(episodeUrl) {
        viewModel.play(episodeUrl)
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Now Playing", fontSize = 20.sp, textAlign = TextAlign.Center)

        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(25.dp),
            textAlign = TextAlign.Center,
            text = episode_title, fontSize = 25.sp
        )

        val imagePainter = rememberAsyncImagePainter("https://the-podcasts.fly.dev/v1/images/${imageUrl}")
        Image(
            painter = imagePainter,
            contentDescription = null,
            modifier = Modifier
                .size(350.dp)
                .padding(20.dp)
            )
        var duration_formatted = DateUtils.formatElapsedTime((episode_duration).toLong())
        var current_duration_formatted = DateUtils.formatElapsedTime((player.currentPosition/1000).toLong())

    var isPlaying by remember { mutableStateOf(player.isPlaying) }
    var currentPosition by remember { mutableStateOf(0L) }
    var duration_new by remember { mutableStateOf(0L) }

    val scope = rememberCoroutineScope()

    DisposableEffect(player) {
        val listener = object : Player.Listener {
            override fun onIsPlayingChanged(isPlaying: Boolean) {
                    player.isPlaying
            }

            override fun onPlaybackStateChanged(state: Int) {
                if (state == Player.STATE_READY) {
                    scope.launch {
                        duration_new = player.duration
                    }
                }
            }
        }

        player.addListener(listener)

        onDispose {
            player.removeListener(listener)
            viewModel.player.release()
        }
    }

    LaunchedEffect(player) {
        while (true) {
            currentPosition = player.currentPosition
            delay(500)
        }
    }
        Slider(
            value = currentPosition.toFloat(),
            onValueChange = { viewModel.seekTo(currentPosition) },
            valueRange = 0f..duration_new.toFloat()
        )

        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            textAlign = TextAlign.Center,
            text = "Episode Duration: "+current_duration_formatted+"/"+duration_formatted, fontSize = 15.sp
        )

        Spacer(modifier = Modifier.height(8.dp))

        Row {

            Button(onClick = { viewModel.rewind() }) {
                Text("Rewind 10s")
            }
            Spacer(modifier = Modifier.width(8.dp))

            var i by remember { mutableStateOf(0) }
            Button(
                onClick = {
                    if (player.isPlaying) {
                        viewModel.pause()
                        i = 1
                    } else {
                        viewModel.play()
                        i = 0
                    }
                },

            ) {
                if (i ==0){
                    Text(text = "Pause")
                }else{
                    Text(text = "Play")
                }
            }
            Spacer(modifier = Modifier.width(8.dp))
            Button(onClick = { viewModel.forward() }) {
                Text("Forward 10s")
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
    }
}


