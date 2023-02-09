package com.nutversion1.nutandroidshowcase.viewmodels

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.nutversion1.nutandroidshowcase.api.ApiManager
import com.nutversion1.nutandroidshowcase.api.ErrorMessage
import com.nutversion1.nutandroidshowcase.api.ResponseResult
import com.nutversion1.nutandroidshowcase.database.RandomQuoteEntity
import com.nutversion1.nutandroidshowcase.repository.RandomQuoteRepository
import com.nutversion1.nutandroidshowcase.ui.RandomQuoteUi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class RandomQuoteViewModel(private val repository: RandomQuoteRepository) : ViewModel() {
    val responseResult = MutableLiveData<ResponseResult>()

    fun getRandomQuote() {
        responseResult.postValue(ResponseResult.Loading)

        viewModelScope.launch(Dispatchers.Default) {
            repository.getQuote().let {
                responseResult.postValue(it)
            }
        }
    }

    class Factory(private val repository: RandomQuoteRepository) : ViewModelProvider.Factory{
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return RandomQuoteViewModel(repository) as T
        }
    }
}

