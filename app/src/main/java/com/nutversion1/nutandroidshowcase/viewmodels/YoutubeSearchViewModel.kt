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
import com.nutversion1.nutandroidshowcase.api.responses.YoutubeSearchResponse
import kotlinx.coroutines.launch
import retrofit2.Response

class YoutubeSearchViewModel : ViewModel() {
    val responseResult = MutableLiveData<ResponseResult>()

    fun youtubeSearch(query: String){
        viewModelScope.launch {
            responseResult.postValue(ResponseResult.Loading)

            val result = ApiManager.getYoutubeSearchService().search(query)

            handleResponse(result)
        }
    }

    private fun handleResponse(result: Response<YoutubeSearchResponse>){
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
            return YoutubeSearchViewModel() as T
        }
    }

    sealed class ResponseResult{
        class Success(val response: YoutubeSearchResponse) : ResponseResult()
        class Error(val errorMessage: String) : ResponseResult()
        object Loading : ResponseResult()
    }
}