package com.example.firstnetworkapi.view

import com.example.firstnetworkapi.model.SatScoresItem
import com.example.firstnetworkapi.model.SchoolsItem

sealed class UIState {
    object LOADING : UIState()
    data class SUCCESS_SCHOOLSITEM (val response: List<SchoolsItem>) : UIState()
    data class SUCCESS_SATSCORES(val response: List<SatScoresItem>) : UIState()
    data class ERROR(val error: Exception) : UIState()


}
