package com.nutversion1.nutandroidshowcase.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.nutversion1.nutandroidshowcase.api.ApiManager
import com.nutversion1.nutandroidshowcase.api.ErrorMessage
import com.nutversion1.nutandroidshowcase.api.responses.GetLeagueTableResponse
import com.nutversion1.nutandroidshowcase.api.responses.GetMemesResponse
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import retrofit2.Response

class FootballViewModel : ViewModel(){
    val responseResult = MutableLiveData<ResponseResult>()

    fun getPremierLeagueTable(){
        viewModelScope.launch {
            responseResult.postValue(ResponseResult.Loading)

            try {
                handleResponse(ApiManager.getFootballService().getPremierLeagueTable())
            }
            catch (e: Exception){
                responseResult.postValue(ResponseResult.Error(e.toString()))
            }
        }
    }

    fun getLaligaTable(){
        viewModelScope.launch {
            responseResult.postValue(ResponseResult.Loading)

            try {
                handleResponse(ApiManager.getFootballService().getLaligaTable())
            }
            catch (e: Exception){
                responseResult.postValue(ResponseResult.Error(e.toString()))
            }
        }
    }

    fun getSerieATable(){
        viewModelScope.launch {
            responseResult.postValue(ResponseResult.Loading)

            try {
                handleResponse(ApiManager.getFootballService().getSerieATable())
            }
            catch (e: Exception){
                responseResult.postValue(ResponseResult.Error(e.toString()))
            }
        }
    }

    fun getBundesligaTable(){
        viewModelScope.launch {
            responseResult.postValue(ResponseResult.Loading)

            try {
                handleResponse(ApiManager.getFootballService().getBundesligaTable())
            }
            catch (e: Exception){
                responseResult.postValue(ResponseResult.Error(e.toString()))
            }
        }
    }

    private fun handleResponse(result: Response<List<GetLeagueTableResponse>>){
        if(result.isSuccessful){
            responseResult.postValue(
                result.body()?.let {
                    ResponseResult.Success(it)
                }
            )
        }else{
            val errorResponse = Gson().fromJson(result.errorBody()?.charStream(), ErrorMessage::class.java)
            responseResult.postValue(ResponseResult.Error(errorResponse.message.toString()))
        }
    }

    class Factory : ViewModelProvider.Factory{
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return FootballViewModel() as T
        }
    }

    sealed class ResponseResult{
        class Success(val response: List<GetLeagueTableResponse>) : ResponseResult()
        class Error(val errorMessage: String) : ResponseResult()
        object Loading : ResponseResult()
    }
}