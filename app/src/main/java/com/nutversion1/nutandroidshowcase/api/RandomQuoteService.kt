package com.nutversion1.nutandroidshowcase.api

import com.nutversion1.nutandroidshowcase.api.responses.GetRandomQuoteResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET

interface RandomQuoteService {
    @GET("quotes/random/")
    suspend fun getRandomQuote(): Response<GetRandomQuoteResponse>
}