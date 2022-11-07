package com.nutversion1.nutandroidshowcase.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.nutversion1.nutandroidshowcase.api.ApiManager
import com.nutversion1.nutandroidshowcase.api.requests.DetectLanguageRequest
import com.nutversion1.nutandroidshowcase.api.requests.TranslateRequest
import com.nutversion1.nutandroidshowcase.api.responses.DetectLanguageResponse
import com.nutversion1.nutandroidshowcase.api.responses.TranslateResponse
import kotlinx.coroutines.launch

class TranslateViewModel : ViewModel() {
    val translateResponse = MutableLiveData<TranslateResponse>()
    val detectLanguageResponse = MutableLiveData<DetectLanguageResponse>()

    fun translate(translateRequest: TranslateRequest){
        viewModelScope.launch {
            val result = ApiManager.getTranslateService().translate(translateRequest)
            Log.d("myDebug", "result: $result ${result.body()}")

            translateResponse.postValue(result.body())
        }
    }

    fun detectLanguage(detectLanguageRequest: DetectLanguageRequest){
        viewModelScope.launch {
            val result = ApiManager.getTranslateService().detectLanguage(detectLanguageRequest)
            Log.d("myDebug", "result: $result ${result.body()}")

            detectLanguageResponse.postValue(result.body())
        }
    }

    class Factory : ViewModelProvider.Factory{
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return TranslateViewModel() as T
        }
    }
}