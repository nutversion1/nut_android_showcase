package com.nutversion1.nutandroidshowcase.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.nutversion1.nutandroidshowcase.api.ApiManager
import com.nutversion1.nutandroidshowcase.api.responses.GetNumbersResponse
import kotlinx.coroutines.launch

class NumbersViewModel : ViewModel() {
    val getNumbersResponse = MutableLiveData<GetNumbersResponse>()

    fun getRandomDateFact(){
        viewModelScope.launch {
            val result = ApiManager.getNumbersService().getRandomDateFact()
            Log.d("myDebug", "result: ${result.body()}")

            getNumbersResponse.postValue(result.body())
        }
    }

    fun getRandomMathFact(){
        viewModelScope.launch {
            val result = ApiManager.getNumbersService().getRandomMathFact()
            Log.d("myDebug", "result: ${result.body()}")

            getNumbersResponse.postValue(result.body())
        }
    }

    fun getRandomTriviaFact(){
        viewModelScope.launch {
            val result = ApiManager.getNumbersService().getRandomTriviaFact()
            Log.d("myDebug", "result: ${result.body()}")

            getNumbersResponse.postValue(result.body())
        }
    }

    fun getRandomYearFact(){
        viewModelScope.launch {
            val result = ApiManager.getNumbersService().getRandomYearFact()
            Log.d("myDebug", "result: ${result.body()}")

            getNumbersResponse.postValue(result.body())
        }
    }

    class Factory : ViewModelProvider.Factory{
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return NumbersViewModel() as T
        }
    }
}