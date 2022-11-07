package com.nutversion1.nutandroidshowcase.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.nutversion1.nutandroidshowcase.api.ApiManager
import com.nutversion1.nutandroidshowcase.api.ErrorMessage
import com.nutversion1.nutandroidshowcase.api.responses.GetRandomHobbyResponse
import kotlinx.coroutines.launch

class HobbiesViewModel : ViewModel() {
    val response = MutableLiveData<Response>()

    fun getRandomHobby(category: String? = null){
        viewModelScope.launch {
            response.postValue(Response.Loading)

            val result = ApiManager.getHobbiesService().getRandomHobby(category)

            if(result.isSuccessful){
                response.postValue(result.body()?.let {
                    Response.Success(it)
                })

            }else{
                val errorResponse = Gson().fromJson(result.errorBody()?.charStream(), ErrorMessage::class.java)
                response.postValue(Response.Error(errorResponse.message))
            }
        }
    }

    class Factory : ViewModelProvider.Factory{
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return HobbiesViewModel() as T
        }
    }

    sealed class Response{
        class Success(val getRandomHobbyResponse: GetRandomHobbyResponse): Response()
        class Error(val errorMessage: String): Response()
        object Loading: Response()
    }
}

