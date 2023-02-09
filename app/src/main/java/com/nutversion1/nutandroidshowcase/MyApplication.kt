package com.nutversion1.nutandroidshowcase

import android.app.Application
import android.util.Log
import androidx.room.Room
import com.nutversion1.nutandroidshowcase.api.ApiManager
import com.nutversion1.nutandroidshowcase.database.RandomQuoteDatabase
import com.nutversion1.nutandroidshowcase.database.RandomQuoteEntity
import com.nutversion1.nutandroidshowcase.repository.RandomQuoteRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.util.concurrent.Executors

class MyApplication : Application() {

}