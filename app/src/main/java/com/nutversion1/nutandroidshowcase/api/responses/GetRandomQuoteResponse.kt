package com.nutversion1.nutandroidshowcase.api.responses

import com.squareup.moshi.Json

data class GetRandomQuoteResponse(
    @field:Json(name = "id") val id: Int,
    @field:Json(name = "language_code") val languageCode: String,
    @field:Json(name = "content") val content: String,
    @field:Json(name = "url") val url: String,
    @field:Json(name = "originator") val originator: Originator,
    @field:Json(name = "tags") val tags: List<String>,
)

data class Originator(
    @field:Json(name = "id") val id: Int,
    @field:Json(name = "name") val name: String,
    @field:Json(name = "url") val url: String,
)
