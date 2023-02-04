package com.example.firstnetworkapi.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.firstnetworkapi.model.SchoolsItem
import com.example.firstnetworkapi.rest.SchoolRepository
import com.example.firstnetworkapi.views.UIState
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collect

private const val TAG = "SchoolsViewModel"

class SchoolsViewModel(
    private val SchoolRepository: SchoolRepository,
    private val ioDispatcher :CoroutineDispatcher = Dispatchers.IO
) : ViewModel() {
    init {
        getSchools()
    }

    var fragmentState: Boolean = false
    private val _schools: MutableLiveData<UIState> = MutableLiveData(UIState.LOADING)
    val schools: LiveData<UIState> get() = _schools

    private val _satScores: MutableLiveData<UIState> = MutableLiveData(UIState.LOADING)
    val satScores: LiveData<UIState> get() = _satScores

    fun getSchools(dbn: String? = null) {
        dbn?.let { item ->
            Log.d(TAG, "getSchools: ENTERED IN getSchools(${item})")
         viewModelScope.launch(ioDispatcher) {
             SchoolRepository.getSatScore(item).collect{
                 _schools.postValue(it)
             }
         }
        } ?: run {
            viewModelScope.launch(ioDispatcher) {
                SchoolRepository.getAllSchools().collect {
                    Log.d(TAG, "getSchools: UIState collected ${it}")
                    _schools.postValue(it)
                }
            }
        }
    }




}