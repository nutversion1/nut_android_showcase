package com.nutversion1.nutandroidshowcase.api.responses

import com.squareup.moshi.Json

data class FetchHoroscopeInformationResponse(
    @field:Json(name = "date_range") val dateRange: String,
    @field:Json(name = "current_date") val currentDate: String,
    @field:Json(name = "description") val description: String,
    @field:Json(name = "compatibility") val compatibility: String,
    @field:Json(name = "mood") val mood: String,
    @field:Json(name = "color") val color: String,
    @field:Json(name = "lucky_number") val luckyNumber: String,
    @field:Json(name = "lucky_time") val luckyTime: String,
)
