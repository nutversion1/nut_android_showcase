package com.nutversion1.nutandroidshowcase.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.nutversion1.nutandroidshowcase.api.ApiManager
import com.nutversion1.nutandroidshowcase.api.responses.GetRandomHobbyResponse
import kotlinx.coroutines.launch

class HobbiesViewModel : ViewModel() {
    val getRandomHobbyResponse = MutableLiveData<GetRandomHobbyResponse>()

    fun getRandomHobby(category: String? = null){
        viewModelScope.launch {
            val result = ApiManager.getHobbiesService().getRandomHobby(category)
            Log.d("myDebug", "result: ${result.body()}")

            getRandomHobbyResponse.postValue(result.body())
        }
    }

    class Factory : ViewModelProvider.Factory{
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return HobbiesViewModel() as T
        }
    }
}