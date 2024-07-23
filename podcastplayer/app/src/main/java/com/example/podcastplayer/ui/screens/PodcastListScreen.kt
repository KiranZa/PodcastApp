package com.example.podcastplayer.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.podcastplayer.data.PodcastResponse
import androidx.compose.ui.text.style.TextAlign
import com.example.podcastplayer.viewmodel.PodcastViewModel
import java.net.URLEncoder
import java.nio.charset.StandardCharsets


@SuppressLint("SuspiciousIndentation")
@Composable
fun PodcastListScreen(
    navController: NavController,
    viewModel: PodcastViewModel = hiltViewModel()
) {
 val podcasts by viewModel.podcasts.collectAsState()
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF00008B)) // Blue background color
    )
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(podcasts) { podcast ->
            PodcastGridItem(podcast) {
                val encodedDescription =
                    URLEncoder.encode(podcast.description, StandardCharsets.UTF_8.toString())
                    navController.navigate("episodes/${podcast.id}/${podcast.image}/${encodedDescription}")
            }
        }
    }
}

@Composable
fun PodcastGridItem(podcast: PodcastResponse, onClick: () -> Unit) {

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.Black)
            .border(1.dp, Color.Gray, RoundedCornerShape(8.dp))
            .clickable(onClick = onClick)

    ) {
        Column(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
        ) {
            val imagePainter = rememberAsyncImagePainter("https://the-podcasts.fly.dev/v1/images/${podcast.image}")

            Image(
            painter = imagePainter,
            contentDescription = null,
            modifier = Modifier
                .height(150.dp)
                .fillMaxWidth(),

            contentScale = ContentScale.Crop
        )
            Text(
                text = podcast.title,
                fontSize = 17.sp,
                style = MaterialTheme.typography.h4,
                textAlign = TextAlign.Start,
            )
            Text(
                text = "Author: "+podcast.author,
                fontSize = 12.sp,
                style = MaterialTheme.typography.h4,
            )
        }
    }
}