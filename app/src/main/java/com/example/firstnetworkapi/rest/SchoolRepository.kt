package com.example.firstnetworkapi.rest

import android.content.ContentValues.TAG
import android.util.Log
import com.example.firstnetworkapi.utils.FailureResponse
import com.example.firstnetworkapi.utils.NullSatScoreResponse
import com.example.firstnetworkapi.utils.NullSchoolResponse
import com.example.firstnetworkapi.views.UIState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

interface SchoolRepository {
    fun getAllSchools(): Flow<UIState>
    fun getSatScore(dbn: String): Flow<UIState>
}

class SchoolRepositoryImpl constructor(
    private val schoolApi: ServiceApi
): SchoolRepository {

    override fun getAllSchools(): Flow<UIState> = flow {
        emit(UIState.LOADING)
        Log.d("SchoolRepository", "getAllSchools test")

        try {
            val response = schoolApi.getAllSchools()
            if (response.isSuccessful) {
                response.body()?.let {
                    // todo emit success value
                    val temp = UIState.SUCCESS(it)
                } ?: throw NullSchoolResponse()
            }else {
                throw FailureResponse(response.errorBody()?.string())
            }
    } catch(e: Exception) {
            emit(UIState.ERROR(e))
        }
    }

    override fun getSatScore(dbn: String): Flow<UIState> = flow {
        emit(UIState.LOADING)
        try {
            val response = schoolApi.getSatScoresAsync(dbn).await()
            if(response.isSuccessful){
                response.body().let {
                    //todo emit success value
                    val temp = UIState.SUCCESS(it)
                    Log.d(TAG, "getSatScore: $it ")
                }
            } else {
                throw FailureResponse(response.errorBody()?.string())
            }
        } catch (e: Exception) {
            emit(UIState.ERROR(e))
        }
    }

}