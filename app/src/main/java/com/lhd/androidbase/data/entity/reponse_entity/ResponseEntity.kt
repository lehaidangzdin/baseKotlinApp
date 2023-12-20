package com.lhd.androidbase.data.entity.reponse_entity

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ResponseEntity(
    @Json(name = "data")
    val data: Any?,
    @Json(name = "e")
    val e: Any?,
    @Json(name = "message")
    val message: String,
    @Json(name = "status")
    val status: Int

)
