package com.nutversion1.nutandroidshowcase.api

import com.nutversion1.nutandroidshowcase.api.responses.GetFreeGameDetailResponse
import com.nutversion1.nutandroidshowcase.api.responses.GetFreeGamesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface FreeGamesService {
    @GET("games")
    suspend fun getFreeGames(
        @Query("platform") platform: String?,
        @Query("category") category: String?,
        @Query("sort-by") sortBy: String?,
    ): Response<List<GetFreeGamesResponse>>

    @GET("game")
    suspend fun getFreeGameDetail(
        @Query("id") id: Int
    ): Response<GetFreeGameDetailResponse>
}