package com.nutversion1.nutandroidshowcase.api

import com.nutversion1.nutandroidshowcase.api.responses.GetLeagueTableResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface FootballService {
    @GET("premierleague/table")
    suspend fun getPremierLeagueTable(): Response<List<GetLeagueTableResponse>>

    @GET("laliga/table")
    suspend fun getLaligaTable(): Response<List<GetLeagueTableResponse>>

    @GET("seriea/table")
    suspend fun getSerieATable(): Response<List<GetLeagueTableResponse>>

    @GET("bundesliga/table")
    suspend fun getBundesligaTable(): Response<List<GetLeagueTableResponse>>
}