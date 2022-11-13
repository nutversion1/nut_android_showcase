package com.nutversion1.nutandroidshowcase.api

import com.google.gson.annotations.SerializedName

data class ErrorMessage(
    @SerializedName("message") val message: String?,
)
