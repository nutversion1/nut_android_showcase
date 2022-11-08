package com.nutversion1.nutandroidshowcase.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.nutversion1.nutandroidshowcase.api.ApiManager
import com.nutversion1.nutandroidshowcase.api.ErrorMessage
import com.nutversion1.nutandroidshowcase.api.responses.GetNumbersResponse
import kotlinx.coroutines.launch
import retrofit2.Response

class NumbersViewModel : ViewModel() {
    val responseResult = MutableLiveData<ResponseResult>()

    fun getRandomDateFact(){
        viewModelScope.launch {
            responseResult.postValue(ResponseResult.Loading)

            val result = ApiManager.getNumbersService().getRandomDateFact()

            handleResponse(result)
        }
    }

    fun getRandomMathFact(){
        viewModelScope.launch {
            responseResult.postValue(ResponseResult.Loading)

            val result = ApiManager.getNumbersService().getRandomMathFact()

            handleResponse(result)
        }
    }

    fun getRandomTriviaFact(){
        viewModelScope.launch {
            responseResult.postValue(ResponseResult.Loading)

            val result = ApiManager.getNumbersService().getRandomTriviaFact()

            handleResponse(result)
        }
    }

    fun getRandomYearFact(){
        viewModelScope.launch {
            responseResult.postValue(ResponseResult.Loading)

            val result = ApiManager.getNumbersService().getRandomYearFact()

            handleResponse(result)
        }
    }

    private fun handleResponse(result: Response<GetNumbersResponse>){
        if(result.isSuccessful){
            responseResult.postValue(
                result.body()?.let {
                    ResponseResult.Success(it)
                }
            )
        }else{
            val errorResponse = Gson().fromJson(result.errorBody()?.charStream(), ErrorMessage::class.java)
            responseResult.postValue(ResponseResult.Error(errorResponse.message))
        }
    }

    class Factory : ViewModelProvider.Factory{
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return NumbersViewModel() as T
        }
    }

    sealed class ResponseResult{
        class Success(val getNumbersResponse: GetNumbersResponse):ResponseResult()
        class Error(val errorMessage: String):ResponseResult()
        object Loading:ResponseResult()
    }
}