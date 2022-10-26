package com.nutversion1.nutandroidshowcase.api.responses

import com.squareup.moshi.Json

data class GetRandomHobbyResponse(
    @field:Json(name = "hobby") val hobby: String?,
    @field:Json(name = "link") val link: String?,
    @field:Json(name = "category") val category: String?,
)
