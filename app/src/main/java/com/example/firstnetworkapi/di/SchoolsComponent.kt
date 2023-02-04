package com.example.firstnetworkapi.di

import androidx.fragment.app.Fragment
import com.example.firstnetworkapi.MainActivity
import dagger.Component


@Component(modules = [
    NetworkModule::class,
    RepositoryModule::class,
    //ApplicationModule::class,
])
interface SchoolsComponent {
    fun inject(mainActivity: MainActivity)
    fun inject(SchoolsFragment: Fragment)
}