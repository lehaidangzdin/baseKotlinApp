package com.lhd.androidbase.data.modelJson


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ImageRes(
    @Json(name = "data")
    val data: List<String>?,
    @Json(name = "e")
    val e: Any?,
    @Json(name = "message")
    val message: String,
    @Json(name = "status")
    val status: Int
)