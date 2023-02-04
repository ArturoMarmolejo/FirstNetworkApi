package com.example.firstnetworkapi.di

import android.app.Application
import android.content.Context
import com.example.firstnetworkapi.rest.SchoolRepository
import com.example.starwarsmvvm.utils.SchoolViewModelFactory
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineDispatcher

@Module
class ApplicationModule(private val application: Application) {
    @Provides
    fun providesContext(): Context =
        application.applicationContext

    @Provides
    fun providesViewModelFactory(
        repository: SchoolRepository,
        ioDispatcher: CoroutineDispatcher
    ): SchoolViewModelFactory = SchoolViewModelFactory(repository, ioDispatcher)
}