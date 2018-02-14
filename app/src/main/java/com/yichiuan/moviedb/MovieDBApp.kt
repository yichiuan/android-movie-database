package com.yichiuan.moviedb

import android.app.Application
import com.facebook.stetho.Stetho
import timber.log.Timber

class MovieDBApp : Application() {
    override fun onCreate() {
        super.onCreate()

        // Timber init
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())

            Stetho.initializeWithDefaults(this)
        }
    }

}
