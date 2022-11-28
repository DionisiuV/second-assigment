package com.valentin.secondhomework.model.data

sealed class ApiResponse<out T> {

    data class Success<out T>(val dataSet: T) : ApiResponse<T>()

    data class Error(val error: String) : ApiResponse<Nothing>()

    object Loading : ApiResponse<Nothing>()
}