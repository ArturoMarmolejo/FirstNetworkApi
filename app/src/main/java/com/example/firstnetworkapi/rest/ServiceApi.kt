package com.example.firstnetworkapi.rest

import com.example.firstnetworkapi.model.SatScoresItem
import com.example.firstnetworkapi.model.SchoolsItem
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ServiceApi {

    /**
     * method to get the schools from server
     */
    @GET(SCHOOL_PATH)
    suspend fun getAllSchools(): Response<List<SchoolsItem>>

    @GET(SAT_SCORES)
    suspend fun getSatScoresAsync(
        @Path("dbn") dbn: String
    ): Deferred<Response<List<SatScoresItem>>>


    companion object {
        //SCHOOL LIST:
        // https://data.cityofnewyork.us/resource/s3k6-pzi2.json

        //SAT SCORES:
        //https://data.cityofnewyork.us/resource/f9bf-2cp4.json?dbn=11X253

        private const val DBN = "dbn"
        const val BASE_URL = "https://data.cityofnewyork.us/resource/"
        const val SCHOOL_PATH = "s3k6-pzi2.json"
        const val SAT_SCORES = "f9bf-2cp4.json?dbn=${DBN}"
    }
}