package com.nutversion1.nutandroidshowcase.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.nutversion1.nutandroidshowcase.api.ApiManager
import com.nutversion1.nutandroidshowcase.api.ErrorMessage
import com.nutversion1.nutandroidshowcase.api.RandomQuoteService
import com.nutversion1.nutandroidshowcase.api.ResponseResult
import com.nutversion1.nutandroidshowcase.api.responses.GetRandomQuoteResponse
import com.nutversion1.nutandroidshowcase.database.RandomQuoteDao
import com.nutversion1.nutandroidshowcase.database.RandomQuoteEntity
import com.nutversion1.nutandroidshowcase.mapper.RandomQuoteMapper
import com.nutversion1.nutandroidshowcase.ui.RandomQuoteUi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import retrofit2.Response
import java.util.concurrent.Executors

class RandomQuoteRepository(
    private val randomQuoteService: RandomQuoteService,
    private val randomQuoteDao: RandomQuoteDao,
    private val randomQuoteMapper: RandomQuoteMapper,
){
    suspend fun getQuote(): ResponseResult {
        val result = randomQuoteService.getRandomQuote()

        if (result.isSuccessful) {
            result.body()?.let {
                randomQuoteDao.deleteQuotes()
                randomQuoteDao.insertQuote(randomQuoteMapper.serverToEntity(it))

                return ResponseResult.Success(randomQuoteMapper.serverToUi(it))
            }
        } else {
            randomQuoteDao.getQuotes().firstOrNull()?.let {
                return ResponseResult.Success(randomQuoteMapper.entityToUi(it))
            }
        }

        val errorResponse = Gson().fromJson(result.errorBody()?.charStream(), ErrorMessage::class.java)
        return ResponseResult.Error(errorResponse.message.toString())

    }
}