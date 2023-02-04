package com.example.firstnetworkapi.utils

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.firstnetworkapi.di.SchoolsApp
import com.example.firstnetworkapi.viewmodel.SchoolsViewModel
import com.example.starwarsmvvm.utils.SchoolViewModelFactory
import javax.inject.Inject

open class BaseFragment: Fragment() {
    @Inject
    lateinit var schoolViewModelFactory: SchoolViewModelFactory

    protected val schoolsViewModel: SchoolsViewModel by lazy {
        ViewModelProvider(requireActivity(), schoolViewModelFactory)[SchoolsViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        SchoolsApp.schoolsComponent.inject(this)
    }

}