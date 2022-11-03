package com.nutversion1.nutandroidshowcase.api.responses

import com.squareup.moshi.Json

data class YoutubeSearchResponse(
    @field:Json(name = "results") val results: List<YoutubeSearchResult>,
)

data class YoutubeSearchResult(
    @field:Json(name = "url") val url: String,
    @field:Json(name = "title") val title: String,
    @field:Json(name = "uploadedAt") val uploadedAt: String,
)
