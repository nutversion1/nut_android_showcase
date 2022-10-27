package com.nutversion1.nutandroidshowcase.api

import com.nutversion1.nutandroidshowcase.api.responses.GetNumbersResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NumbersService {
    @GET("random/date")
    suspend fun getRandomDateFact(
        @Query("json") json: Boolean? = true
    ): Response<GetNumbersResponse>

    @GET("random/math")
    suspend fun getRandomMathFact(
        @Query("json") json: Boolean? = true
    ): Response<GetNumbersResponse>

    @GET("random/trivia")
    suspend fun getRandomTriviaFact(
        @Query("json") json: Boolean? = true
    ): Response<GetNumbersResponse>

    @GET("random/year")
    suspend fun getRandomYearFact(
        @Query("json") json: Boolean? = true
    ): Response<GetNumbersResponse>
}