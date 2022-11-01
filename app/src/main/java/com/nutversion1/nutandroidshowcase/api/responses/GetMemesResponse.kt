package com.nutversion1.nutandroidshowcase.api.responses

import com.squareup.moshi.Json

data class GetMemesResponse(
    @field:Json(name = "title") val title: String,
    @field:Json(name = "link") val link: String,
    @field:Json(name = "is_safe") val isSafe: Boolean,
    @field:Json(name = "subreddit_name") val subRedditName: String,
    @field:Json(name = "up_vote") val upVote: Float,
    @field:Json(name = "down_vote") val downVote: Float,
)
