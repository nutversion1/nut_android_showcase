package com.nutversion1.nutandroidshowcase.api.requests

import com.google.gson.annotations.SerializedName
import com.squareup.moshi.Json

data class TranslateRequest(
    @field:Json(name = "q") val text: String,
    @field:Json(name = "source") val source: String,
    @field:Json(name = "target") val target: String,
)
