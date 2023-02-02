package com.example.firstnetworkapi.utils

class NullSchoolResponse(message: String = "Null response while trying to Get All Schools") : Exception(message)
class NullSatScoreResponse(message: String = "Null response while trying to get SAT Scores") : Exception(message)
class FailureResponse(message: String?) : Exception(message)