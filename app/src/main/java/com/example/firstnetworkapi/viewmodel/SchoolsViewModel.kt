package com.example.firstnetworkapi.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.firstnetworkapi.model.SchoolsItem
import com.example.firstnetworkapi.rest.SchoolRepository
import com.example.firstnetworkapi.rest.ServiceApi
import com.example.firstnetworkapi.view.UIState
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect

private const val TAG = "SchoolsViewModel"

class SchoolsViewModel(
    private val SchoolRepository: SchoolRepository,
    private val ioDispatcher :CoroutineDispatcher = Dispatchers.IO
) : ViewModel() {
    init {

    }

    var fragmentState: Boolean = false
    private val _schools: MutableLiveData<UIState> = MutableLiveData(UIState.LOADING)
    val schools: LiveData<UIState> get() = _schools

    private val _satScores: MutableLiveData<UIState> = MutableLiveData(UIState.LOADING)
    val satScores: LiveData<UIState> get() = _satScores

    fun getAllSchools(): List<SchoolsItem> {
        viewModelScope.launch(ioDispatcher) { //Clashing for main thread supervision
            _schools.postValue(UIState.LOADING).let {
                        //this post value only works in the main thread and worker therad
                        _schools.postValue(SchoolRepository.getAllSchools() as UIState)
                        withContext(Dispatchers.Main) {
                            //this set value only works in the mean thread
                            _schools.value = UIState.SUCCESS_SCHOOLSITEM(getAllSchools())
                            Log.d("onCreate", "getAllSchools: $it ")
                            return@withContext;
                        }
                    }
                } else {
                    throw Exception(response.errorBody()?.string())
                }


        }
        return emptyList()
    }



}