package com.nutversion1.nutandroidshowcase.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.nutversion1.nutandroidshowcase.api.ApiManager
import com.nutversion1.nutandroidshowcase.api.responses.GetRandomQuoteResponse
import kotlinx.coroutines.launch

class RandomQuoteViewModel : ViewModel() {
    val randomQuote = MutableLiveData<GetRandomQuoteResponse>()

    fun getRandomQuote(){
        viewModelScope.launch {
            val result = ApiManager.getRandomQuoteService().getRandomQuote()
            Log.d("myDebug", "result: ${result.body()}")

            randomQuote.postValue(result.body())
        }
    }

    class Factory : ViewModelProvider.Factory{
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return RandomQuoteViewModel() as T
        }
    }
}