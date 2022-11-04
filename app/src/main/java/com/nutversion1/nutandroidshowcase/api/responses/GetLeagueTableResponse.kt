package com.nutversion1.nutandroidshowcase.api.responses

import com.squareup.moshi.Json

data class GetLeagueTableResponse(
    @field:Json(name = "Position") val position: String,
    @field:Json(name = "SquadLogo") val squadLogo: String,
    @field:Json(name = "Name") val name: String,
    @field:Json(name = "Points") val points: String,
    @field:Json(name = "Played") val played: String,
    @field:Json(name = "Winned") val won: String,
    @field:Json(name = "Loosed") val loosed: String,
    @field:Json(name = "Tie") val tie: String,
    @field:Json(name = "Goal Difference") val goalDifference: String,
)
