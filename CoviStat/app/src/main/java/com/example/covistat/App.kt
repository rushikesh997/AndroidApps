package com.example.covistat

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class App: Application() {
    @Inject
    lateinit var networkHelper: NetworkHelper
}