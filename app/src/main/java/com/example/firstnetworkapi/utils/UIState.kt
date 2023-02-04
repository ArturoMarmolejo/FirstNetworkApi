package com.example.firstnetworkapi.utils

sealed class UIState {
    object LOADING: UIState()
    //Adds the success state after domain data has been created
    data class ERROR(val error: Exception): UIState()

}