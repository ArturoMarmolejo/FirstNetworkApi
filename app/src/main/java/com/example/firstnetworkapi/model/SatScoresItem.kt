package com.example.firstnetworkapi.model


import com.google.gson.annotations.JsonAdapter
import com.google.gson.annotations.SerializedName
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SatScoresItem(
    @SerializedName("dbn")
    val dbn: String = "",
    @SerializedName("num_of_sat_test_takers")
    val numOfSatTestTakers: String = "",
    @SerializedName("sat_critical_reading_avg_score")
    val satCriticalReadingAvgScore: String = "",
    @SerializedName("sat_math_avg_score")
    val satMathAvgScore: String = "",
    @SerializedName("sat_writing_avg_score")
    val satWritingAvgScore: String = "",
    @SerializedName("school_name")
    val schoolName: String = ""
)