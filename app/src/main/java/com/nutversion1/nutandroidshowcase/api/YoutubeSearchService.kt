package com.nutversion1.nutandroidshowcase.api

import com.nutversion1.nutandroidshowcase.api.responses.YoutubeSearchResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface YoutubeSearchService {
    @GET("search")
    suspend fun search(
        @Query("query") query: String
    ): Response<YoutubeSearchResponse>
}