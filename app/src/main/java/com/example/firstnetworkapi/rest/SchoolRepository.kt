package com.example.firstnetworkapi.rest

import com.example.firstnetworkapi.utils.FailureResponse
import com.example.firstnetworkapi.utils.NullSatScoreResponse
import com.example.firstnetworkapi.utils.NullSchoolResponse
import com.example.firstnetworkapi.view.UIState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

interface SchoolRepository {
    fun getAllSchools(): Flow<UIState>
    fun getSatScore(dbn: String): Flow<UIState>
}

abstract class SchoolRepositoryImpl @Inject constructor(
    private val schoolApi: ServiceApi
): SchoolRepository {

    override fun getAllSchools(): Flow<UIState> = flow {
        emit(UIState.LOADING)

        try {
            val response = schoolApi.getAllSchools()
            if (response.isSuccessful) {
                response.body()?.let {
                    // todo emit success value
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
                } ?: throw NullSatScoreResponse()
            } else {
                throw FailureResponse(response.errorBody()?.string())
            }
        } catch (e: Exception) {
            emit(UIState.ERROR(e))
        }
    }

}