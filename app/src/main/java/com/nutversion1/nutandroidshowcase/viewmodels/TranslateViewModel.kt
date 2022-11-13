package com.nutversion1.nutandroidshowcase.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.nutversion1.nutandroidshowcase.api.ApiManager
import com.nutversion1.nutandroidshowcase.api.ErrorMessage
import com.nutversion1.nutandroidshowcase.api.ResponseResult
import com.nutversion1.nutandroidshowcase.api.requests.DetectLanguageRequest
import com.nutversion1.nutandroidshowcase.api.requests.TranslateRequest
import com.nutversion1.nutandroidshowcase.api.responses.DetectLanguageResponse
import com.nutversion1.nutandroidshowcase.api.responses.TranslateResponse
import com.nutversion1.nutandroidshowcase.api.responses.YoutubeSearchResponse
import kotlinx.coroutines.launch

class TranslateViewModel : ViewModel() {
    val translateResponseResult = MutableLiveData<ResponseResult>()
    val detectLanguageResponseResult = MutableLiveData<ResponseResult>()

    fun translate(translateRequest: TranslateRequest){
        viewModelScope.launch {
            translateResponseResult.postValue(ResponseResult.Loading)

            val result = ApiManager.getTranslateService().translate(translateRequest)

            if(result.isSuccessful){
                translateResponseResult.postValue(
                    result.body()?.let {
                        ResponseResult.Success(it)
                    }
                )
            }else{
                val errorResponse = Gson().fromJson(result.errorBody()?.charStream(), ErrorMessage::class.java)
                translateResponseResult.postValue(ResponseResult.Error(errorResponse.message.toString()))
            }
        }
    }

    fun detectLanguage(detectLanguageRequest: DetectLanguageRequest){
        viewModelScope.launch {
            detectLanguageResponseResult.postValue(ResponseResult.Loading)

            val result = ApiManager.getTranslateService().detectLanguage(detectLanguageRequest)

            if(result.isSuccessful){
                detectLanguageResponseResult.postValue(
                    result.body()?.let {
                        ResponseResult.Success(it)
                    }
                )
            }else{
                val errorResponse = Gson().fromJson(result.errorBody()?.charStream(), ErrorMessage::class.java)
                detectLanguageResponseResult.postValue(ResponseResult.Error(errorResponse.message.toString()))
            }
        }
    }

    class Factory : ViewModelProvider.Factory{
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return TranslateViewModel() as T
        }
    }
}