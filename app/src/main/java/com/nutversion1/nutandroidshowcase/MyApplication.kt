package com.nutversion1.nutandroidshowcase

import android.app.Application
import androidx.room.Room
import com.nutversion1.nutandroidshowcase.api.ApiManager
import com.nutversion1.nutandroidshowcase.database.RandomQuoteDao
import com.nutversion1.nutandroidshowcase.database.RandomQuoteDatabase
import com.nutversion1.nutandroidshowcase.di.*
import com.nutversion1.nutandroidshowcase.mapper.RandomQuoteMapper
import com.nutversion1.nutandroidshowcase.repository.RandomQuoteRepository
import com.nutversion1.nutandroidshowcase.viewmodels.RandomQuoteViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidFileProperties
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.GlobalContext.startKoin
import org.koin.core.logger.Level
import org.koin.dsl.module
import kotlin.random.Random

class MyApplication : Application() {


    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger(Level.INFO)
            androidContext(this@MyApplication)
            androidFileProperties()
            modules(listOf(
                serviceModule,
                databaseModule,
                mapperModule,
                repositoryModule,
                viewModelModule,
            ))
        }
    }
}