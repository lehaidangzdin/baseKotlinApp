package com.lhd.androidbase.data.entity.reponse_entity


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class RegisterResponseEntity(
    @Json(name = "authentication")
    val authentication: Authentication,
    @Json(name = "email")
    val email: String,
    @Json(name = "_id")
    val id: String,
    @Json(name = "username")
    val username: String
)

@JsonClass(generateAdapter = true)
data class Authentication(
    @Json(name = "password")
    val password: String,
    @Json(name = "salt")
    val salt: String
)