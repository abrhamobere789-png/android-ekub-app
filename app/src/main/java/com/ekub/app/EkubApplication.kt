package com.ekub.app

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class EkubApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        // Initialize app-level configurations
    }
}
