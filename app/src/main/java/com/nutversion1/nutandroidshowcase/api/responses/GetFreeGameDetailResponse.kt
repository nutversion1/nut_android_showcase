package com.nutversion1.nutandroidshowcase.api.responses

import com.squareup.moshi.Json

data class GetFreeGameDetailResponse(
    @field:Json(name = "id") val id: Int,
    @field:Json(name = "title") val title: String,
    @field:Json(name = "thumbnail") val thumbnail: String,
    @field:Json(name = "status") val Status: String,
    @field:Json(name = "short_description") val shortDescription: String,
    @field:Json(name = "description") val description: String,
    @field:Json(name = "game_url") val gameUrl: String,
    @field:Json(name = "genre") val genre: String,
    @field:Json(name = "platform") val platform: String,
    @field:Json(name = "publisher") val publisher: String,
    @field:Json(name = "developer") val developer: String,
    @field:Json(name = "release_date") val releaseDate: String,
    @field:Json(name = "freetogame_profile_url") val freeToGameProfileUrl: String,
    @field:Json(name = "minimum_system_requirements") val minimumSystemRequirements: MinimumSystemRequirements,
    @field:Json(name = "screenshots") val screenshots: List<FreeGameScreenshot>,
)

data class MinimumSystemRequirements(
    @field:Json(name = "os") val os: String,
    @field:Json(name = "processor") val processor: String,
    @field:Json(name = "memory") val memory: String,
    @field:Json(name = "graphics") val graphics: String,
    @field:Json(name = "storage") val storage: String,
)

data class FreeGameScreenshot(
    @field:Json(name = "id") val id: String,
    @field:Json(name = "image") val image: String,
)
