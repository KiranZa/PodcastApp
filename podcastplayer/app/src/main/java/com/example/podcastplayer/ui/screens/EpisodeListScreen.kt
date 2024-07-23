package com.example.podcastplayer.ui.screens


import android.text.format.DateUtils
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.podcastplayer.viewmodel.EpisodeViewModel
import java.net.URLEncoder
import java.nio.charset.StandardCharsets
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.example.podcastplayer.data.EpisodeResponse

@Composable
fun EpisodeListScreen(
    viewModel: EpisodeViewModel,
    navController: NavController,
    podcastId: String,
    imageUrl:String,
    description:String

) {
    viewModel.loadEpisodes(podcastId)
    val episodes by viewModel.episodes.collectAsState()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF00008B)) // Blue background color for the main screen
    )
    {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            textAlign = TextAlign.Center,
            text = "List Of Episodes", fontSize = 25.sp, fontWeight= FontWeight.Bold,color = Color.LightGray
        )

        val imagePainter = rememberAsyncImagePainter("https://the-podcasts.fly.dev/v1/images/${imageUrl}")

        Image(
            painter = imagePainter,
            contentDescription = null,
            modifier = Modifier
                .size(200.dp)
                .fillMaxWidth()
                .align(alignment = Alignment.CenterHorizontally)
                .padding(10.dp)
                .border(1.dp, Color.Gray, RoundedCornerShape(2.dp)),
        )
        val description_ = description.replace('+', ' ')

        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            textAlign = TextAlign.Start,
            text = "Description: ", fontSize = 20.sp,fontWeight = FontWeight.Bold,color = Color.Gray
        )
        Text(
            modifier = Modifier
                .height(150.dp)
                .fillMaxWidth()
                .padding(8.dp)
                .verticalScroll(rememberScrollState()),
            textAlign = TextAlign.Justify,

            text = description_, fontSize = 15.sp
        )

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),

        )

        {
            items(episodes) { episode ->
                EpisodeItem(episode, imageUrl) {
                    val encodedUrl =
                        URLEncoder.encode(episode.url, StandardCharsets.UTF_8.toString())
                    val encodedDuration =
                        URLEncoder.encode(episode.duration, StandardCharsets.UTF_8.toString())
                    navController.navigate("player/${encodedUrl}/${imageUrl}/${encodedDuration}/${episode.title}")
                }
            }
        }
    }
}

@Composable
fun EpisodeItem(
    episode: EpisodeResponse,
    imageUrl: String,
    onClick: () -> Unit
)
{
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .border(2.dp, Color.DarkGray, RoundedCornerShape(8.dp))
            .clickable { onClick() }

            .background(Color.Gray,RoundedCornerShape(8.dp)),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {
        val imagePainter = rememberAsyncImagePainter("https://the-podcasts.fly.dev/v1/images/${imageUrl}")
        Image(
            painter = imagePainter,
            contentDescription = null,
            modifier = Modifier
                .size(64.dp)
                .padding(8.dp)
                .border(1.dp, Color.Gray, RoundedCornerShape(2.dp)),
            )
        Column (
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth(),
        ) {
            Text(text = episode.title, style = MaterialTheme.typography.body1,color = Color.Black,textAlign = TextAlign.Justify, )
            var duration_formatted = DateUtils.formatElapsedTime((episode.duration).toLong())
            Text(text = "Episode duration: "+ duration_formatted , style = MaterialTheme.typography.body2 , fontSize = 12.sp, color = Color.White)

        }
    }
}


