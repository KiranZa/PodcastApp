package com.example.podcastplayer.network

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import com.google.firebase.FirebaseApp

@HiltAndroidApp
class PodcastApp : Application()
{
    override fun onCreate() {
        super.onCreate()
        // Initializing Firebase
        FirebaseApp.initializeApp(this)
    }
}
