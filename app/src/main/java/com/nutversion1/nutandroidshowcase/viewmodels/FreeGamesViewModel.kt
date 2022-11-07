package com.nutversion1.nutandroidshowcase.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.nutversion1.nutandroidshowcase.api.ApiManager
import com.nutversion1.nutandroidshowcase.api.responses.GetFreeGameDetailResponse
import com.nutversion1.nutandroidshowcase.api.responses.GetFreeGamesResponse
import kotlinx.coroutines.launch

class FreeGamesViewModel : ViewModel() {
    val getFreeGamesResponse = MutableLiveData<List<GetFreeGamesResponse>>()
    val getFreeGameDetailResponse = MutableLiveData<GetFreeGameDetailResponse>()

    fun getFreeGames(platform: String? = null, category: String? = null, sortBy: String? = null){
        viewModelScope.launch {
            val result = ApiManager.getFreeGamesService().getFreeGames(platform, category, sortBy)
            Log.d("myDebug", "result: $result")

            getFreeGamesResponse.postValue(result.body())
        }
    }

    fun getFreeGameDetail(id: Int){
        viewModelScope.launch {
            val result = ApiManager.getFreeGamesService().getFreeGameDetail(id)
            Log.d("myDebug", "result: $result ${result.body()}")

            getFreeGameDetailResponse.postValue(result.body())
        }
    }

    class Factory : ViewModelProvider.Factory{
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return FreeGamesViewModel() as T
        }
    }
}