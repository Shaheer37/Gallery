package com.gallery.ui

import android.app.Application
import androidx.multidex.MultiDexApplication
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class App : MultiDexApplication(){
    override fun onCreate() {
        super.onCreate()

        Timber.plant(Timber.DebugTree())
    }
}