package com.example.starwarsmvvm.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.firstnetworkapi.rest.SchoolRepository
import com.example.firstnetworkapi.viewmodel.SchoolsViewModel
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class SchoolViewModelFactory @Inject constructor(
    private val SchoolRepository: SchoolRepository,
    private val ioDispatcher: CoroutineDispatcher
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return SchoolsViewModel(SchoolRepository, ioDispatcher) as T
    }
}