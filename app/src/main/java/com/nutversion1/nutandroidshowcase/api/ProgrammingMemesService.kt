package com.nutversion1.nutandroidshowcase.api

import com.nutversion1.nutandroidshowcase.api.responses.GetMemesResponse
import retrofit2.Response
import retrofit2.http.GET

interface ProgrammingMemesService {
    @GET(".")
    suspend fun getMemes(): Response<List<GetMemesResponse>>
}