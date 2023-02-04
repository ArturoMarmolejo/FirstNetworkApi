package com.example.firstnetworkapi.di

import com.example.firstnetworkapi.rest.SchoolRepository
import com.example.firstnetworkapi.rest.SchoolRepositoryImpl
import dagger.Binds
import dagger.Module

@Module
abstract class RepositoryModule {

    @Binds
    abstract fun providesSchoolRepository(
        SchoolRepositoryImpl: SchoolRepositoryImpl
    ): SchoolRepository
}
