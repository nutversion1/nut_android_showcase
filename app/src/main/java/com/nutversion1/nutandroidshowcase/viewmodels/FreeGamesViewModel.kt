package com.nutversion1.nutandroidshowcase.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.nutversion1.nutandroidshowcase.api.ApiManager
import com.nutversion1.nutandroidshowcase.api.ErrorMessage
import com.nutversion1.nutandroidshowcase.api.responses.DetectLanguageResponse
import com.nutversion1.nutandroidshowcase.api.responses.GetFreeGameDetailResponse
import com.nutversion1.nutandroidshowcase.api.responses.GetFreeGamesResponse
import com.nutversion1.nutandroidshowcase.api.responses.TranslateResponse
import kotlinx.coroutines.launch

class FreeGamesViewModel : ViewModel() {
    val getFreeGamesResponseResult = MutableLiveData<GetFreeGamesResponseResult>()
    val getFreeGameDetailResponseResult = MutableLiveData<GetFreeGameDetailResponseResult>()

    fun getFreeGames(platform: String? = null, category: String? = null, sortBy: String? = null){
        viewModelScope.launch {
            getFreeGamesResponseResult.postValue(GetFreeGamesResponseResult.Loading)

            val result = ApiManager.getFreeGamesService().getFreeGames(platform, category, sortBy)

            if(result.isSuccessful){
                getFreeGamesResponseResult.postValue(
                    result.body()?.let {
                        GetFreeGamesResponseResult.Success(it)
                    }
                )
            }else{
                val errorResponse = Gson().fromJson(result.errorBody()?.charStream(), ErrorMessage::class.java)
                getFreeGamesResponseResult.postValue(GetFreeGamesResponseResult.Error(errorResponse.message))
            }

        }
    }

    fun getFreeGameDetail(id: Int){
        viewModelScope.launch {
            getFreeGameDetailResponseResult.postValue(GetFreeGameDetailResponseResult.Loading)

            val result = ApiManager.getFreeGamesService().getFreeGameDetail(id)

            if(result.isSuccessful){
                getFreeGameDetailResponseResult.postValue(
                    result.body()?.let {
                        GetFreeGameDetailResponseResult.Success(it)
                    }
                )
            }else{
                val errorResponse = Gson().fromJson(result.errorBody()?.charStream(), ErrorMessage::class.java)
                getFreeGameDetailResponseResult.postValue(GetFreeGameDetailResponseResult.Error(errorResponse.message))
            }
        }
    }

    class Factory : ViewModelProvider.Factory{
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return FreeGamesViewModel() as T
        }
    }

    sealed class GetFreeGamesResponseResult{
        class Success(val response: List<GetFreeGamesResponse>) : GetFreeGamesResponseResult()
        class Error(val errorMessage: String) : GetFreeGamesResponseResult()
        object Loading : GetFreeGamesResponseResult()
    }

    sealed class GetFreeGameDetailResponseResult{
        class Success(val response: GetFreeGameDetailResponse) : GetFreeGameDetailResponseResult()
        class Error(val errorMessage: String) : GetFreeGameDetailResponseResult()
        object Loading : GetFreeGameDetailResponseResult()
    }
}