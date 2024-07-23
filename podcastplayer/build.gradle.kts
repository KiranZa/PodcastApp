// Top-level build file where you can add configuration options common to all sub-projects/modules.
//plugins {
//    alias(libs.plugins.android.application) apply false
//    alias(libs.plugins.jetbrains.kotlin.android) apply false
//}

plugins {

     id("com.google.dagger.hilt.android") version "2.45" apply false
     kotlin("jvm") version "1.8.0" apply false
     id("com.google.gms.google-services") version "4.3.15" apply false
     id("org.jetbrains.kotlin.android") version "1.8.0" apply false
     id ("com.android.application") version "8.0.0" apply false
     id("com.android.library") version "8.0.0" apply false
     id("com.google.firebase.crashlytics") version "2.8.1" apply false
}