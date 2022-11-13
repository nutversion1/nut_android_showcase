package com.nutversion1.nutandroidshowcase.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.nutversion1.nutandroidshowcase.api.ApiManager
import com.nutversion1.nutandroidshowcase.api.ErrorMessage
import com.nutversion1.nutandroidshowcase.api.requests.DetectLanguageRequest
import com.nutversion1.nutandroidshowcase.api.requests.TranslateRequest
import com.nutversion1.nutandroidshowcase.api.responses.DetectLanguageResponse
import com.nutversion1.nutandroidshowcase.api.responses.TranslateResponse
import com.nutversion1.nutandroidshowcase.api.responses.YoutubeSearchResponse
import kotlinx.coroutines.launch

class TranslateViewModel : ViewModel() {
    val translateResponseResult = MutableLiveData<TranslateResponseResult>()
    val detectLanguageResponseResult = MutableLiveData<DetectLanguageResponseResult>()

    fun translate(translateRequest: TranslateRequest){
        viewModelScope.launch {
            translateResponseResult.postValue(TranslateResponseResult.Loading)

            val result = ApiManager.getTranslateService().translate(translateRequest)

            if(result.isSuccessful){
                translateResponseResult.postValue(
                    result.body()?.let {
                        TranslateResponseResult.Success(it)
                    }
                )
            }else{
                val errorResponse = Gson().fromJson(result.errorBody()?.charStream(), ErrorMessage::class.java)
                translateResponseResult.postValue(TranslateResponseResult.Error(errorResponse.message.toString()))
            }
        }
    }

    fun detectLanguage(detectLanguageRequest: DetectLanguageRequest){
        viewModelScope.launch {
            detectLanguageResponseResult.postValue(DetectLanguageResponseResult.Loading)

            val result = ApiManager.getTranslateService().detectLanguage(detectLanguageRequest)

            if(result.isSuccessful){
                detectLanguageResponseResult.postValue(
                    result.body()?.let {
                        DetectLanguageResponseResult.Success(it)
                    }
                )
            }else{
                val errorResponse = Gson().fromJson(result.errorBody()?.charStream(), ErrorMessage::class.java)
                detectLanguageResponseResult.postValue(DetectLanguageResponseResult.Error(errorResponse.message.toString()))
            }
        }
    }

    class Factory : ViewModelProvider.Factory{
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return TranslateViewModel() as T
        }
    }

    sealed class TranslateResponseResult{
        class Success(val response: TranslateResponse) : TranslateResponseResult()
        class Error(val errorMessage: String) : TranslateResponseResult()
        object Loading : TranslateResponseResult()
    }

    sealed class DetectLanguageResponseResult{
        class Success(val response: DetectLanguageResponse) : DetectLanguageResponseResult()
        class Error(val errorMessage: String) : DetectLanguageResponseResult()
        object Loading : DetectLanguageResponseResult()
    }
}