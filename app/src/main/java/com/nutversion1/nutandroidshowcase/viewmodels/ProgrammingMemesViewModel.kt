package com.nutversion1.nutandroidshowcase.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.nutversion1.nutandroidshowcase.api.ApiManager
import com.nutversion1.nutandroidshowcase.api.responses.GetMemesResponse
import kotlinx.coroutines.launch

class ProgrammingMemesViewModel : ViewModel() {
    val getMemesResponse = MutableLiveData<List<GetMemesResponse>>()

    fun getMemes(){
        viewModelScope.launch {
            val result = ApiManager.getProgrammingMemesService().getMemes()
            Log.d("myDebug", "result: ${result.body()}")

            getMemesResponse.postValue(result.body())
        }
    }

    class Factory : ViewModelProvider.Factory{
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return ProgrammingMemesViewModel () as T
        }
    }
}