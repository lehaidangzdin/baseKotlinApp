package com.lhd.androidbase.data.entity.reponse_entity

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class BaseResponseEntity<T>(
    @Json(name = "data")
    val data: T,
    @Json(name = "e")
    val e: String?,
    @Json(name = "message")
    val message: String,
    @Json(name = "status")
    val status: Int
)


