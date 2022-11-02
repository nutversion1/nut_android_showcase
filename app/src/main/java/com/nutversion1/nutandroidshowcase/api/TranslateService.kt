package com.nutversion1.nutandroidshowcase.api

import com.nutversion1.nutandroidshowcase.api.requests.DetectLanguageRequest
import com.nutversion1.nutandroidshowcase.api.requests.TranslateRequest
import com.nutversion1.nutandroidshowcase.api.responses.DetectLanguageResponse
import com.nutversion1.nutandroidshowcase.api.responses.TranslateResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface TranslateService {
    @POST("v2")
    suspend fun translate(
        @Body translateRequest: TranslateRequest
    ): Response<TranslateResponse>

    @POST("v2/detect")
    suspend fun detectLanguage(
        @Body detectLanguageRequest: DetectLanguageRequest
    ): Response<DetectLanguageResponse>
}