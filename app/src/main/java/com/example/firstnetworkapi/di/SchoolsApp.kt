package com.example.firstnetworkapi.di

import android.app.Application

class SchoolsApp: Application() {

    override fun onCreate() {
        super.onCreate()
        schoolsComponent = DaggerSchoolsComponent.builder().build()
    }

    companion object {
        lateinit var schoolsComponent: SchoolsComponent
    }
}