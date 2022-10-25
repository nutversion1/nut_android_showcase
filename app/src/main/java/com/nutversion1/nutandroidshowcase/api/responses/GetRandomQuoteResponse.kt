package com.nutversion1.nutandroidshowcase.api.responses

import com.google.gson.annotations.SerializedName

data class GetRandomQuoteResponse(
    @SerializedName("id") val id: Int,
    @SerializedName("language_code") val languageCode: String,
    @SerializedName("content") val content: String,
    @SerializedName("url") val url: String,
)
