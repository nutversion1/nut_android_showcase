package com.nutversion1.nutandroidshowcase.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.nutversion1.nutandroidshowcase.api.ApiManager
import com.nutversion1.nutandroidshowcase.api.responses.FetchHoroscopeInformationResponse
import kotlinx.coroutines.launch

class AztroViewModel : ViewModel() {
    val fetchHoroscopeInformationResponse = MutableLiveData<FetchHoroscopeInformationResponse>()

    fun fetchHoroscopeInformation(sign: String, day: String){
        viewModelScope.launch {
            val result = ApiManager.getAztroService().fetchHoroscopeInformation(sign, day)
            Log.d("myDebug", "result: $result ${result.body()}")

            fetchHoroscopeInformationResponse.postValue(result.body())
        }
    }

    class Factory : ViewModelProvider.Factory{
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return AztroViewModel() as T
        }
    }
}