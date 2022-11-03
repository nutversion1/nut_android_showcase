package com.nutversion1.nutandroidshowcase.api

import com.nutversion1.nutandroidshowcase.api.requests.DetectLanguageRequest
import com.nutversion1.nutandroidshowcase.api.responses.DetectLanguageResponse
import com.nutversion1.nutandroidshowcase.api.responses.FetchHoroscopeInformationResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Query

interface AztroService {
    @POST(".")
    suspend fun fetchHoroscopeInformation(
        @Query("sign") sign: String,
        @Query("day") day: String,
    ): Response<FetchHoroscopeInformationResponse>
}