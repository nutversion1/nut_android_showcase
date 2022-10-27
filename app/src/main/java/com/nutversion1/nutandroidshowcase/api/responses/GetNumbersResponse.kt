package com.nutversion1.nutandroidshowcase.api.responses

import com.squareup.moshi.Json

data class GetNumbersResponse(
    @field:Json(name = "text") val text: String?,
    @field:Json(name = "date") val date: String?,
    @field:Json(name = "year") val year: Int?,
    @field:Json(name = "number") val number: Int?,
    @field:Json(name = "found") val found: Boolean?,
    @field:Json(name = "type") val type: String?,
)
