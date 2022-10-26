package com.nutversion1.nutandroidshowcase

import android.util.Log
import androidx.lifecycle.*
import com.nutversion1.nutandroidshowcase.api.ApiManager
import com.nutversion1.nutandroidshowcase.api.responses.GetRandomHobbyResponse
import com.nutversion1.nutandroidshowcase.api.responses.GetRandomQuoteResponse
import kotlinx.coroutines.launch

class MyViewModel : ViewModel() {
    val randomQuote = MutableLiveData<GetRandomQuoteResponse>()
    val getRandomHobbyResponse = MutableLiveData<GetRandomHobbyResponse>()

    fun getRandomQuote(){
        viewModelScope.launch {
            val result = ApiManager.getRandomQuoteService().getRandomQuote()
            Log.d("myDebug", "result: ${result.body()}")

            randomQuote.postValue(result.body())
        }
    }

    fun getRandomHobby(category: String? = null){
        viewModelScope.launch {
            val result = ApiManager.getHobbiesService().getRandomHobby(category)
            Log.d("myDebug", "result: ${result.body()}")

            getRandomHobbyResponse.postValue(result.body())
        }
    }

    class Factory : ViewModelProvider.Factory{
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return MyViewModel() as T
        }
    }
}