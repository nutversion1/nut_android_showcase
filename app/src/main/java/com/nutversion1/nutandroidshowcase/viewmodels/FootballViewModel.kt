package com.nutversion1.nutandroidshowcase.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.nutversion1.nutandroidshowcase.api.ApiManager
import com.nutversion1.nutandroidshowcase.api.responses.GetLeagueTableResponse
import kotlinx.coroutines.launch

class FootballViewModel : ViewModel(){
    val leagueTableResponse = MutableLiveData<List<GetLeagueTableResponse>>()

    fun getPremierLeagueTable(){
        viewModelScope.launch {
            val result = ApiManager.getFootballService().getPremierLeagueTable()
            Log.d("myDebug", "result: $result ${result.body()}")

            leagueTableResponse.postValue(result.body())
        }
    }

    fun getLaligaTable(){
        viewModelScope.launch {
            val result = ApiManager.getFootballService().getLaligaTable()
            Log.d("myDebug", "result: $result ${result.body()}")

            leagueTableResponse.postValue(result.body())
        }
    }

    fun getSerieATable(){
        viewModelScope.launch {
            val result = ApiManager.getFootballService().getSerieATable()
            Log.d("myDebug", "result: $result ${result.body()}")

            leagueTableResponse.postValue(result.body())
        }
    }

    fun getBundesligaTable(){
        viewModelScope.launch {
            val result = ApiManager.getFootballService().getBundesligaTable()
            Log.d("myDebug", "result: $result ${result.body()}")

            leagueTableResponse.postValue(result.body())
        }
    }

    class Factory : ViewModelProvider.Factory{
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return FootballViewModel() as T
        }
    }
}