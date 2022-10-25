package com.nutversion1.nutandroidshowcase.api

import com.nutversion1.nutandroidshowcase.api.responses.GetRandomQuoteResponse
import retrofit2.Call
import retrofit2.http.GET

interface RandomQuoteService {
    @GET("quote/random")
    fun getRandomQuote(): Call<GetRandomQuoteResponse>


//    @POST("auth/ids")
//    fun logIn(
//        @Body authRequest: AuthRequest
//    ): Call<AuthResponse>
//
//    @POST("reliability/check-death-status")
//    fun checkDeathStatus(
//        @Body checkDeathStatusRequest: CheckDeathStatusRequest
//    ): Call<CheckDeathStatusResponse>
//
//    @GET("reliability/currentDate")
//    fun getCurrentDate(): Call<GetCurrentDateResponse>
//
//    @POST("reliability/query-edocument")
//    fun queryEDocument(
//        @Body queryEDocumentRequest: QueryEDocumentRequest
//    ): Call<QueryEDocumentResponse>
}