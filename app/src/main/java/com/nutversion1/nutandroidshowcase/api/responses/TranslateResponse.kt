package com.nutversion1.nutandroidshowcase.api.responses

import com.squareup.moshi.Json

data class TranslateResponse(
    @field:Json(name = "data") val data: TranslateData?,
)

data class TranslateData(
    @field:Json(name = "translations") val translations: Translations?,
)

data class Translations(
    @field:Json(name = "translatedText") val translatedText: String?,
)
