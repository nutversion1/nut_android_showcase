package com.nutversion1.nutandroidshowcase.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.nutversion1.nutandroidshowcase.api.ApiManager
import com.nutversion1.nutandroidshowcase.api.ErrorMessage
import com.nutversion1.nutandroidshowcase.api.ResponseResult
import com.nutversion1.nutandroidshowcase.api.responses.GetRandomHobbyResponse
import kotlinx.coroutines.launch

class HobbiesViewModel : ViewModel() {
    val responseResult = MutableLiveData<ResponseResult>()

    fun getRandomHobby(category: String? = null){
        viewModelScope.launch {
            responseResult.postValue(ResponseResult.Loading)

            val result = ApiManager.getHobbiesService().getRandomHobby(category)

            if(result.isSuccessful){
                responseResult.postValue(result.body()?.let {
                    ResponseResult.Success(it)
                })

            }else{
                val errorResponse = Gson().fromJson(result.errorBody()?.charStream(), ErrorMessage::class.java)
                responseResult.postValue(ResponseResult.Error(errorResponse.message.toString()))
            }
        }
    }

    class Factory : ViewModelProvider.Factory{
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return HobbiesViewModel() as T
        }
    }
}

