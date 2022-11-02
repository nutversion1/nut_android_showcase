package com.nutversion1.nutandroidshowcase.api.requests

import com.squareup.moshi.Json

data class DetectLanguageRequest(
    @field:Json(name = "q") val text: String,
)
