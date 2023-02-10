package com.nutversion1.nutandroidshowcase.di

import android.app.Application
import androidx.room.Room
import com.nutversion1.nutandroidshowcase.api.ApiManager
import com.nutversion1.nutandroidshowcase.database.RandomQuoteDatabase
import com.nutversion1.nutandroidshowcase.mapper.RandomQuoteMapper
import com.nutversion1.nutandroidshowcase.repository.RandomQuoteRepository
import com.nutversion1.nutandroidshowcase.viewmodels.RandomQuoteViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val serviceModule = module {
    single { ApiManager.getRandomQuoteService() }
}

val databaseModule = module {
    fun provideRandomQuoteDatabase(application: Application) =
        Room.databaseBuilder(
            application, RandomQuoteDatabase::class.java, "random_quote_db")
            .fallbackToDestructiveMigration()
            .build()

    fun provideRandomQuoteDao(database: RandomQuoteDatabase) = database.randomQuoteDao()

    single { provideRandomQuoteDatabase(androidApplication()) }
    single { provideRandomQuoteDao(get()) }
}

val mapperModule = module {
    single { RandomQuoteMapper() }
}

val repositoryModule = module {
    single { RandomQuoteRepository(get(), get(), get()) }
}

val viewModelModule = module {
    viewModel{
        RandomQuoteViewModel(get())
    }
}