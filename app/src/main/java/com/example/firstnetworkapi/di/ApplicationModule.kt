package com.example.firstnetworkapi.di

import android.app.Application
import android.content.Context
import dagger.Provides

class ApplicationModule(private val application: Application) {
    @Provides
    fun providesContext(): Context =
        application.applicationContext
}