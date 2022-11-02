package com.nutversion1.nutandroidshowcase.api.responses

import com.squareup.moshi.Json

data class DetectLanguageResponse(
    @field:Json(name = "data") val data: DetectLanguageData?,
)

data class DetectLanguageData(
    @field:Json(name = "detections") val detections: List<Detection>?,
)

data class Detection(
    @field:Json(name = "language") val language: String?,
    @field:Json(name = "isReliable") val isReliable: Boolean?,
    @field:Json(name = "confidence") val confidence: Float?,
)
