package com.nutversion1.nutandroidshowcase.api

import com.nutversion1.nutandroidshowcase.api.responses.GetRandomHobbyResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


interface HobbiesService {
    @GET("hobbies")
    suspend fun getRandomHobby(
        @Query("category") category: String?
    ): Response<GetRandomHobbyResponse>
}