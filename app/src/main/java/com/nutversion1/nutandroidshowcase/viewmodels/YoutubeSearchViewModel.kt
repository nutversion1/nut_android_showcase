package com.nutversion1.nutandroidshowcase.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.nutversion1.nutandroidshowcase.MyViewModel
import com.nutversion1.nutandroidshowcase.api.ApiManager
import com.nutversion1.nutandroidshowcase.api.responses.YoutubeSearchResponse
import kotlinx.coroutines.launch

class YoutubeSearchViewModel : ViewModel() {
    val youtubeSearchResponse = MutableLiveData<YoutubeSearchResponse>()

    fun youtubeSearch(query: String){
        viewModelScope.launch {
            val result = ApiManager.getYoutubeSearchService().search(query)
            Log.d("myDebug", "result: $result ${result.body()}")

            youtubeSearchResponse.postValue(result.body())
        }
    }

    class Factory : ViewModelProvider.Factory{
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return YoutubeSearchViewModel() as T
        }
    }
}