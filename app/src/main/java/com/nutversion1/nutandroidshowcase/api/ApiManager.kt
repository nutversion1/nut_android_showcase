package com.nutversion1.nutandroidshowcase.api

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.RequestBody
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.moshi.MoshiConverterFactory


object ApiManager {
    fun getRandomQuoteService(): RandomQuoteService {
        val okHttpClient = OkHttpClient.Builder().apply {
            addInterceptor(
                Interceptor { chain ->
                    val builder = chain.request().newBuilder()
                    builder.header("X-RapidAPI-Key", "d6c331a93dmsh50acb261fb544bbp104233jsnf173aa315856")
                    return@Interceptor chain.proceed(builder.build())
                }
            )
        }.build()

        return Retrofit.Builder()
            .baseUrl("https://quotes15.p.rapidapi.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build().create(RandomQuoteService::class.java)
    }

    fun getHobbiesService(): HobbiesService {
        val okHttpClient = OkHttpClient.Builder().apply {
            addInterceptor(
                Interceptor { chain ->
                    val builder = chain.request().newBuilder()
                    builder.header("X-RapidAPI-Key", "d6c331a93dmsh50acb261fb544bbp104233jsnf173aa315856")
                    return@Interceptor chain.proceed(builder.build())
                }
            )
        }.build()

        return Retrofit.Builder()
            .baseUrl("https://hobbies-by-api-ninjas.p.rapidapi.com/v1/")
            .addConverterFactory(MoshiConverterFactory.create())
            .client(okHttpClient)
            .build().create(HobbiesService::class.java)
    }

    fun getNumbersService(): NumbersService {
        val okHttpClient = OkHttpClient.Builder().apply {
            addInterceptor(
                Interceptor { chain ->
                    val builder = chain.request().newBuilder()
                    builder.header("X-RapidAPI-Key", "d6c331a93dmsh50acb261fb544bbp104233jsnf173aa315856")
                    return@Interceptor chain.proceed(builder.build())
                }
            )
        }.build()

        return Retrofit.Builder()
            .baseUrl("https://numbersapi.p.rapidapi.com/")
            .addConverterFactory(MoshiConverterFactory.create())
            .client(okHttpClient)
            .build().create(NumbersService::class.java)
    }

    fun getProgrammingMemesService(): ProgrammingMemesService {
        val okHttpClient = OkHttpClient.Builder().apply {
            addInterceptor(
                Interceptor { chain ->
                    val builder = chain.request().newBuilder()
                    builder.header("X-RapidAPI-Key", "d6c331a93dmsh50acb261fb544bbp104233jsnf173aa315856")
                    return@Interceptor chain.proceed(builder.build())
                }
            )
        }.build()

        return Retrofit.Builder()
            .baseUrl("https://programming-memes-reddit.p.rapidapi.com/")
            .addConverterFactory(MoshiConverterFactory.create())
            .client(okHttpClient)
            .build().create(ProgrammingMemesService::class.java)
    }

    fun getTranslateService(): TranslateService {
        val okHttpClient = OkHttpClient.Builder().apply {
            addInterceptor(
                Interceptor { chain ->
                    val builder = chain.request().newBuilder()
                    builder.header("X-RapidAPI-Key", "d6c331a93dmsh50acb261fb544bbp104233jsnf173aa315856")
                    return@Interceptor chain.proceed(builder.build())
                }
            )
        }.build()

        return Retrofit.Builder()
            .baseUrl("https://deep-translate1.p.rapidapi.com/language/translate/")
            .addConverterFactory(MoshiConverterFactory.create())
            .client(okHttpClient)
            .build().create(TranslateService::class.java)
    }

    fun getAztroService(): AztroService {
        val okHttpClient = OkHttpClient.Builder().apply {
            addInterceptor(
                Interceptor { chain ->
                    val builder = chain.request().newBuilder()
                    builder.header("X-RapidAPI-Key", "d6c331a93dmsh50acb261fb544bbp104233jsnf173aa315856")
                    return@Interceptor chain.proceed(builder.build())
                }
            )
        }.build()

        return Retrofit.Builder()
            .baseUrl("https://sameer-kumar-aztro-v1.p.rapidapi.com/")
            .addConverterFactory(MoshiConverterFactory.create())
            .client(okHttpClient)
            .build().create(AztroService::class.java)
    }

    fun getYoutubeSearchService(): YoutubeSearchService {
        val okHttpClient = OkHttpClient.Builder().apply {
            addInterceptor(
                Interceptor { chain ->
                    val builder = chain.request().newBuilder()
                    builder.header("X-RapidAPI-Key", "d6c331a93dmsh50acb261fb544bbp104233jsnf173aa315856")
                    return@Interceptor chain.proceed(builder.build())
                }
            )
        }.build()

        return Retrofit.Builder()
            .baseUrl("https://simple-youtube-search.p.rapidapi.com/")
            .addConverterFactory(MoshiConverterFactory.create())
            .client(okHttpClient)
            .build().create(YoutubeSearchService::class.java)
    }

    fun getFootballService(): FootballService {
        val okHttpClient = OkHttpClient.Builder().apply {
            addInterceptor(
                Interceptor { chain ->
                    val builder = chain.request().newBuilder()
                    builder.header("X-RapidAPI-Key", "d6c331a93dmsh50acb261fb544bbp104233jsnf173aa315856")
                    return@Interceptor chain.proceed(builder.build())
                }
            )
        }.build()

        return Retrofit.Builder()
            .baseUrl("https://football98.p.rapidapi.com/")
            .addConverterFactory(MoshiConverterFactory.create())
            .client(okHttpClient)
            .build().create(FootballService::class.java)
    }

    fun getFreeGamesService(): FreeGamesService {
        val okHttpClient = OkHttpClient.Builder().apply {
            addInterceptor(
                Interceptor { chain ->
                    val builder = chain.request().newBuilder()
                    builder.header("X-RapidAPI-Key", "d6c331a93dmsh50acb261fb544bbp104233jsnf173aa315856")
                    return@Interceptor chain.proceed(builder.build())
                }
            )
        }.build()

        return Retrofit.Builder()
            .baseUrl("https://free-to-play-games-database.p.rapidapi.com/api/")
            .addConverterFactory(MoshiConverterFactory.create())
            .client(okHttpClient)
            .build().create(FreeGamesService::class.java)
    }
}