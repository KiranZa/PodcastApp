
plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("kapt")
    id("dagger.hilt.android.plugin")
    id("com.google.gms.google-services")
    id("com.google.firebase.crashlytics")
}

android {
    namespace = "com.example.podcastplayer"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.podcastplayer"
        minSdk = 26
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        //
        // Add Hilt Test Runner
        testInstrumentationRunnerArguments["clearPackageData"] = "true"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlinOptions {
        jvmTarget = "17"
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        //kotlinCompilerExtensionVersion = "1.4.5"
        kotlinCompilerExtensionVersion = "1.4.0"
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation("androidx.core:core-ktx:1.10.1")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.1")
    implementation("androidx.activity:activity-compose:1.7.2")

    implementation("androidx.compose.ui:ui:1.4.5")
    implementation("androidx.compose.material:material:1.4.5")
    implementation("androidx.compose.ui:ui-tooling-preview:1.4.5")
    implementation("androidx.navigation:navigation-compose:2.5.3")
    implementation("androidx.hilt:hilt-navigation-compose:1.0.0")

//    implementation("com.google.dagger:hilt-android:2.44.2")
    implementation("com.google.dagger:hilt-android:2.45")
    implementation(libs.androidx.ui.tooling.preview.android)
    implementation(libs.androidx.material3.android)
//    implementation(libs.firebase.crashlytics.ktx)
//    kapt("com.google.dagger:hilt-android-compiler:2.44.2")
    kapt("com.google.dagger:hilt-android-compiler:2.45")

//Retrofit
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")

    ////
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-moshi:2.9.0")
    implementation("com.squareup.moshi:moshi-kotlin:1.12.0")
//room
    implementation("androidx.room:room-runtime:2.5.0")
    kapt("androidx.room:room-compiler:2.5.0")
    implementation("androidx.room:room-ktx:2.5.0")

    implementation("androidx.compose.runtime:runtime-livedata:1.4.5")

    implementation("androidx.compose.ui:ui-test-junit4:1.4.5")
    debugImplementation("androidx.compose.ui:ui-tooling:1.4.5")
//
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.3.0")
    androidTestImplementation("androidx.compose.ui:ui-test-junit4:1.4.5")

//dagger
    androidTestImplementation("com.google.dagger:hilt-android-testing:2.45")
    kaptAndroidTest("com.google.dagger:hilt-android-compiler:2.45")
//firebase
    implementation("com.google.firebase:firebase-analytics-ktx:21.3.0")
    implementation("com.google.firebase:firebase-firestore-ktx:24.4.0")
    implementation("com.google.firebase:firebase-crashlytics:18.3.2")
//coin
   // implementation("io.coil-kt:coil-compose:2.4.0")
    implementation("io.coil-kt:coil-compose:2.1.0")


// coroutines
    testImplementation("junit:junit:4.13.2")
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.6.4")
    testImplementation("io.mockk:mockk:1.13.3")
    testImplementation("androidx.arch.core:core-testing:2.1.0")

//Media3
//    implementation("androidx.media3:media3-exoplayer:1.0.0-beta01")
//    implementation("androidx.media3:media3-ui:1.0.0-beta01")
    ////
    implementation ("androidx.media3:media3-exoplayer:1.0.0")
    implementation ("androidx.media3:media3-ui:1.0.0")

    implementation ("androidx.compose.material:material-icons-extended:1.4.3") // Ensure this version matches your Compose version

    ///////////

    ///////////////////////
    implementation("androidx.core:core-ktx:1.10.1")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.9.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.1")
    implementation("androidx.activity:activity-compose:1.8.0")
    implementation("androidx.compose.ui:ui:1.5.0")
    implementation("androidx.compose.material:material:1.5.0")
    implementation("androidx.compose.ui:ui-tooling-preview:1.5.0")
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.6.1")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.1")
    implementation("com.google.dagger:hilt-android:2.45")
    kapt("com.google.dagger:hilt-android-compiler:2.45")
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("com.squareup.okhttp3:logging-interceptor:4.9.3")
    implementation("androidx.navigation:navigation-compose:2.6.0")
    implementation("androidx.hilt:hilt-navigation-compose:1.0.0")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
  //  androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
  //  androidTestImplementation("androidx.compose.ui:ui-test-junit4:1.5.0")
    debugImplementation("androidx.compose.ui:ui-tooling:1.5.0")

    //////////

    androidTestImplementation("com.google.dagger:hilt-android-testing:2.45")
    kaptAndroidTest("com.google.dagger:hilt-compiler:2.45")

    // Compose UI testing dependencies
//    androidTestImplementation("androidx.compose.ui:ui-test-junit4:1.6.0")
//    debugImplementation("androidx.compose.ui:ui-tooling:1.6.0")
//    androidTestImplementation("androidx.compose.ui:ui-test-manifest:1.6.0")
//
//    // Mockito and other testing dependencies
    testImplementation("org.mockito:mockito-core:5.0.0")
    testImplementation("org.mockito:mockito-inline:5.0.0")
//    testImplementation("androidx.arch.core:core-testing:2.2.0")
//    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.7.0")

}


kapt {
    correctErrorTypes = true
}

hilt {
    enableAggregatingTask = true
}