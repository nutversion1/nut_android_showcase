package com.nutversion1.nutandroidshowcase.api

sealed class ResponseResult {
    class Success<T>(val response: T) : ResponseResult()
    class Error(val errorMessage: String) : ResponseResult()
    object Loading : ResponseResult()
}